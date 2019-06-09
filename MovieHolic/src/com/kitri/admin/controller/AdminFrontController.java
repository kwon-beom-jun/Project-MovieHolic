package com.kitri.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet("/admin")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		
		String act = request.getParameter("act");
		String notify = request.getParameter("notify");
		String path = "/page/admin/management.jsp";
		

		if ("notify".equals(notify) && "alllist".equals(act)) { // 다른 페이지에서 management 들어올때.
			
			int cnt = 1; 
			path = AdminController.getAdminController().NFandAll(request, response,cnt);
			MoveUrl.forward(request, response, path);
			
		} else if ("notify".equals(notify)) { // 신고 게시판
			
			path = AdminController.getAdminController().NFselectByList(request, response);
			MoveUrl.forward(request, response, path);
			
		} else if ("alllist".equals(act)) { // 유저 전체 게시판
			
			int cnt = 1;
			path = AdminController.getAdminController().selectByUserList(request, response, cnt);
			MoveUrl.forward(request, response, path);

		} else if ("inactiveList".equals(act)) { // 유저 휴면 게시판
			
			int cnt = 2;
			path = AdminController.getAdminController().selectByUserList(request, response, cnt);
			MoveUrl.forward(request, response, path);

		} else if ("unsubscribelist".equals(act)) { // 유저 탈퇴 게시판

			int cnt = 3;
			path = AdminController.getAdminController().selectByUserList(request, response, cnt);
			MoveUrl.forward(request, response, path);
			
		} else if ("secession".equals(act)) { // 유저 탈퇴 처리
			
			path = AdminController.getAdminController().deletUser(request, response);
			MoveUrl.forward(request, response, path);
			
		} else if ("np_delete".equals(act)) { // 신고게시판 행 삭제
			
			path = AdminController.getAdminController().deleteBoard(request, response);
			MoveUrl.forward(request, response, path);
			
		} else if ("enable".equals(act)) { //전체페이지 휴면 변경
			
			path = AdminController.getAdminController().dormancy(request, response);
			MoveUrl.forward(request, response, path);
			
		} else if ("search".equals(act)) {
			
			path = AdminController.getAdminController().search(request, response);
			MoveUrl.forward(request, response, path);
			
		} 
//		else if () {
//			
//		} 
		else { // 이상하게 들어왔을때.
			
			int cnt = 4;
			path = AdminController.getAdminController().selectByUserList(request, response, cnt);
			MoveUrl.forward(request, response, path);
			
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}
