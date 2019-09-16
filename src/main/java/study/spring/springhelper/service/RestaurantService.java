package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.Restaurants;



public interface RestaurantService {
	/**
     * 학과 데이터 목록 조회
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
	public List<Restaurants.Items> getRestaurantList(Restaurants.Items input) throws Exception;
	
    public List<Restaurants.Items> getRestaurantList_Kor(Restaurants.Items input) throws Exception;
    
    public List<Restaurants.Items> getRestaurantList_Wtf(Restaurants.Items input) throws Exception;
    
    public List<Restaurants.Items> getRestaurantList_Jpn(Restaurants.Items input) throws Exception;
    
    public List<Restaurants.Items> getRestaurantList_Cafe(Restaurants.Items input) throws Exception;
    
    public List<Restaurants.Items> getRestaurantList_Etc(Restaurants.Items input) throws Exception;
    
    public List<Restaurants.Items> getRestaurantList_Chn(Restaurants.Items input) throws Exception;
    
    public Restaurants.Items getRestaurantOne(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Kor(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Chn(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Jpn(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Wtf(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Cafe(Restaurants.Items input) throws Exception;
    
    public int getRestaurantsCount_Etc(Restaurants.Items input) throws Exception;
    
}
