package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.ReserveCheck;


public interface ReserveCheckService {
	/**
     * 학과 데이터 목록 조회
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
	public List<ReserveCheck> getReserveCheckList(ReserveCheck input) throws Exception;
	
    
	/* public ReserveCheck getReserveCheckOne(ReserveCheck input) throws Exception; */
    
    public int getReserveCheckCount(ReserveCheck input) throws Exception;
    
    public int AddReserveCheck(ReserveCheck input) throws Exception;
    
    public int DeleteReserveCheck(ReserveCheck input) throws Exception;
    
}
