package com.camp24.controller.kimes.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camp24.controller.CmpInter;
import com.camp24.dao.kimes.MemberDao;
import com.camp24.vo.MemberVO;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author	김은선
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 김은선
 *
 */
	
public class EditInfo implements CmpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/editInfo";
		// 로그인 체크
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			view = "/camp24/member/login.cmp";
			req.setAttribute("isRedirect", true);
			return view;
		}
		
		// 데이터베이스에서 내정보 가져오기
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getIdInfo(sid);
		ArrayList<MemberVO> list = mDao.getAvtList();
		
		// 데이터 저장
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", list);
		
		return view;
	}

}
