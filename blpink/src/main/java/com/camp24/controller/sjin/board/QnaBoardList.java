package com.camp24.controller.sjin.board;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.camp24.controller.CmpInter;
import com.camp24.dao.sjin.QnaBoardDao;
import com.camp24.util.PageUtil;
import com.camp24.vo.BoardVO;

/**
 * 
 * @author	백서진
 * @since	2022/05/26
 * @version v.1.0
 * 
 * 			작업이력 ]
 * 				
 * 				2022/05/26	-	클래스 제작
 * 								QnA 게시판 리스트 폼 보기 클래스 제작
 * 									담당자 : 백서진
 *
 */

public class QnaBoardList implements CmpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/qnaBoard/qnaBoardList";
		
		// 할 일
		// 파라미터 꺼내고
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		String msg = req.getParameter("msg");
		
		// 총 게시글 수 조회
		QnaBoardDao qDao = new QnaBoardDao();
		
		int total = qDao.getTotal();
		PageUtil page = new PageUtil(nowPage, total);
		
		// 데이터베이스에서 게시글 리스트 가져오고
		ArrayList<BoardVO> list = qDao.getQBoardList(page);
		
		// 뷰에 데이터 심고
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		if(msg != null) {
			req.setAttribute("MSG", msg);
		}
		
		// 뷰 부르고
		return view;
	}

}
