package com.mingjalee.server.demo4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jerry
 * @create 16/9/29 11:01
 */
public class WebHandler extends DefaultHandler {
    private List<Entity> entityList;
    private List<Mapping> mappingList;
    private Entity entity;
    private Mapping mapping;
    private String beginTag = null;
    private boolean isMap;


    @Override
    public void startDocument() throws SAXException {
        //super.startDocument();
        //文档解析开始
        entityList = new ArrayList<Entity>();
        mappingList = new ArrayList<Mapping>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);
        //开始元素
        if (null != qName) {
            beginTag = qName;
            if (qName.equals("servlet")) {
                isMap = false;
                entity = new Entity();
            }
            else if(qName.equals("servlet-mapping")) {
                isMap = true;
                mapping = new Mapping();
            }
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);
        //处理内容
        if (beginTag != null) {
            String str = new String(ch, start, length);
            if (isMap) { //Mapping(string, string)
                if (beginTag.equals("servlet-name")) {
                    mapping.setServletName(str);
                }else if(beginTag.equals("url-pattern")) {
                    mapping.getUrlPatterns().add(str);
                }
            }else {
                if (beginTag.equals("servlet-name")) {
                    entity.setServletName(str);
                }else if (beginTag.equals("servlet-class")) {
                    entity.setServletClz(str);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        //结束元素
        if (null != qName) {
            if (qName.equals("servlet")) {
                entityList.add(entity);
            }else if (qName.equals("servlet-mapping")) {
                mappingList.add(mapping);
            }
        }

        beginTag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        //super.endDocument();
        //文档解析结束
    }

/*    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //获取解析器
        SAXParser parser = factory.newSAXParser();
        //编写处理器
        WebHandler web = new WebHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("com/mingjalee/server/demo4/web.xml"),
                web);

        System.out.println(web.getEntityList());
    }*/

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<Mapping> mappingList) {
        this.mappingList = mappingList;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public String getBeginTag() {
        return beginTag;
    }

    public void setBeginTag(String beginTag) {
        this.beginTag = beginTag;
    }

    public boolean isMap() {
        return isMap;
    }

    public void setMap(boolean map) {
        isMap = map;
    }
}
