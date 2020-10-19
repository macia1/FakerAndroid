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

public class PathEditor {
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


    public PathEditor(File manifestFile) throws DocumentException {
        this.manifestFile = manifestFile;
        SAXReader reader = new SAXReader();
        document = reader.read(manifestFile);
        resourcesElement = document.getRootElement();
    }
    public Element getResourcesElement(){
        return  resourcesElement;
    }

    public void copyElements(Element elementFrom, Element elementTo) {//TODO
        List<Element> elementsFrom = elementFrom.elements();
        List<Element> elementsTo = elementTo.elements();
        for (Element elementItemFrom : elementsFrom) {//TODO
            String fromName = elementItemFrom.attributeValue("name");
            if(!isNameRepeat(elementsTo, fromName)){
                Element copy = elementItemFrom.createCopy();
                elementTo.add(copy);
            }else{
            }
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

