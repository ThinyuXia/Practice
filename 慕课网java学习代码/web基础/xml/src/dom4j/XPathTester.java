package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

public class XPathTester {
    public void xPath(String xPathExp) throws DocumentException {
        String path = "/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/xml/hr.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(path);
        List<Node> nodes =  document.selectNodes(xPathExp); //执行xPath表达式，Node是Attribute和Element的父类
        for(Node node : nodes){
            Element emp = (Element) node;
            System.out.println(emp.element("name").getText());
        }
    }

    public static void main(String[] args) throws DocumentException {
        XPathTester xPathTester = new XPathTester();
//        xPathTester.xPath("/hr/employee"); //按照路径查找
//        xPathTester.xPath("//employee"); //全局查找employee标签
//        xPathTester.xPath("//employee[salary<20000]");
//        xPathTester.xPath("//employee[@number=3644]");
//        xPathTester.xPath("//employee[1]");
//        xPathTester.xPath("//employee[position() < 2]");
        xPathTester.xPath("//employee[1] | //employee[3]");
    }
}
