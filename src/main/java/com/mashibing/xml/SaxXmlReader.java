package com.mashibing.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * description  SaxXmlReader <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:03  2021/11/26
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class SaxXmlReader {

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(new UserHandler());
        parser.parse("D:\\work\\mashibing-lessons-pure\\mashibing-spring-redis\\src\\main\\resources\\User.xml");

    }

}
