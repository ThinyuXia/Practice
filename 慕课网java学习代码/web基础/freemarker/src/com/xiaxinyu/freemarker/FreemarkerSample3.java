package com.xiaxinyu.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaxinyu.freemarker.entity.Computer;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreemarkerSample3 {
 
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// TODO Auto-generated method stub
		// 1.加载模版
		// 创建核心配置对象
		Configuration config = new Configuration(Configuration.VERSION_2_3_31);
		// 设置加载目录
		config.setClassForTemplateLoading(FreemarkerSample3.class, ""); //在指定类所在的包下加载
		// 得到模版对象
		Template template = config.getTemplate("sample3.ftl");
		
		// 2.创建数据
		Map<String,Object> data = new HashMap<>();
		
		data.put("name", "jackson");
		data.put("brand", "bmw");
		data.put("words","blood first blood");
		data.put("n", 2123.3233);
		data.put("date", new Date());
		List<Computer> computers = new ArrayList();
		computers.add(new Computer("Mac",1,new HashMap(),10000));  
		computers.add(new Computer("HuaWei",1,new HashMap(),12000));  
		computers.add(new Computer("waixingren",1,new HashMap(),15000)); 
		data.put("list", computers);
		
		
		// 3.产生输出
		template.process(data,new OutputStreamWriter(System.out)); //将字节流转换为字符流
	}

}
