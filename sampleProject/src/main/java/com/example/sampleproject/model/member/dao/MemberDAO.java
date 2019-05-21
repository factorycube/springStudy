package com.example.sampleproject.model.member.dao;

import java.util.List;

import com.example.sampleproject.model.member.dto.MemberVO;

public interface MemberDAO {
	// 회원 목록 
    public List<MemberVO> memberList();
    // 회원 입력
    public void insertMember(MemberVO vo);
    // 회원 정보 상세보기
    public MemberVO viewMember(String userId);
    // 회원삭제
    public void deleteMember(String userId);
    // 회원정보 수정
    public void updateMember(MemberVO vo);
    // 회원 정보 수정 및 삭제를 위한 비밀번호 체크
    public boolean checkPw(String userId, String userPw);
}
