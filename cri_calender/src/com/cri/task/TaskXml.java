package com.cri.task;

import java.io.File;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
		Document document = builder.parse("taskXML.xml");
		Element root = (Element)document.getDocumentElement();
		Element task = document.createElement("task");

		//dateNum属性に日付を数字のみにしたものをもたせる
		String dateNum = time1.substring(0, 4) + time1.substring(5, 7) + time1.substring(8, 10);
		task.setAttribute("dateNum",dateNum);
		//root.appendChild(task);

		Element time = document.createElement("time");
		time.appendChild(document.createTextNode(time1));
		task.appendChild(time);

		Element title = document.createElement("title");
		title.appendChild(document.createTextNode(title1));
		task.appendChild(title);

		Element text = document.createElement("text");
		text.appendChild(document.createTextNode(text1));
		task.appendChild(text);

		//ここでどこに入れるか
		NodeList list = root.getElementsByTagName("task");

		int[] dateNumbers = new int[list.getLength()];

		if(dateNumbers.length != 0) {
			for(int i=0;i<list.getLength();i++) {

				Node node = ((Element)list.item(i));
				NamedNodeMap attrs = node.getAttributes();
				String attributeString = attrs.getNamedItem("dateNum").toString();
				int attr = Integer.parseInt(attributeString.substring(9,17));
				dateNumbers[i] = attr;
			}
		}

		int index = -1;
		for(int i=0;i<dateNumbers.length;i++) {

			if(Integer.parseInt(dateNum) == dateNumbers[i]) {

				index = i;
				break;
			}else if(Integer.parseInt(dateNum) < dateNumbers[i]) {

				index = i;
				break;
			}
		}

		System.out.println(index);

		if(index != -1) {

			Node node = ((Element)list.item(index));
			root.insertBefore(task, node);
		}else {

			root.appendChild(task);
		}

		//ファイルへ出力
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("taskXML.xml"));
		StreamResult result = new StreamResult(outputStreamWriter);
		transformer.transform(source,result);

	}

	public String[] viewXml() throws Exception{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("TaskXML.xml");

		//dateノードの数を取得するため
		NodeList list = document.getElementsByTagName("task");
		String[] str = new String[list.getLength()];

		for(int i=0;i<list.getLength();i++) {

			Element root = (Element)document.getDocumentElement();
			Node node = root.getElementsByTagName("task").item(i);
			String task = node.getTextContent();
			str[i] = task;

		}

		return str;

	}

	public String[] sendXml(int listNumber) throws Exception{

		String[] task = new String[5];
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("TaskXML.xml");

		Element root = (Element)document.getDocumentElement();

		NodeList time = root.getElementsByTagName("time");
		NodeList title = root.getElementsByTagName("title");
		NodeList text = root.getElementsByTagName("text");

		task[0] = time.item(listNumber).getTextContent().substring(0, 4);
		//日付が２桁（１０の位が０)か
		if(time.item(listNumber).getTextContent().substring(5,6).equals("0")) {
			task[1] = time.item(listNumber).getTextContent().substring(6, 7);
		}else {
			task[1] = time.item(listNumber).getTextContent().substring(5, 7);
		}

		if(time.item(listNumber).getTextContent().substring(8,9).equals("0")) {
			task[2] = time.item(listNumber).getTextContent().substring(9, 10);
		}else {
			task[2] = time.item(listNumber).getTextContent().substring(8, 10);
		}
		task[3] = title.item(listNumber).getTextContent().substring(0, title.item(listNumber).getTextContent().length() -1);
		task[4] = text.item(listNumber).getTextContent();

		return task;

	}

	public void removeTask(int listNumber) throws Exception{

		if(listNumber < 0) {
			return;
		}
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		Document document = builder.parse("taskXML.xml");
		Element root = (Element)document.getDocumentElement();

		NodeList list = root.getElementsByTagName("task");
		//ここで削除するタスクを指定
		root.removeChild(list.item(listNumber));

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("taskXML.xml"));
		StreamResult result = new StreamResult(outputStreamWriter);
		transformer.transform(source,result);
	}

}
