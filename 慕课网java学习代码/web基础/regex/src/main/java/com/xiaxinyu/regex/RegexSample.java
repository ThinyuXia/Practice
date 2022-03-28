package com.xiaxinyu.regex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSample {
	public static void main(String[] args) throws IOException {
		StringBuilder content = new StringBuilder();
		FileInputStream fis = new FileInputStream("/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/regex/src/main/webapp/sample.html");
		InputStreamReader isr = new InputStreamReader(fis,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(isr); //引入缓冲机制可提取文件读取效率
		String lineText = "";
		while((lineText = bufferedReader.readLine()) != null) {
//			System.out.println(lineText);
			content.append(lineText + "\n");
		}
		bufferedReader.close();
//		System.out.println(content.toString()); 
		//1.创建正则表达式对象
		Pattern p = Pattern.compile("<li>([\\u4e00-\\u9fa5]{2,10})([a-zA-Z]+)</li>"); //可对括号分组的信息单独提取
		//2.匹配正则表达式
		Matcher m = p.matcher(content);
		//3.查找匹配的结果
		while(m.find()) {
			 System.out.println(m.group(0));
			 System.out.println(m.group(1));
			 System.out.println(m.group(2));
		}
	}
}
