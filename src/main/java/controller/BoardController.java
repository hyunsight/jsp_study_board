package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.Board;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;
	private ServletContext ctx;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//init은 서블릿 객체 생성 시 딱 한번만 실행되므로 객체를 한번만 생성해 공유한다.
		dao = new BoardDAO();
		ctx = getServletContext(); //ServletContext: 웹 어플리케이션의 자원 관리
		
	}	
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //request 객체 한글깨짐 방지
		
		String command = request.getServletPath();
		String site = null;
		
		System.out.println("command: " + command);
		
		switch (command) {
			case "/index": site = getList(request); break; //index.jsp로 이동
			case "/view": break; //view.jsp로 이동
			case "/write": break; //write.jsp로 이동
			case "/insert": break;
			case "/edit": break; //edit.jsp로 이동
			case "/update": break;
			case "/delete": break;
		}
		
		ctx.getRequestDispatcher("/" + site).forward(request, response);
	}
	
	
	//BoardDAO 객체의 getList 메소드 실행: 게시물 전체 목록을 가져온 후 request 객체에 넣어준다.
	public String getList(HttpServletRequest request) {
		List<Board> list;
		
		try {
			list = dao.getList(); //throws Exception하는 메서드에 대해서는 try...catch 필수 
			request.setAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index.jsp";
	}
}
