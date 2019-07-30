package com.example.ygdf.xmldemo.Helper;

import android.content.Context;

import com.example.ygdf.xmldemo.pojo.Person;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomHelper {
    public static ArrayList<Person> queryXML(Context context){
        ArrayList<Person> Persons = new ArrayList<Person>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(context.getAssets().open("person2.xml"));
            System.out.println("处理的、该文档的DomImplemention对象="+doc.getImplementation());
            NodeList nList = doc.getElementsByTagName("person");

            for(int i = 0;i < nList.getLength();i++){
                Element personElement = (Element) nList.item(i);
                Person p = new Person();
                p.setId(Integer.parseInt(personElement.getAttribute("id")));

                NodeList childNoList = personElement.getChildNodes();
                for(int j = 0;j < childNoList.getLength();j++){
                    Node childNode = childNoList.item(j);
                    if(childNode.getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNode;
                        if("name".equals(childElement.getNodeName())){
                            p.setName(childElement.getFirstChild().getNodeValue());

                        }else if ("age".equals(childElement.getNodeName())){
                            p.setAge(Integer.parseInt(childElement.getFirstChild().getNodeValue()));
                        }
                    }

                    Persons.add(p);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Persons;
    }
}
