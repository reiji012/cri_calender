package com.cri.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TaskXml{

	public void createXml() throws Exception{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.newDocument();
		Element root = document.createElement("root");
		document.appendChild(root);

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		File newXML = new File("taskXML.xml");
		FileOutputStream os = new FileOutputStream(newXML);
		StreamResult result = new StreamResult(os);
		transformer.transform(source,result);
	}

	public void appendXml(String time1,String title1,String text1) throws Exception{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		FileInputStream fileInputStream = new FileInputStream("taskXML.xml");
		Document document = builder.parse(fileInputStream);
		fileInputStream.close();
		Element root = (Element)document.getDocumentElement();
		//Node target = root.getElementsByTagName("task").item(0);
		Element task = document.createElement("task");
		root.appendChild(task);

		Element time = document.createElement("time");
		time.appendChild(document.createTextNode(time1));
		task.appendChild(time);

		Element title = document.createElement("title");
		title.appendChild(document.createTextNode(title1));
		task.appendChild(title);

		Element text = document.createElement("text");
		text.appendChild(document.createTextNode(text1));
		task.appendChild(text);

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("taskXML.xml"));
		StreamResult result = new StreamResult(outputStreamWriter);
		transformer.transform(source,result);

	}

	public Element viewXml() throws Exception{

//		List<String> arrayList = new ArrayList<String>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("taskXML.xml");

		//dateノードの数を取得するため
		NodeList list = document.getElementsByTagName("date");
		//Node node = ((Element)nodeList.item(0)).getElementsByTagName("date").item(0);


//		for(int i=0;i<list.getLength();i++) {
//
		Element root = (Element)document.getDocumentElement();
//			Node node = root.getElementsByTagName("date").item(i);
//
//			arrayList.add(node.getTextContent());
//
//		}
//
//		return arrayList;
		return root;

	}

}
