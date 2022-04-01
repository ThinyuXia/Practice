package com.xiaxinyu.gallery.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.xiaxinyu.gallery.entity.Painting;

/*
 * 数据源类：用于将xml文件解析成java对象
 * */
public class XmlDataSource {
	//通过使用static关键字保证数据的全局唯一性
	private static List<Painting> data = new ArrayList<>();
	private static String dataFile;
	static {
		 dataFile = XmlDataSource.class.getResource("/painting.xml").getPath(); //获取类路径下的xml文件路径--运行时src路径下的文件会在classes路径下
		 																	
		 URLDecoder decoder = new URLDecoder();
		 try {
			dataFile = decoder.decode(dataFile,"utf-8"); //防止路径中的空格被转换成base64格式
			System.out.println(dataFile); 
			//利用Dom4j对xml文件进行解析
			SAXReader reader = new SAXReader();
			//1.获取Document文档对象
			Document document =  reader.read(dataFile);
			//2.利用XPath得到xml的结点集合 
			List<Node> nodes = document.selectNodes("/root/painting "); 
			for(Node node : nodes) {
				Element e = (Element) node;
				Painting painting = new Painting();
				painting.setId(Integer.parseInt(e.attributeValue("id")));
				painting.setPname(e.elementText("pname"));
				painting.setCategory(Integer.parseInt(e.elementText("category")));
				painting.setPrice(Integer.parseInt(e.elementText("price")));
				painting.setPreview(e.elementText("preview"));
				painting.setDescription(e.elementText("description"));
				data.add(painting);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
/*
 * 获取包含所有油画对象的List集合
 * */
	public static List<Painting> getRawData(){
		return data;
	}
	
	public static void main(String[] args) {
		List<Painting> list = XmlDataSource.getRawData();
//		System.out.println(list.size());
//		for(Painting p : list) {
//			System.out.println(p.getId());
//		}
	}
}
