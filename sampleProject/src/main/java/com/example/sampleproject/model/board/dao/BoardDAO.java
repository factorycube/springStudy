package com.example.sampleproject.model.board.dao;

import java.util.List;

import com.example.sampleproject.model.board.dto.BoardVO;

public interface BoardDAO {
	// 01. 게시글 작성
    public void create(BoardVO vo);
    // 02. 게시글 상세보기
    public BoardVO read(int bno);
    // 03. 게시글 수정
    public void update(BoardVO vo);
    // 04. 게시글 삭제
    public void delete(int bno);
    // 05. 게시글 전체 목록 ==> 검색옵션, 키워드 매개변수 추가
    public List<BoardVO> listAll(int start, int end, String searchOption, String keyword);
    // 06. 게시글 조회 증가
    public void increaseViewcnt(int bno);
    // 07. 게시글 레코드 갯수 메서드 추가
    public int countArticle(String searchOption, String keyword);
}
