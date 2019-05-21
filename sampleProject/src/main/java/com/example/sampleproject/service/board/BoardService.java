package com.example.sampleproject.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.sampleproject.model.board.dto.BoardVO;

public interface BoardService {
	// 01. 게시글 작성
    public void create(BoardVO vo);
    // 02. 게시글 상세보기
    public BoardVO read(int bno);
    // 03. 게시글 수정
    public void update(BoardVO vo);
    // 04. 게시글 삭제
    public void delete(int bno);
    // 05. 게시글 전체 목록
    public List<BoardVO> listAll();
    // 06. 게시글 조회
    public void increaseViewcnt(int bno, HttpSession session);
}
