package com.summer.tech.jmx.server;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.summer.tech.jmx.client.obj.RuntimeObject;
import com.summer.tech.jmx.client.obj.RuntimeObjectPool;
import com.sun.jdmk.comm.*;


public class Server implements NotificationListener {
	private static Logger logger=Logger.getLogger(Server.class.getName());
	private MBeanServer m_mbServer = MBeanServerFactory.createMBeanServer();
	private RuntimeObjectPool m_runtimePool = new RuntimeObjectPool();
	private String name;
	private String password;
	public Server() {
		try {
			ObjectName pool_name = new ObjectName(
					"OMS:name=Runtime Status Pool");
			m_mbServer.registerMBean(m_runtimePool, pool_name);
			m_runtimePool.addNotificationListener(this, null, null);
		} catch (Exception e) {
			logger.error("容器异常:",e);
		}

	}
	public void initRuntimeObject(String addr, String app, int dur) {
		m_runtimePool.initRuntimeObject(addr, app, dur);

	}
	public void dealTimeout(String addr, String app){
		RuntimeObject rt_obj=m_runtimePool.getRuntimeObject(addr, app);
		if(rt_obj!=null) {
			System.out.println("ADDR:"+addr+" APP:"+app+" TIMEOUT");
			m_runtimePool.reportTimeout(addr, app);

		}
	}
	public void dealReportAlive(String addr, String app){
		RuntimeObject rt_obj=m_runtimePool.getRuntimeObject(addr, app);
		if(rt_obj!=null) {
			//System.out.println("ADDR:"+addr+" APP:"+app+" is ALIVE");
			m_runtimePool.reportAlive(addr, app);
		}
	}

	public void dealFatal(String addr, String app,String info){
		RuntimeObject rt_obj=m_runtimePool.getRuntimeObject(addr, app);
		if(rt_obj!=null) {
			System.out.println("ADDR:"+addr+" APP:"+app+" is FATAL");
			m_runtimePool.reportFatal(addr, app,info);
		}
	}

	public void handleNotification(Notification notif, Object handback) {

		System.out.println("Receiving notification...");
		System.out.println(notif.getType());
		System.out.println(notif.getMessage());
	}

	public void startRMIConnector() {
		try {
			int NAMIN_PORT = 1099;
			String JNDI_PATH = "/jmxconnector";
			NamingService ns = new NamingService();
			ns.setPort(NAMIN_PORT);
			ns.start();

			JMXServiceURL jmx_url = new JMXServiceURL(
					"service:jmx:rmi://127.0.0.1/jndi/rmi://127.0.0.1:"
							+ NAMIN_PORT + JNDI_PATH);

			String[] credentials = new String[]{name,password};
			Map<String, String[]> props = new HashMap<String, String[]>();
		    props.put("jmx.remote.credentials", credentials);

			JMXConnectorServer connectorServer = JMXConnectorServerFactory
					.newJMXConnectorServer(jmx_url, props, m_mbServer);

			connectorServer.start();
		} catch (Exception e) {
			logger.error("容器异常:",e);
		}
	}

	public List<RuntimeObject> getRuntimeObjectList(){

		if(this.m_runtimePool==null)
			return null;

		return this.m_runtimePool.getRuntimeObjectList();

	}

	public void startHTMLAdaptor() {

		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		try {
			adapter.setPort(9092);
			// create the HTML adapter
			ObjectName adapterName = new ObjectName(
					"OMSAgent:name=htmladapter,port=9092");

			this.m_mbServer.registerMBean(adapter, adapterName);
			AuthInfo login = new AuthInfo();
	        login.setLogin(name);
	        login.setPassword(password);
	        //adapter.addUserAuthenticationInfo(login);

			adapter.start();
		} catch (Exception e) {
			logger.error("容器异常:",e);
			System.out.println("Error Starting HTML Adapter for Agent");
		}

	}

	public static void main(String args[]) {
		System.out.println("HelloAgent is start");
		Server container = new Server();
		container.startHTMLAdaptor();
		container.startRMIConnector();
		System.out.println("HelloAgent is running");
		while (true) {

			// agent.printGreeting();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}