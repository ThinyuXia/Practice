package com.xiaxinyu.total;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestTotalListener implements ServletContextListener,ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		
		HttpServletRequest req = (HttpServletRequest)arg0.getServletRequest();
		String url = req.getRequestURL().toString();
		
		//将用于获取图表数据的请求排除在外
		if(url.endsWith("/rt")) return;
		
		
		
		List<String> timeList = (List<String>) arg0.getServletContext().getAttribute("timeList");
		List<Integer> valueList = (List<Integer>) arg0.getServletContext().getAttribute("valueList");
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(data);
		if(timeList.indexOf(time) == -1) {
			timeList.add(time);
			valueList.add(1);
			arg0.getServletContext().setAttribute("timeList", timeList);
			arg0.getServletContext().setAttribute("valueList", valueList);
		}else {
			int index = timeList.indexOf(time);
			valueList.set(index, valueList.get(index) + 1);
			arg0.getServletContext().setAttribute("timeList", timeList);
			arg0.getServletContext().setAttribute("valueList", valueList);
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		List timeList = new ArrayList<>();
		List valueList = new ArrayList<>();
		arg0.getServletContext().setAttribute("timeList", timeList);
		arg0.getServletContext().setAttribute("valueList", valueList); 
		System.out.println("请求分析已初始化");
	}

}
