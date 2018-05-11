package com.summer.tech.jws.axiom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

public class AxiomDemo {

	public static void main(String[] args) throws Exception {
		AxiomDemo demo = new AxiomDemo();
		//demo.simpleRead();
		//demo.complexRead();
		//demo.simpleWrite();
		demo.complexWrite();
	}

	public void simpleRead() throws XMLStreamException, FactoryConfigurationError {
		// 首先对具体的xml文件构建parser
        InputStream xmlFile = AxiomDemo.class.getResourceAsStream("test.xml");
        //基于jdk stax的方式都xml文档，基于pull的方式
        XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(xmlFile);
        // 还需要StAXOMBuilder对象
        StAXOMBuilder builder = new StAXOMBuilder(parser);

        OMElement doc = builder.getDocumentElement();      //    读到<fool></fool>

        OMElement cre = doc.getFirstChildWithName(new QName("student"));  //读到<student>

        OMElement cre1 = cre.getFirstChildWithName(new QName("id"));  //    读到<id></id>
        System.out.println(cre1.getLocalName()+":"+cre1.getText());
        cre1 = cre.getFirstChildWithName(new QName("name"));       //    读到<name></name>
        System.out.println(cre1.getLocalName()+":"+cre1.getText());

        cre1 = cre.getFirstChildWithName(new QName("age"));      //    读到<age></age>
        System.out.println(cre1.getLocalName()+":"+cre1.getText());

        cre1 = cre.getFirstChildWithName(new QName("sex"));     //    读到<sex></sex>
        System.out.println(cre1.getLocalName()+":"+cre1.getText());
	}

	public void complexRead() throws XMLStreamException, FactoryConfigurationError {
		InputStream xmlFile = AxiomDemo.class.getResourceAsStream("test.xml");
        XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(xmlFile);

        StAXOMBuilder builder = new StAXOMBuilder(parser);

        OMElement doc = builder.getDocumentElement();

        Iterator<OMElement> iter = doc.getChildElements();
        while(iter.hasNext()){
            OMElement temp = iter.next();
            System.out.println("====================");
            System.out.println(temp.getLocalName());
            System.out.println(temp.getText());

            if(temp.getLocalName().equals("student")){
                Iterator<OMElement> iter1 = temp.getChildElements();
                System.out.println("----------------");
                while(iter1.hasNext()){
                    OMElement temp1 = iter1.next();
                    System.out.println(temp1.getLocalName()+":"+temp1.getText());
                }
            }
        }
	}

	public void simpleWrite() throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
		//1.建立节点
        // 通常通过OMFactory来构造XML文档中的element
        OMFactory factory = OMAbstractFactory.getOMFactory();

        //建立doc节点,doc节点会和下面的root节点合并
        OMDocument doc = factory.createOMDocument();

        //建立root节点
        OMElement root = factory.createOMElement(new QName("root"));

        //建立两个普通节点
        OMElement stu = factory.createOMElement(new QName("student"));
        stu.addChild(factory.createOMText("mac"));

        OMElement tea = factory.createOMElement(new QName("teacher"));
        tea.addChild(factory.createOMText("silly"));

        //2.构建树
        //构建树,将两个普通节点连到root节点上
        root.addChild(stu);
        root.addChild(tea);
        //构建树,将root节点连到doc节点上
        doc.addChild(root);

        //3. 写入文件
        // 构建writer做输出器
        XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(
                new FileOutputStream("test11.xml"));
        root.serialize(writer); // cache on
        writer.flush();
	}

	public void complexWrite() throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
		//1. 构建节点
        // 通常通过OMFactory来构造XML文档中的element
        OMFactory factory = OMAbstractFactory.getOMFactory();

        // 建立 namespace
        OMNamespace ns = factory.createOMNamespace("http://demo.axiom","x");
        OMNamespace ns1 = factory.createOMNamespace("http://ot.demo.axiom","y");

        //建立doc节点,doc节点会和下面的root节点合并
        OMDocument doc = factory.createOMDocument();

        //建立root节点
        OMElement root = factory.createOMElement("root",ns);

        //建立两个普通节点
        OMElement stu = factory.createOMElement("student",ns1);
        stu.addChild(factory.createOMText("mac"));

        OMElement tea = factory.createOMElement("teacher", "http://namespace", "ns");
        tea.addChild(factory.createOMText("silly"));

        // 2.构建树
        //构建树,将两个普通节点连到root节点上
        root.addAttribute(factory.createOMAttribute("attr", ns, "hello world"));
        root.addChild(stu);
        root.addChild(tea);
        //构建树,将root节点连到doc节点上
        doc.addChild(root);

        //3. 输出到文件或打印到屏幕
        // 构建writer做输出器
        XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(
                new FileOutputStream("test2.xml"));
        root.serialize(writer); // cache on
        writer.flush();
	}
}
