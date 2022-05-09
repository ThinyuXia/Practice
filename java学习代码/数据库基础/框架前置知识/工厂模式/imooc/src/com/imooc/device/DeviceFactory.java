package com.imooc.device;

import javax.servlet.http.HttpServletRequest;

public class DeviceFactory {
	public static Device getDevice(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		System.out.println(userAgent);
		if(userAgent.indexOf("Windows NT") != -1) {
			return new DesktopDevice();
		}else if(userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("Android") != -1) {
			return new MobileDevice();
		}else {
			return null;
		}
	}
}
