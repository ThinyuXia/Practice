package com.xiaxinyu.gallery.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
		 reload();
	}
	
/*
 * 获取包含所有油画对象的List集合
 * */
	public static List<Painting> getRawData(){
		return data;
	}
	
	private static void reload() {
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
			data.clear();
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
	
	public static void append(Painting painting){
		//1.读取XML文档得到Document对象
		SAXReader reader = new SAXReader();
		Writer writer = null;
		
		try {
			Document document = reader.read(dataFile);
			//2.创建新的painting结点
			Element root = document.getRootElement(); //获取root结点
			Element p = root.addElement("painting"); //创建新的子结点

			//3.创建painting结点的各个子结点
			p.addAttribute("id", String.valueOf(data.size() + 1));
			p.addElement("pname").setText(painting.getPname());;
			p.addElement("category").setText(String.valueOf(painting.getCategory()));
			p.addElement("price").setText(String.valueOf(painting.getPrice()));
			p.addElement("preview").setText(String.valueOf(painting.getPreview()));
			p.addElement("description").setText(String.valueOf(painting.getDescription()));
			//4.将painting结点写入XML，完成追加操作
			writer = new OutputStreamWriter(new FileOutputStream(dataFile),"utf-8");
			document.write(writer);
			
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			reload(); //内存与XML文件数据同步 
		}
		
		
	}
	public static void main(String[] args) {
		List<Painting> list = XmlDataSource.getRawData();
//		System.out.println(list.size());
//		for(Painting p : list) {
//			System.out.println(p.getId());
//		}
		Painting p = new Painting();
		p.setPname("测试油画");
		p.setCategory(1);
		p.setPrice(3644);
		p.setPreview("url");
		p.setDescription("description");
		XmlDataSource.append(p);
		System.out.println(dataFile);
	} 
}
