package com.xiaxinyu.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xiaxinyu.listener.entity.Channel;

@WebListener()
public class StaticDataListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		List list = new ArrayList<>();
		list.add(new Channel("免费课程","111"));
		list.add(new Channel("实战课程","112"));
		list.add(new Channel("就业课程","113"));
		arg0.getServletContext().setAttribute("list", list);
		
	}
	
}
