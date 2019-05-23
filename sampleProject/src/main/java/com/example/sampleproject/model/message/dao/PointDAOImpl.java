package com.example.sampleproject.model.message.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Inject
	SqlSession sqlSession;
	
	// 회원 포인트 갱신(메시지 발신)
	@Override
	public void updatePoint(String userId, int userPoint) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPoint", userPoint);
		sqlSession.update("point.updatePoint", map);
	}

}
