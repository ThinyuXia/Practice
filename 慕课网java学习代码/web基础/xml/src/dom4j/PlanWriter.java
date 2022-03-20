package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;

public class PlanWriter {
    public void writeXml() throws DocumentException, IOException {
        String path = "/Users/xiaxinyu/Desktop/github-repo/practice/慕课网java学习代码/web基础/xml/plan.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(path);
        Element root = document.getRootElement();
        Element course = root.addElement("course");
        course.addElement("course-name").setText("英语课");
        course.addElement("class-hour").setText("1.5小时");
        course.addElement("exam-form").setText("口试");
        Writer writer = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
        document.write(writer);
        writer.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        PlanWriter planWriter = new PlanWriter();
        planWriter.writeXml();
    }
}
