package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act= request.getParameter("action");
		
		// 방명록 리스트&추가
		if("addList".equals(act)) {
			GuestbookDao gbDao= new GuestbookDao();
			List<GuestbookVo> gbList= gbDao.getList();
			
			request.setAttribute("gl", gbList);
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);	
		}
		
		// 방명록 추가
		else if("add".equals(act)) {
			GuestbookDao gbDao = new GuestbookDao();
			
			String name= request.getParameter("name");
			String password= request.getParameter("password");
			String content= request.getParameter("content");
			
			GuestbookVo gv = new GuestbookVo(name, password, content);
			
			gbDao.guestInsert(gv);
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		
		// 방명록 삭제폼
		else if("deleteForm".equals(act)) {
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
		}
		
		//방명록 삭제
		else if("delete".equals(act)) {
			GuestbookDao gbDao = new GuestbookDao();
			
			int no= Integer.parseInt(request.getParameter("no"));
			String password1= gbDao.getGuest(no).getPassword();
			String password2= request.getParameter("password");

			
			if(password1.equals(password2)) {
				System.out.println("비밀번호 일치");
				gbDao.guestDelete(no);
			}
			else {
				System.out.println("비밀번호가 불일치");
			}
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		else {
			System.out.println("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
