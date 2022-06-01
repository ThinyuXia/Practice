package dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;

public class HrReader {
    public void readXml() throws DocumentException {
        String file = "/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/xml/hr.xml";
        //SAXReader类是读取xml文件的核心类，用于将解析的xml文件以树的形势保存在内存中
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);
        Element root = document.getRootElement(); //获取xml文件根标签
        //elements方法用于获取指定的标签集合
        List<Element> employees = root.elements("employee");
        for(Element employee : employees){
            // element方法用于获取唯一的子节点标签
             Element name = employee.element("name");
             String employeeName = name.getText(); //获取标签文本值；
             System.out.println(employeeName);
             System.out.println(employee.elementText("age"));
             System.out.println(employee.elementText("salary"));
             Element department = employee.element("department");
             System.out.println(department.elementText("dname"));
             System.out.println(department.elementText("address"));
             Attribute attribute = employee.attribute("number");
             System.out.println(attribute.getText() );
        }
    }
    public static void main(String[] args) throws DocumentException {
        HrReader hrReader = new HrReader();
        hrReader.readXml();
    }
}
