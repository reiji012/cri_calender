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

<<<<<<< HEAD
=======
		//既存のタスクの日付と追加されるタスクの日付を比較する
		NodeList list = root.getElementsByTagName("task");

		int[] dateNumbers = new int[list.getLength()];


		if(dateNumbers.length != 0) {
			for(int i=0;i<list.getLength();i++) {

				Node node = ((Element)list.item(i));
				NamedNodeMap attrs = node.getAttributes();
				//属性の表示を文字列としてもつ
				String attributeString = attrs.getNamedItem("dateNum").toString();
				//属性の表示の文字列から日付を表す数字部分を切り取り
				int attr = Integer.parseInt(attributeString.substring(9,17));
				dateNumbers[i] = attr;
			}
		}

		int index = -1;
		for(int i=0;i<dateNumbers.length;i++) {

			if(Integer.parseInt(dateNum) == dateNumbers[i]) {

				while(Integer.parseInt(dateNum) != dateNumbers[i]) {
					i++;
					index = i;
					break;
				}
			}else if(Integer.parseInt(dateNum) < dateNumbers[i]) {

				index = i;
				break;
			}
		}

		if(index != -1) {

			Node node = ((Element)list.item(index));
			root.insertBefore(task, node);
		}else {

			root.appendChild(task);
		}

		//ファイルへ出力
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938
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
			String sss = node.getTextContent();
			str[i] = sss;

		}

		return str;

	}


	public String[] dateTask (int dateNum) throws Exception{

		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		Document document = builder.parse("taskXML.xml");
		Element root = (Element)document.getDocumentElement();

		//dateノードの数を取得するためのlist
		NodeList list = document.getElementsByTagName("task");
		String[] str = new String[list.getLength()];

		for(int i=0;i<list.getLength();i++) {

			Node node = root.getElementsByTagName("task").item(i);
			NamedNodeMap attrs = node.getAttributes();
			//属性の表示を文字列としてもつ
			String attributeString = attrs.getNamedItem("dateNum").toString();
			//属性の表示の文字列から日付を表す数字部分を切り取り
			int attr = Integer.parseInt(attributeString.substring(9,17));
			if(attr == dateNum) {
				String task = node.getTextContent();
				str[i] = task;
			}
		}

		return str;

	}

	public String[] dateTask (int dateNum) throws Exception{

		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		Document document = builder.parse("taskXML.xml");
		Element root = (Element)document.getDocumentElement();

		//dateノードの数を取得するためのlist
		NodeList list = document.getElementsByTagName("task");
		String[] str = new String[list.getLength()];

		for(int i=0;i<list.getLength();i++) {

			Node node = root.getElementsByTagName("task").item(i);
			NamedNodeMap attrs = node.getAttributes();
			//属性の表示を文字列としてもつ
			String attributeString = attrs.getNamedItem("dateNum").toString();
			//属性の表示の文字列から日付を表す数字部分を切り取り
			int attr = Integer.parseInt(attributeString.substring(9,17));
			if(attr == dateNum) {
				String task = node.getTextContent();
				str[i] = task;
			}
		}

		return str;

	}

}
