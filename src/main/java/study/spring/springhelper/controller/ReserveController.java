package study.spring.springhelper.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.ReserveCheck;
import study.spring.springhelper.model.Restaurants;
import study.spring.springhelper.service.ReserveCheckService;
import study.spring.springhelper.service.RestaurantService;

@Controller
public class ReserveController {
	@Autowired
	WebHelper webHelper;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	ReserveCheckService reservecheckService;

	@RequestMapping(value = "Reserve/res.do", method = RequestMethod.GET)
	public ModelAndView reserve(Model model) {

		Calendar calendar = Calendar.getInstance();

		String checkdate = webHelper.getString("checkdate");

		checkdate = String.format("%04d년%02d월%02d일 %02d시 %02d분", calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

		int restNo = webHelper.getInt("restNo");

		Restaurants.Items output = null;

		Restaurants.Items input = new Restaurants.Items();
		input.setRestNo(restNo);

		try {
			output = restaurantService.getRestaurantOne(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		output.setRestNo(restNo);

		String restitle = output.getTitle();

		model.addAttribute("checkdate", checkdate);
		model.addAttribute("output", output);
		model.addAttribute("restitle", restitle);

		String viewPath = "Reserve/res";
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "Reserve/res_list.do", method = RequestMethod.GET)
	public ModelAndView reserve_list(Model model) {
		int checkNo = webHelper.getInt("checkNo");
		String title = webHelper.getString("getTitle");
		String checkdate = webHelper.getString("getchecktime");

		List<ReserveCheck> output = null;
		PageData pageData = null;

		// 페이지 번호(기본값 1)
		int nowPage = webHelper.getInt("page", 1);
		// 전체 게시글 수
		int totalCount = 0;
		// 한 페이지당 표시할 목록 수
		int listCount = 10;
		// 한 그룹당 표시할 페이지 번호 수
		int pageCount = 5;

		ReserveCheck input = new ReserveCheck();

		input.setCheckNo(checkNo);
		input.setTitle(title);
		input.setCheckdate(checkdate);

		try {
			totalCount = reservecheckService.getReserveCheckCount(input);
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			if (title != null) {
				reservecheckService.AddReserveCheck(input);
			}

			ReserveCheck.setOffset(pageData.getOffset());
			ReserveCheck.setListCount(pageData.getListCount());

			output = reservecheckService.getReserveCheckList(input);

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("output",output);
		model.addAttribute("pageData",pageData);
		
		

		String viewPath = "Reserve/res_list";
		return new ModelAndView(viewPath);

	}
	
	@RequestMapping(value = "/Reserve/res_delete.do",method = RequestMethod.GET)
	public ModelAndView delete(Model model) {
		
		int checkNo = webHelper.getInt("checkNo");
		
		if(checkNo == 0){
			webHelper.redirect("res_list.do","삭제할 내용이 없습니다.");
		} 
		
		ReserveCheck input = new ReserveCheck();
		input.setCheckNo(checkNo);
		
		try{
			reservecheckService.DeleteReserveCheck(input);
			webHelper.redirect("res_list.do","삭제되었습니다.");
		}catch(Exception e){
			return webHelper.redirect("res_list.do", "삭제에 실패 하였습니다.");
		}
		
		/*String viewPath = "Reserve/res_list";
		 return new ModelAndView(viewPath); */
		return webHelper.redirect("res_list.do","삭제되었습니다.");
		
		
		
	}
}
