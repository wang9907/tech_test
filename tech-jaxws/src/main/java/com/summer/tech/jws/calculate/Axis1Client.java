package com.summer.tech.jws.calculate;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Axis1Client {

	public static String endpoint = "http://localhost:8088/calculate";

	public static void main(String[] args) {
		invokeRemoteFuc();
	}

	public static void invokeRemoteFuc() {
		String result = "";
		Service service = new Service();
		Call call;
		Object[] object = new Object[2];
		object[0] = 10;// Object是用来存储方法的参数
		object[1] = 20;
		String targetNamespace = "http://calculate.jws.tech.summer.com/";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);// 远程调用路径
			call.setOperationName(new QName(targetNamespace,"add"));// 调用的方法名

			// 设置参数名:
			call.addParameter(new QName(targetNamespace,"x"), // 参数名
					XMLType.XSD_INT, // 参数类型:int
					ParameterMode.IN);// 参数模式：'IN' or 'OUT'
			call.addParameter(new QName(targetNamespace,"y"), // 参数名
					XMLType.XSD_INT, // 参数类型:int
					ParameterMode.IN);// 参数模式：'IN' or 'OUT'

			// 设置返回值类型：
			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String

			result = (String) call.invoke(object);// 远程调用

			System.out.println(result);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
