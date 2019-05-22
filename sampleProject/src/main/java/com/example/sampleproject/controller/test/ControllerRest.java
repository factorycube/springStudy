package com.example.sampleproject.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleproject.model.board.dto.BoardVO;

@RestController
@RequestMapping("/basic/*")
public class ControllerRest {
	// json객체 리턴
    @RequestMapping("/sendVO2")
    public BoardVO sendVO2(){
        BoardVO vo = new BoardVO();
        vo.setBno(1);
        vo.setWriter("DoubleS");
        vo.setContent("게시글 내용입니다");
        vo.setRecnt(1);
        vo.setTitle("게시글 1");
        vo.setUserName("DoubleS");
        return vo;
    }
    
    // json 객체 배열 리턴
    @RequestMapping("/sendList")
    public List<BoardVO> sendList(){
        // ArrayList 객체 생성
        List<BoardVO> items = new ArrayList<>();
        for(int i=1; i <=10; i++){
            BoardVO vo = new BoardVO(); //vo 객체 생성
            vo.setBno(i);
            vo.setWriter("DoubleS"+i);
            vo.setContent("게시글 내용입니다"+i);
            vo.setRecnt(i);
            vo.setTitle("게시글"+i);
            vo.setUserName("DoubleS"+i);
            items.add(vo); // 리스트에 vo추가
        }
        return items; // 리스트를 리턴함
    }
    
    // json객체를 map에 저장하여 
    @RequestMapping("/sendMap")
    public Map<Integer, BoardVO> sendMap(){
        // Map<Key자료형, Value자료형>
        Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
        for(int i=1; i <=10; i++){
            BoardVO vo = new BoardVO(); //vo 객체 생성
            vo.setBno(i);
            vo.setWriter("DoubleS"+i);
            vo.setContent("게시글 내용입니다"+i);
            vo.setRecnt(i);
            vo.setTitle("게시글"+i);
            vo.setUserName("DoubleS"+i);
            map.put(i, vo); // 맵에 vo추가
        }
        return map;
    }
    
 // ResponseEntity : 데이터 + http status code
    @RequestMapping("/sendMap2")
    public ResponseEntity<Map<Integer, BoardVO>> sendMap2(){
        // Map<Key자료형, Value자료형>
        //Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
        Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
        for(int i=1; i <=10; i++){
            BoardVO vo = new BoardVO(); //vo 객체 생성
            vo.setBno(i);
            vo.setWriter("DoubleS"+i);
            vo.setContent("게시글 내용입니다"+i);
            vo.setRecnt(i);
            vo.setTitle("게시글"+i);
            vo.setUserName("DoubleS"+i);
            map.put(i, vo); // 맵에 vo추가
        }
        // 리턴시 map과 상태메시지를 함께 전송
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
        
    @RequestMapping("/sendErrorAuth")
    public ResponseEntity<Void> sendListAuth(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
