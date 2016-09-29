package com.mingjalee.server.demo4;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author jerry
 * @create 16/9/27 10:33
 */
public class WebApp {
    private static ServletContext context;
    static {

        try {
            //获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //获取解析器
            SAXParser parser = factory.newSAXParser();
            WebHandler web = new WebHandler();
            parser.parse(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("com/mingjalee/server/demo4/web.xml"),
                    web);
            context = new ServletContext();
            Map<String, String> servlet = context.getServlet();
            //servlet-name -> servlet-class
            for(Entity entity:web.getEntityList()) {
                servlet.put(entity.getServletName(), entity.getServletClz());
            }


            //url-pattern -> servlet-name
            Map<String, String> mapping = context.getMapping();
            for (Mapping mapp:web.getMappingList()) {
                List<String> urls = mapp.getUrlPatterns();
                for (String url:urls) {
                    mapping.put(url, mapp.getServletName());
                }
            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static Servlet getServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (null == url || (url = url.trim()).equals("") ) {
            return null;
        }
        //return context.getServlet().get( (context.getMapping().get(url)) );
        //反射机制,动态获取对象
        String name = context.getServlet().get( context.getMapping().get(url) );
        return (Servlet) Class.forName(name).newInstance();//确保空构造存在
    }
}
