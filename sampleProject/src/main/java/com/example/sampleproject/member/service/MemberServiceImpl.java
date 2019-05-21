package com.example.sampleproject.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.sampleproject.member.model.dao.MemberDAOImpl;
import com.example.sampleproject.member.model.dto.MemberVO;

//현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberServiceImpl implements MemberService {

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
    @Inject
    MemberDAOImpl memberDao;
	
    // 01. 회원 목록
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	
	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	@Override
	public MemberVO viewMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub

	}

}
