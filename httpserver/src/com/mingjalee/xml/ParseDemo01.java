package com.mingjalee.xml;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * @author jerry
 * @create 16/9/27 21:59
 */
public class ParseDemo01 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        //1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();

        //3.加载文档 Document 注册处理器

        //4.编写处理器
        PersonHandler handler = new PersonHandler();
        parse.parse(Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("com/mingjalee/xml/person.xml"), handler);

        List<Person> persons = handler.getPersons();
        for (Person p : persons) {
            System.out.println(p.getName() + "--------->" + p.getAge());
        }
    }

}
