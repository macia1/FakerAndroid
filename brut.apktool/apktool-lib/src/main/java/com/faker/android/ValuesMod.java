package com.faker.android;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ValuesMod {
    /**
     * manifest 路径
     */
    File manifestFile;

    /**
     *
     */
    Document document;


    /**
     * manifest
     */
    Element resourcesElement;


    public ValuesMod(File manifestFile) throws DocumentException {
        this.manifestFile = manifestFile;
        SAXReader reader = new SAXReader();
        document = reader.read(manifestFile);
        resourcesElement = document.getRootElement();
    }
    public Element getResourcesElement(){
        return  resourcesElement;
    }


    public void copyValueElements(Element elementFrom, Element elementTo) {//
        List<Element> elementsFrom = elementFrom.elements();
        List<Element> elementsTo = elementTo.elements();

        for (Element elementItemFrom : elementsFrom) {//TODO 重名舍弃
            String fromName = elementItemFrom.attributeValue("name");
            if(!isNameRepeat(elementsTo, fromName)){
                Element copy = elementItemFrom.createCopy();
                elementTo.add(copy);
            }else{
            }
        }
    }

    //拷贝节点元素
    public void copyValueElement(Element elementFrom, Element elementTo) {//TODO
        List<Element> elementsTo = elementTo.elements();
        String fromName = elementFrom.attributeValue("name");
        if(!isNameRepeat(elementsTo, fromName)){
            Element copy = elementFrom.createCopy();
            elementTo.add(copy);
        }else{
//            System.out.println("name 重复元素:"+elementFrom.attributeValue("name"));
        }
    }





    private boolean isNameRepeat(List<Element> elementsTo, String fromName) {
        for (Element elementItemTo: elementsTo) {
            String toName = elementItemTo.attributeValue("name");
            if(toName.equals(fromName)){
                return true;
            }
        }
        return false;
    }


    public void save() throws IOException {
        OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
        xmlFormat.setEncoding("UTF-8");
        xmlFormat.setNewlines(true);
        xmlFormat.setIndent(true);
        xmlFormat.setIndent("    ");
        XMLWriter writer = new XMLWriter(new FileWriter(manifestFile), xmlFormat);
        writer.write(document);
        writer.close();
    }
}

