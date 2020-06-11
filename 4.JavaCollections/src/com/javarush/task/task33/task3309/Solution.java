package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, writer);

        String xml = writer.toString();

        Pattern pattern = Pattern.compile("<" + tagName + ">(?:[^<>]+)</" + tagName + ">");
        Matcher matcher = pattern.matcher(xml);
        StringBuilder sb = new StringBuilder(xml);
        int offset = 0;
        while (matcher.find()){
            int start = matcher.start() + offset;
            int end = matcher.end() + offset;
            String result = String.format("<!--%s-->%s", comment, matcher.group());
            sb.replace(start, end, result);
            offset = sb.length() - xml.length();
        }
        return sb.toString();
    }

    public static void main(String[] args) {

/*        try {
            String xmlData =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                            "<shop>\n" +
                            "    <goods>\n" +
                            "        <names>S1</names>\n" +
                            "        <names>S2</names>\n" +
                            "    </goods>\n" +
                            "    <count>12</count>\n" +
                            "    <profit>123.4</profit>\n" +
                            "    <secretData>String1</secretData>\n" +
                            "    <secretData>String2</secretData>\n" +
                            "    <secretData>String3</secretData>\n" +
                            "    <secretData>String4</secretData>\n" +
                            "    <secretData>String5</secretData>\n" +
                            "</shop>";

            StringReader reader = new StringReader(xmlData);

            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Shop o = (Shop) unmarshaller.unmarshal(reader);

            String result = toXmlWithComment(o, "secretData", "It's comment");
            System.out.println(result);
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

    }
}
