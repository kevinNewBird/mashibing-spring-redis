package com.mashibing.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * description  UserHandler <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:05  2021/11/26
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class UserHandler extends DefaultHandler {


    private List<String> nameList;

    private boolean isName = false;

    @Override
    public void startDocument() throws SAXException {
        this.nameList = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("name")) {
            isName = true;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (isName) {
            isName = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isName) {
            String name = new String(ch, start, length);
            System.out.println("userName:" + name);
            nameList.add(name);
        }
    }
}
