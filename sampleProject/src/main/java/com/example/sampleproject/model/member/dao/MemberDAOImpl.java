package com.example.sampleproject.model.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.sampleproject.model.member.dto.MemberVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class MemberDAOImpl implements MemberDAO {

	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
    // 의존관계 주입(Dependency Injection, DI)
    // 느스한 결함
    // IoC(Inversion of Control, 제어의 역전)
    @Inject
    // Inject애노테이션이 없으면 sqlSession은 null상태이지만
    // Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
    // try catch문, finally문, 객체를 close할 필요가 없어졌다.
    SqlSession sqlSession;
	
    // 01. 회원 목록
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}
	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
	}
	// 03. 회원 정보 상세 조회
	@Override
	public MemberVO memberInfo(String userId) {
		return sqlSession.selectOne("member.memberInfo", userId);
	}
	// 05. 회원 정보 삭제 처리
	@Override
	public void deleteMember(String userId) {
		sqlSession.delete("member.deleteMember",userId);
	}
	// 04. 회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		sqlSession.update("member.updateMember", vo);
	}
	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
    @Override
    public boolean checkPw(String userId, String userPw) {
        boolean result = false;
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("userPw", userPw);
        int count = sqlSession.selectOne("member.checkPw", map);
        if(count == 1) result= true;
        return result;
    }
    
    // 01_01. 회원 로그인체크
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name = sqlSession.selectOne("member.loginCheck", vo);
        return (name == null) ? false : true;
	}
	// 01_02. 회원 로그인 정보
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("member.viewMember", vo);
	}
	// 02. 회원 로그아웃
	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
	}
}
