package com.wsguardian.engine.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsguardian.engine.process.EngineServiceBean;

@WebServlet(urlPatterns = "/service/*", loadOnStartup = 1)
public class EngineServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println(request.getPathInfo()+"Esta es la url del request");
		//System.out.println("\n"+response.toString()+"   nothing up");
		EngineServiceBean service= new EngineServiceBean();
		service.processRequest(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    doGet(request,response);
	}
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            //System.out.println(response.getWriter());
            //System.out.println("<h3> Hello world</h3>");
	}
}
