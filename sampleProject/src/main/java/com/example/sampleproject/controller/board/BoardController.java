package com.example.sampleproject.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.sampleproject.model.board.dto.BoardVO;
import com.example.sampleproject.service.board.BoardPager;
import com.example.sampleproject.service.board.BoardService;
import com.example.sampleproject.service.board.ReplyService;

@Controller    // 현재 클래스를 컨트롤러 빈(bean)으로 등록
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// 의존관계 주입 => BoardServiceImpl 생성
    // IoC 의존관계 역전
    @Inject
    BoardService boardService;
    
    @Inject // ReplyService 주입(ReplyService의 댓글의 갯수를 구하는 메서드 호출하기 위해)
    ReplyService replyService;
    
    // 01. 게시글 목록
    @RequestMapping("list.do")
    // @RequestParam(defaultValue="") ==> 기본값 할당 : 현재페이지를 1로 초기화
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption,
                            @RequestParam(defaultValue="") String keyword,
                            @RequestParam(defaultValue="1") int curPage) throws Exception{
        
        // 레코드의 갯수 계산
        int count = boardService.countArticle(searchOption, keyword);
        
        // 페이지 나누기 관련 처리
        BoardPager boardPager = new BoardPager(count, curPage);
        int start = boardPager.getPageBegin();
        int end = boardPager.getPageEnd();
        
        List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
        
        // 데이터를 맵에 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list); // list
        map.put("count", count); // 레코드의 갯수
        map.put("searchOption", searchOption); // 검색옵션
        map.put("keyword", keyword); // 검색키워드
        map.put("boardPager", boardPager);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
        mav.setViewName("board/list"); // 뷰를 list.jsp로 설정
        
        return mav; // list.jsp로 List가 전달된다.
    }
    
    // 02_01. 게시글 작성화면
    // @RequestMapping("board/write.do")
    // value="", method="전송방식"
    @RequestMapping(value="write.do", method=RequestMethod.GET)
    public String write(){
        return "board/write"; // write.jsp로 이동
    }
    
    // 02_02. 게시글 작성처리
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo, HttpSession session) {
    	// session에 저장된 userId를 writer에 저장
        String writer = (String) session.getAttribute("userId");
        // vo에 writer를 세팅
        vo.setWriter(writer);
        boardService.create(vo);
        return "redirect:list.do";
    }
    
    // 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
    @RequestMapping(value="view.do", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, @RequestParam String searchOption,
                            @RequestParam String keyword, HttpSession session) throws Exception{
        boardService.increaseViewcnt(bno, session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("board/view");
        // 댓글의 수를 맵에 저장 : 댓글이 존재하는 게시물의 삭제처리 방지하기 위해 
        mav.addObject("count", replyService.count(bno)); 
        mav.addObject("dto", boardService.read(bno));
        mav.addObject("curPage", curPage);
        mav.addObject("searchOption", searchOption);
        mav.addObject("keyword", keyword);
        logger.info("mav:", mav);
        return mav;
    }
    
    // 04. 게시글 수정
    // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
    @RequestMapping(value="update.do", method=RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo) {
        boardService.update(vo);
        return "redirect:list.do";
    }
    
    // 05. 게시글 삭제
    @RequestMapping("delete.do")
    public String delete(@RequestParam int bno) {
        boardService.delete(bno);
        return "redirect:list.do";
    }
}
