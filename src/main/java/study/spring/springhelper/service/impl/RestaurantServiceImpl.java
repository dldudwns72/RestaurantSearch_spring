package study.spring.springhelper.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.Restaurants;
import study.spring.springhelper.model.Restaurants.Items;
import study.spring.springhelper.service.RestaurantService;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	SqlSession sqlSession;

	/** 생성자를 통한 객체 생성 
	public RestaurantServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	} */

	@Override
	public List<Restaurants.Items> getRestaurantList(Restaurants.Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Restaurants.Items> getRestaurantList_Kor(Restaurants.Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_kor", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Restaurants.Items> getRestaurantList_Chn(Restaurants.Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_Chn", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Items> getRestaurantList_Wtf(Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_Wtf", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Items> getRestaurantList_Jpn(Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_Jpn", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Items> getRestaurantList_Cafe(Items input) throws Exception {
		List<Restaurants.Items> result = null;

		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_Cafe", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public List<Items> getRestaurantList_Etc(Items input) throws Exception {
		List<Restaurants.Items> result = null;
		try {
			result = sqlSession.selectList("RestaurantsMapper.selectList_Etc", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public int getRestaurantsCount(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public Restaurants.Items getRestaurantOne(Restaurants.Items input) throws Exception {
		Restaurants.Items result = null;
		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectItem", input);

			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch (NullPointerException e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}

	@Override
	public int getRestaurantsCount_Kor(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Kor", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	@Override
	public int getRestaurantsCount_Chn(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Chn", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	@Override
	public int getRestaurantsCount_Jpn(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Jpn", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	@Override
	public int getRestaurantsCount_Wtf(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Wtf", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	@Override
	public int getRestaurantsCount_Cafe(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Cafe", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	
	@Override
	public int getRestaurantsCount_Etc(Items input) throws Exception {
		int result = 0;

		try {
			result = sqlSession.selectOne("RestaurantsMapper.selectCount_Etc", input);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}

		return result;
	}
	

}
