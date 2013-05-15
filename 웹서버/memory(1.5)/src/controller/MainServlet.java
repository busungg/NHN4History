package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommandMapping;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("메인 서블릿 초기화 로직");
		String path=getServletContext().getRealPath(getInitParameter("commandPath"));
		//String path=getServletContext().getRealPath(getInitParameter("commandPath"));commandConfig.properties
		System.out.println(path);
		CommandMapping.mapping(path);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("메인 서블릿 진입");
		
		String action=request.getParameter("action");
		System.out.println("action :" + action);
		//String nextPage=CommandMapping.getCommand(action).execute(request, response);
		CommandMapping.getCommand(action).execute(request, response);
		//System.out.println("nextPage :"+nextPage);
		//request.getRequestDispatcher(nextPage).forward(request, response);
	}
}


