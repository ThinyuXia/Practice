package com.xiaxinyu.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaxinyu.freemarker.entity.Computer;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerSample1 {
 
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// TODO Auto-generated method stub
		// 1.加载模版
		// 创建核心配置对象
		Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		// 设置加载目录
		config.setClassForTemplateLoading(FreemarkerSample1.class, ""); //在指定类所在的包下加载
		// 得到模版对象
		Template template = config.getTemplate("sample1.ftl");
		
		// 2.创建数据
		Map<String,Object> data = new HashMap<>();
		data.put("site","百度");
		data.put("url","https://www.baidu.com");
		data.put("date", new Date());
		data.put("number",837138.231231);
		
		Map<String,String> info = new HashMap<>();
		info.put("cpu","intel");
		info.put("name","imacbook");
		Computer c1 = new Computer("Mac",1,info,10000);
		data.put("c1", c1);
		// 3.产生输出
		template.process(data,new OutputStreamWriter(System.out)); //将字节流转换为字符流
	}

}
