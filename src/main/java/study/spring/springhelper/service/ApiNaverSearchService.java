package study.spring.springhelper.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import study.spring.springhelper.model.Img_Search;

public interface ApiNaverSearchService {
	
	String BASE_URL ="https://openapi.naver.com";
	@Headers({ "X-Naver-Client-Id: zeIUt33syZHSFsF7Pgyt", "X-Naver-Client-Secret: yLWDqhag3h" })
	//@GET("/v1/search/image.json?query = text&display=display")
	@GET("/v1/search/image.json")

	Call<Img_Search> getImage(@Query("query") String query, @Query("display") int display);

}
