package study.spring.springhelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.service.RestaurantService;

@Controller
public class IdealController {
	
	
	@Autowired
	WebHelper webHelper;

	@Autowired
	RestaurantService restaurantService;

	@Value("#{servletContext.contextPath}")
	String contextPath;
	

	@RequestMapping(value = "/Ideal/header.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView header(Model model) {
		String viewPath = "Ideal/header";
		return new ModelAndView(viewPath);
	}
	
	
	@RequestMapping(value = "Ideal/ideal_main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ideal_main(Model model) {
		//초기화
		int gamecount=0; 

		int g1 = 0;
		int g2 = 0;
		int g3 = 0;
		int g4 = 0;
		int g5 = 0;
		int g6 = 0;
		int g7 = 0;
		int g8 = 0;
		int g9 = 0;
		int g10 = 0;
		int g11 = 0;
		int g12 = 0;
		int g13 = 0;
		int g14 = 0;
		int g15 = 0;
		int g16 = 0;


		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result5 = 0;
		int result6 = 0;
		int result7 = 0;
		int result8 = 0;
		int result9 = 0;
		int result10 = 0;
		int result11 = 0;
		int result12 = 0;
		int result13 = 0;
		int result14 = 0;
		int result15 = 0;
		
		model.addAttribute("g1",g1);
		model.addAttribute("g2",g2);
		model.addAttribute("g3",g3);
		model.addAttribute("g4",g4);
		model.addAttribute("g5",g5);
		model.addAttribute("g6",g6);
		model.addAttribute("g7",g7);
		model.addAttribute("g8",g8);
		model.addAttribute("g9",g9);
		model.addAttribute("g10",g10);
		model.addAttribute("g11",g11);
		model.addAttribute("g12",g12);
		model.addAttribute("g13",g13);
		model.addAttribute("g14",g14);
		model.addAttribute("g15",g15);
		model.addAttribute("g16",g16);
		
		model.addAttribute("result1",result1);
		model.addAttribute("result2",result2);
		model.addAttribute("result3",result3);
		model.addAttribute("result4",result4);
		model.addAttribute("result5",result5);
		model.addAttribute("result6",result6);
		model.addAttribute("result7",result7);
		model.addAttribute("result8",result8);
		model.addAttribute("result9",result9);
		model.addAttribute("result10",result10);
		model.addAttribute("result11",result11);
		model.addAttribute("result12",result12);
		model.addAttribute("result13",result13);
		model.addAttribute("result14",result14);
		model.addAttribute("result15",result15);
		
		model.addAttribute("gamecount",gamecount);
		
		
		String viewPath = "Ideal/ideal_main";
		return new ModelAndView(viewPath);
	}
	
	
	@RequestMapping(value = "Ideal/ideal_worldcup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ideal_worldcup(Model model) {
		
		
	      int g1 = webHelper.getInt("g1");
	      int g2 = webHelper.getInt("g2");
	      int g3 = webHelper.getInt("g3");
	      int g4 = webHelper.getInt("g4");
	      int g5 = webHelper.getInt("g5");
	      int g6 = webHelper.getInt("g6");
	      int g7 = webHelper.getInt("g7");
	      int g8 = webHelper.getInt("g8");
	      int g9 = webHelper.getInt("g9");
	      int g10 = webHelper.getInt("g10");
	      int g11 = webHelper.getInt("g11");
	      int g12 = webHelper.getInt("g12");
	      int g13 = webHelper.getInt("g13");
	      int g14 = webHelper.getInt("g14");
	      int g15 = webHelper.getInt("g15");
	      int g16 = webHelper.getInt("g16");
	      
	      
	      int result1 = webHelper.getInt("result1");
	      int result2 = webHelper.getInt("result2");
	      int result3 = webHelper.getInt("result3");
	      int result4 = webHelper.getInt("result4");
	      int result5 = webHelper.getInt("result5");
	      int result6 = webHelper.getInt("result6");
	      int result7 = webHelper.getInt("result7");
	      int result8 = webHelper.getInt("result8");
	      int result9 = webHelper.getInt("result9");
	      int result10 = webHelper.getInt("result10");
	      int result11 = webHelper.getInt("result11");
	      int result12 = webHelper.getInt("result12");
	      int result13 = webHelper.getInt("result13");
	      int result14 = webHelper.getInt("result14");
	      int result15 = webHelper.getInt("result15");


	        int gamecount = webHelper.getInt("gamecount");
	        
	        
	        model.addAttribute("g1",g1);
			model.addAttribute("g2",g2);
			model.addAttribute("g3",g3);
			model.addAttribute("g4",g4);
			model.addAttribute("g5",g5);
			model.addAttribute("g6",g6);
			model.addAttribute("g7",g7);
			model.addAttribute("g8",g8);
			model.addAttribute("g9",g9);
			model.addAttribute("g10",g10);
			model.addAttribute("g11",g11);
			model.addAttribute("g12",g12);
			model.addAttribute("g13",g13);
			model.addAttribute("g14",g14);
			model.addAttribute("g15",g15);
			model.addAttribute("g16",g16);
			
			model.addAttribute("result1",result1);
			model.addAttribute("result2",result2);
			model.addAttribute("result3",result3);
			model.addAttribute("result4",result4);
			model.addAttribute("result5",result5);
			model.addAttribute("result6",result6);
			model.addAttribute("result7",result7);
			model.addAttribute("result8",result8);
			model.addAttribute("result9",result9);
			model.addAttribute("result10",result10);
			model.addAttribute("result11",result11);
			model.addAttribute("result12",result12);
			model.addAttribute("result13",result13);
			model.addAttribute("result14",result14);
			model.addAttribute("result15",result15);
			
			model.addAttribute("gamecount",gamecount);
			

			String viewPath = "Ideal/ideal_worldcup";
			return new ModelAndView(viewPath);
	        
		
	}
	
	
	

}
