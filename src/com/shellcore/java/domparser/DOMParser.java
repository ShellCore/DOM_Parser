package com.shellcore.java.domparser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar. 07/06/2017.
 */
public class DOMParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Load and parse de XML Document
        // Document contains the complete XML as a Tree
        String inputFile = "./src/students.xml";
        Document document = builder.parse(inputFile);

        List<Student> students = new ArrayList<>();

        // Iterating through the nodes list and extracting the data
        NodeList nodeList = document.getDocumentElement()
                .getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {


            // We have encountered a <student> tag
            Node node = nodeList.item(i);
            // Check if the current node is an instance of element
            if (node instanceof Element) {
                Student student = new Student();
                // Check if the current node is a student tag
                if (node.getNodeName().equals("student")) {
                    NamedNodeMap attr = node.getAttributes();
                    // get the student "id" and "active" attributes
                    student.id = attr.getNamedItem("id").getNodeValue();
                    student.active = attr.getNamedItem("active").getNodeValue();
                }
                // We get a list of the child nodes of student element
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);

                    // Identifying the child tag of student encountered
                    if (childNode instanceof Element) {
                        // We get the last child of the current tag
                        // The last child will be the next text node
                        String content = childNode.getLastChild()
                                .getNodeValue()
                                .trim();
                        switch (childNode.getNodeName()) {
                            case "firstName" :
                                student.firstName = content;
                                break;
                            case "lastName" :
                                student.lastName = content;
                                break;
                            case "location" :
                                student.location = content;
                                break;
                        }
                    }
                }
                students.add(student);
            }
        }

        students.forEach(student -> System.out.println(student));
    }
}
