package com.example.ygdf.xmldemo.Helper;

import android.util.Xml;

import com.example.ygdf.xmldemo.pojo.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PullHelper {
    public static ArrayList<Person> getPersons(InputStream xml)throws Exception{
        ArrayList<Person> persons = null;
        Person person = null;
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(xml,"utf-8");
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG:
                    if("person".equals(parser.getName())){
                        person = new Person();
                        int id = Integer.parseInt(parser.getAttributeValue(0));
                        person.setId(id);
                    }else if("name".equals(parser.getName())){
                        String name = parser.nextText();
                        person.setName(name);
                    }else if("age".equals(parser.getName())){
                        int age = Integer.parseInt(parser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("person".equals(parser.getName())){
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return persons;
    }

    public  static void save(List<Person> persons, OutputStream out) throws Exception{
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out,"utf-8");
        serializer.startDocument("utf-8",true);
        serializer.startTag(null,"persons");
        for (Person p : persons){
            serializer.startTag(null,"person");
            serializer.attribute(null,"id",p.getId() + "");
            serializer.startTag(null,"name");
            serializer.text(p.getName());
            serializer.endTag(null,"name");
            serializer.startTag(null,"age");
            serializer.text(p.getAge()+"");
            serializer.endTag(null,"age");
            serializer.endTag(null,"person");
        }

        serializer.endTag(null,"persons");
        serializer.endDocument();
        out.flush();
        out.close();
    }
}
