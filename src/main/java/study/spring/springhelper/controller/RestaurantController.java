package study.spring.springhelper.controller;

import java.util.List;

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
import study.spring.springhelper.model.Img_Search.Items;
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

	@RequestMapping(value = "/Restaurant/search.do", method = RequestMethod.GET)
	public ModelAndView list(Model model) {

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
		
		Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

		ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

		String query = webHelper.getString("query", "");
		System.out.println(query.toString()+"쿼리출력");
		
		
		Img_Search.Items img_search = null;

		if (!query.equals("")) {
			Call<Img_Search.Items> call = apinaversearchService.getImage(query, 1);
			try {
				img_search = call.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("query", query);
		model.addAttribute("img_search", img_search);

		String viewPath = "Restaurant/search";
		return new ModelAndView(viewPath);

	}

	@RequestMapping(value = "/Restaurant/place_info.do", method = RequestMethod.GET)
	public ModelAndView place_info(Model model) {
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

		String query = webHelper.getString("query", "");
		System.out.println(query.toString()+"쿼리출력");
		
		
		Img_Search.Items img_search = null;

		if (!query.equals("")) {
			Call<Img_Search.Items> call = apinaversearchService.getImage(query, 1);
			try {
				img_search = call.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("img_search",img_search);
		model.addAttribute("query",query);
		model.addAttribute("restNo", restNo);
		model.addAttribute("output", output);

		String viewPath = "Restaurant/place_info";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_kor.do", method = RequestMethod.GET)
	public ModelAndView cate_kor(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_kor";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_chn.do", method = RequestMethod.GET)
	public ModelAndView cate_chn(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_chn";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_jpn.do", method = RequestMethod.GET)
	public ModelAndView cate_jpn(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_jpn";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_wtf.do", method = RequestMethod.GET)
	public ModelAndView cate_wtf(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_wtf";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_cafe.do", method = RequestMethod.GET)
	public ModelAndView cate_cafe(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_cafe";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/Restaurant/cate_etc.do", method = RequestMethod.GET)
	public ModelAndView cate_etc(Model model) {
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

		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		model.addAttribute("totalCount", totalCount);

		String viewPath = "Restaurant/cate_etc";
		return new ModelAndView(viewPath);
	}

}
