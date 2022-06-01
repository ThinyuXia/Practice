package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PlanReader {
    public void readXml() throws DocumentException {
        String path = "/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/xml/plan.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(path);
        Element root = document.getRootElement();
        Element course = root.element("course");
        System.out.println(course.element("course-name").getText());
        System.out.println(course.element("class-hour").getText());
        System.out.println(course.element("exam-form").getText());

    }

    public static void main(String[] args) throws DocumentException {
        PlanReader planReader = new PlanReader();
        planReader.readXml();
    }
}
