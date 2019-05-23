package com.example.sampleproject.model.message.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.sampleproject.model.message.dto.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	SqlSession sqlSession;
	
	// 메시지 작성
	@Override
	public void create(MessageVO vo) {
		sqlSession.insert("message.create", vo);
	}
	// 메시지 열람
	@Override
	public MessageVO readMessage(int mid) {
		// TODO Auto-generated method stub
		return null;
	}
	// 메시지 열람시간 갱신
	@Override
	public void updateMessage(int mid) {
		// TODO Auto-generated method stub

	}

}
