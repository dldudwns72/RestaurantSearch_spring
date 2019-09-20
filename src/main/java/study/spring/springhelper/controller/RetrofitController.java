package study.spring.springhelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Img_Search;
import study.spring.springhelper.service.ApiNaverSearchService;

@Controller
public class RetrofitController {
	@Autowired
	WebHelper webHelper;

	@Autowired
	RetrofitHelper retrofitHelper;

	@RequestMapping(value = "/Retrofit/naver_image.do", method = RequestMethod.GET)
	public String NaverImageSearch(Model model) {
		Retrofit retrofit = retrofitHelper.getRetrofit(ApiNaverSearchService.BASE_URL);

		ApiNaverSearchService apinaversearchService = retrofit.create(ApiNaverSearchService.class);

		String query = webHelper.getString("query", "");

		Img_Search img_search = null;

		if (!query.equals("")) {
			Call<Img_Search> call = apinaversearchService.getImage(query, 10);

			try {
				img_search = call.execute().body();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("query",query);
		model.addAttribute("img_search",img_search);
		
		return "Retrofit/naver_image";
	}
}
