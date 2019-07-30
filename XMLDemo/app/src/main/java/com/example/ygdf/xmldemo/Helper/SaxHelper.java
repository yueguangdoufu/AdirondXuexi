package com.example.ygdf.xmldemo.Helper;

import android.util.Log;

import com.example.ygdf.xmldemo.pojo.Person;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SaxHelper extends DefaultHandler {

    private Person person;
    private ArrayList<Person> persons;
    private String tagName = null;

    @Override
    public void startDocument() throws SAXException {
        this.persons = new ArrayList<Person>();
        Log.i("SAX","读取到文档头,开始解析xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equals("person")){
            person = new Person();
            person.setId(Integer.parseInt(attributes.getValue("id")));
            Log.i("SAX","开始处理person元素~");
        }
        this.tagName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(this.tagName != null){
            String data = new String(ch,start,length);
            if(this.tagName.equals("name")){
                this.person.setName("name");
                Log.i("SAX","处理name元素内容");
            }else if(this.tagName.equals("age")){
                this.person.setAge(Integer.parseInt(data));
                Log.i("SAX","处理age元素内容");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("person")){
            this.persons.add(person);
            person = null;
            Log.i("SAX","处理person元素结束~");
        }
        this.tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.i("SAX","读取到文档尾，xml解析结束");
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }
}
