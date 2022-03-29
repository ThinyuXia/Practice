package com.xiaxinyu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class WebListener implements ServletContextListener,HttpSessionListener,ServletRequestListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletSession Create Seesion Id : " + arg0.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession Destroy");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext Destroy");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext Init");
	}
 

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("ServletRequest Destroy");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)arg0.getServletRequest();
		System.out.println("ServletRequest Init URI : " + req.getRequestURI());
		
	}
}