package study.spring.springhelper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Restaurants;
import study.spring.springhelper.service.RestaurantService;

@Controller
public class RandomController {
	@Autowired
	WebHelper webHelper;

	@Autowired
	RestaurantService restaurantService;

	@Value("#{servletContext.contextPath}")
	String contextPath;

	@RequestMapping(value = "Random/random.do", method = RequestMethod.GET)
	public ModelAndView random(Model model) {
		String viewPath = "Random/random";
		return new ModelAndView(viewPath);

	}

	@RequestMapping(value = "Random/random_list.do", method = RequestMethod.GET)
	public ModelAndView random_list(Model model) {
		String keyword = webHelper.getString("keyword", "");

		ArrayList<String> randomMenus_kor = new ArrayList<String>();
		ArrayList<String> randomMenus_jpn = new ArrayList<String>();
		ArrayList<String> randomMenus_chn = new ArrayList<String>();
		ArrayList<String> randomMenus_wtf = new ArrayList<String>();
		ArrayList<String> randomMenus_cafe = new ArrayList<String>();
		ArrayList<String> randomMenus_etc = new ArrayList<String>();

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword);
		
		String[] randomMenu_kor = { "불고기", "된장", "순두부", "뼈해장국", "김치", "냉면", "곱창", "감자", "찜닭", "해물", "생선", "족발", "삼계탕" };
		String[] randomMenu_jpn = { "스시", "초밥", "규카츠", "라멘", "사케", };
		String[] randomMenu_chn = { "짜장", "짬뽕", "탕수육", "사천", "중국","양꼬치", "마라탕", "마라샹궈" , "" };
		String[] randomMenu_wtf = { "양식", "버거", "파스타", "미즈", "" };
		String[] randomMenu_cafe = { "카페", "커피", "디저트", "마카롱", "베이커리", "밀크티", "과일주스" };
		String[] randomMenu_etc = { "베트남", "쌀국수", "키친", "타코", "가든", "술", "브런치" };

		for (int i = 0; i < randomMenu_kor.length; i++) {
			randomMenus_kor.add(randomMenu_kor[i]);
		}

		for (int i = 0; i < randomMenu_jpn.length; i++) {
			randomMenus_jpn.add(randomMenu_jpn[i]);
		}

		for (int i = 0; i < randomMenu_chn.length; i++) {
			randomMenus_chn.add(randomMenu_chn[i]);
		}

		for (int i = 0; i < randomMenu_wtf.length; i++) {
			randomMenus_wtf.add(randomMenu_wtf[i]);
		}

		for (int i = 0; i < randomMenu_cafe.length; i++) {
			randomMenus_cafe.add(randomMenu_cafe[i]);
		}

		for (int i = 0; i < randomMenu_etc.length; i++) {
			randomMenus_etc.add(randomMenu_etc[i]);
		}

		double randomValue = Math.random();

		int ran_kor = (int) (randomValue * randomMenus_kor.size()) - 1;
		int ran_jpn = (int) (randomValue * randomMenus_jpn.size()) - 1;
		int ran_chn = (int) (randomValue * randomMenus_chn.size()) - 1;
		int ran_wtf = (int) (randomValue * randomMenus_wtf.size()) - 1;
		int ran_cafe = (int) (randomValue * randomMenus_cafe.size()) - 1;
		int ran_etc = (int) (randomValue * randomMenus_etc.size()) - 1;

		// category 찾을때 쓰는 키워드 값
		String get_Menu_kor = randomMenus_kor.get(ran_kor);
		String get_Menu_jpn = randomMenus_jpn.get(ran_jpn);
		String get_Menu_chn = randomMenus_chn.get(ran_chn);
		String get_Menu_wtf = randomMenus_wtf.get(ran_wtf);
		String get_Menu_cafe = randomMenus_cafe.get(ran_cafe);
		String get_Menu_etc = randomMenus_etc.get(ran_etc);

		randomMenus_kor.remove(ran_kor);
		randomMenus_jpn.remove(ran_jpn);
		randomMenus_chn.remove(ran_chn);
		randomMenus_wtf.remove(ran_wtf);
		randomMenus_cafe.remove(ran_cafe);
		randomMenus_etc.remove(ran_etc);

		String keyword_kor = get_Menu_kor;
		String keyword_jpn = get_Menu_jpn;
		String keyword_chn = get_Menu_chn;
		String keyword_wtf = get_Menu_wtf;
		String keyword_cafe = get_Menu_cafe;
		String keyword_etc = get_Menu_etc;

		model.addAttribute("keyword_kor", keyword_kor);
		model.addAttribute("keyword_jpn", keyword_jpn);
		model.addAttribute("keyword_chn", keyword_chn);
		model.addAttribute("keyword_wtf", keyword_wtf);
		model.addAttribute("keyword_cafe", keyword_cafe);
		model.addAttribute("keyword_etc", keyword_etc);

		String viewPath = "Random/random_list";
		return new ModelAndView(viewPath);

	}

	@RequestMapping(value = "Random/random_menu_Kor.do", method = RequestMethod.GET)
	public ModelAndView random_kor(Model model) {
		String keyword_kor = webHelper.getString("keyword_kor", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_kor);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Kor(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Kor(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_kor",keyword_kor);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Kor";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "Random/random_menu_Chn.do", method = RequestMethod.GET)
	public ModelAndView random_chn(Model model) {
		String keyword_chn = webHelper.getString("keyword_chn", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_chn);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Chn(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Chn(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_chn",keyword_chn);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Chn";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "Random/random_menu_Jpn.do", method = RequestMethod.GET)
	public ModelAndView random_jpn(Model model) {
		String keyword_jpn = webHelper.getString("keyword_jpn", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_jpn);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Jpn(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Jpn(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_jpn",keyword_jpn);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Jpn";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "Random/random_menu_Wtf.do", method = RequestMethod.GET)
	public ModelAndView random_wtf(Model model) {
		String keyword_wtf = webHelper.getString("keyword_wtf", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_wtf);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Wtf(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Wtf(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_Wtf",keyword_wtf);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Wtf";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "Random/random_menu_Cafe.do", method = RequestMethod.GET)
	public ModelAndView random_cafe(Model model) {
		String keyword_cafe = webHelper.getString("keyword_cafe", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_cafe);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Cafe(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Cafe(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_cafe",keyword_cafe);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Cafe";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "Random/random_menu_Etc.do", method = RequestMethod.GET)
	public ModelAndView random_etc(Model model) {
		String keyword_etc = webHelper.getString("keyword_Etc", "");

		Restaurants.Items input = new Restaurants.Items();
		input.setTitle(keyword_etc);

		// 빈즈를 담을 객체 생성
		List<Restaurants.Items> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 9;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		try {
			totalCount = restaurantService.getRestaurantsCount_Etc(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Restaurants.Items.setOffset(pageData.getOffset());
			Restaurants.Items.setListCount(pageData.getListCount());

			output = restaurantService.getRestaurantList_Etc(input);

		}catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword_etc",keyword_etc);
		model.addAttribute("output",output);

		String viewPath = "Random/random_menu_Etc";
		return new ModelAndView(viewPath);

	}

}
