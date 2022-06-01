package com.xiaxinyu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener()
public class FirstListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
				System.out.println("ServletContext 已销毁"); 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		// TODO Auto-generated method stub
				System.out.println("ServletContext 已初始化");
	}
	
}
