package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HrWriter {
    public void writeXml() throws DocumentException, IOException {
        String file = "/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/xml/hr.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);
        Element root = document.getRootElement();
        Element employee = root.addElement("employee"); //addElement用于创建当前对象的子节点
        employee.addAttribute("number","3446");
        Element name = employee.addElement("name" );
        name.setText("王五");
        employee.addElement("age").setText("21");
        employee.addElement("salary").setText("22000");
        Element department =  employee.addElement("department");
        department.addElement("dname").setText("研发部");
        department.addElement("address").setText("北京");
        // 将内存中组织的模型写入xml文件
        Writer writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
        document.write(writer);
        writer.close();
    }

    public static void main(String[] args) throws DocumentException, IOException {
        HrWriter hrWriter = new HrWriter();
        hrWriter.writeXml();
    }
}
