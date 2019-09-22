package study.spring.springhelper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Img_Search;
import study.spring.springhelper.model.Restaurants;
import study.spring.springhelper.service.ApiNaverSearchService;
import study.spring.springhelper.service.RestaurantService;

@Controller
public class RestaurantController {
	@Autowired
	WebHelper webHelper;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	RetrofitHelper retrofitHelper;

	// "/프로젝트명"에 해당하는 ContextPath 변수 주입
	// import org.springframework.beans.factory.annotation.Value;
	@Value("#{servletContext.contextPath}")
	String contextPath;

	@RequestMapping(value = "/Restaurant/header.do", method = RequestMethod.GET)
	public ModelAndView header(Model model) {
		String viewPath = "Restaurant/header";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/img_search.do")
	public ModelAndView img_search(Model model) {
		String query = webHelper.getString("query", "");
		String title = webHelper.getString("title", "");

		Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

		ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

		Img_Search img_search = null;

		if (!query.equals("")) {
			Call<Img_Search> call = apinaversearchService.getImage(query, 1);
			try {
				img_search = call.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("img_search", img_search);
		model.addAttribute("query", query);
		model.addAttribute("title", title);

		String viewPath = "Restaurant/img_search";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/search.do", method = RequestMethod.GET)
	public ModelAndView list(Model model, HttpServletRequest request) {

		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;

		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);

				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();

					if (img_search.items.size() > 0) {
						img_search_result = img_search.items.get(0);
						// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
						thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("thumbnail", thumbnail);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("img_search", img_search);

		String viewPath = "Restaurant/search";
		return new ModelAndView(viewPath);

	}

	@RequestMapping(value = "/Restaurant/place_info.do", method = RequestMethod.GET)
	public ModelAndView place_info(Model model,HttpServletRequest request) {
		int restNo = webHelper.getInt("restNo");

		Restaurants.Items output = null;

		Restaurants.Items input = new Restaurants.Items();
		input.setRestNo(restNo);

		try {
			output = restaurantService.getRestaurantOne(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

		ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

		String thumbnail = null;
		
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;
		
		if (!output.getTitle().equals("")) {
			// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
			Call<Img_Search> call = apinaversearchService.getImage(output.getTitle(), 1);

			try {
				// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
				img_search = call.execute().body();

				if (img_search.items.size() > 0) {
					img_search_result = img_search.items.get(0);
					// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
					thumbnail = img_search_result.getThumbnail();
				} else {
					thumbnail = request.getContextPath() + "/assets/img/noimg.png";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("img_search", img_search);
		model.addAttribute("thumbnail",thumbnail);
		model.addAttribute("restNo", restNo);
		model.addAttribute("output", output);

		String viewPath = "Restaurant/place_info";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_kor.do", method = RequestMethod.GET)
	public ModelAndView cate_kor(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Kor(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Kor(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;

		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);

				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();

					if (img_search.items.size() > 0) {
						img_search_result = img_search.items.get(0);
						// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
						thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("thumbnail", thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_kor";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_chn.do", method = RequestMethod.GET)
	public ModelAndView cate_chn(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Chn(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Chn(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;

		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);

				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();

					if (img_search.items.size() > 0) {
						img_search_result = img_search.items.get(0);
						// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
						thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("thumbnail", thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_chn";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_jpn.do", method = RequestMethod.GET)
	public ModelAndView cate_jpn(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Jpn(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Jpn(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;

		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);

				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();

					if (img_search.items.size() > 0) {
						img_search_result = img_search.items.get(0);
						// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
						thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("thumbnail", thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_jpn";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_wtf.do", method = RequestMethod.GET)
	public ModelAndView cate_wtf(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Wtf(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Wtf(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;
		
		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);
			
			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);
				
				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();
					
					if (img_search.items.size() > 0) {
					img_search_result = img_search.items.get(0);
					// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
					thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		model.addAttribute("thumbnail",thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_wtf";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_cafe.do", method = RequestMethod.GET)
	public ModelAndView cate_cafe(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Cafe(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Cafe(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;
		
		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);
			
			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);
				
				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();
					
					if (img_search.items.size() > 0) {
					img_search_result = img_search.items.get(0);
					// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
					thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		model.addAttribute("thumbnail",thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_cafe";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_etc.do", method = RequestMethod.GET)
	public ModelAndView cate_etc(Model model, HttpServletRequest request) {
		String keyword = webHelper.getString("keyword", "");

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);

		// 전체 게시글 수
		int totalCount = 0;

		// 한 페이지당 표시할 목록 수
		int listCount = 9;

		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);

		List<Restaurants.Items> output = null;

		PageData pageData = null;

		try {
			totalCount = restaurantService.getRestaurantsCount_Etc(input);
			// 페이지 번호 계산 -> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = restaurantService.getRestaurantList_Etc(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		Restaurants.Items input1 = new Restaurants.Items();
		Img_Search img_search = null;
		Img_Search.Items img_search_result = null;
		
		String[] thumbnail = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			// input1 은 output리스트에서 하나씩 꺼내온다. output에는 정보가 들어있고
			input1 = output.get(i);
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

			ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);
			
			if (!input1.getTitle().equals("") || !input1.getTitle().equals(null)) {
				// output의 정보를 가지고 있는 inpu1객체의 title을 검색한다.
				Call<Img_Search> call = apinaversearchService.getImage(input1.getTitle(), 1);
				
				try {
					// img_search는 output정보가 있는 input1객체의 title에 대한 api 검색한 결과의 정보를 가지고 있다
					img_search = call.execute().body();
					
					if (img_search.items.size() > 0) {
					img_search_result = img_search.items.get(0);
					// thumbnail은 img_search_result의 thumbnail 값을 가지고 있다.
					thumbnail[i] = img_search_result.getThumbnail();
					} else {
						thumbnail[i] = request.getContextPath() + "/assets/img/noimg.png";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		model.addAttribute("thumbnail",thumbnail);
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_etc";
		return new ModelAndView(viewPath);
	}

}
