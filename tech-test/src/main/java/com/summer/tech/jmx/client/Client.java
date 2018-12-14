package com.summer.tech.jmx.client;

import java.util.HashMap;
import java.util.Map;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.summer.tech.jmx.client.obj.RuntimeObjectPoolMBean;

public class Client {

	public static void main(String[] args) {
		String user = "admin";
		String pw = "123456";

		String[] credentials = new String[] { user, pw };

		Map<String, String[]> props = new HashMap<String, String[]>();
		props.put("jmx.remote.credentials", credentials);

		JMXServiceURL address;

		try {
			address = new JMXServiceURL(
					"service:jmx:rmi://127.0.0.1/jndi/rmi://127.0.0.1:1099/jmxconnector");
			JMXConnector connector = JMXConnectorFactory.connect(address,
					props);

			MBeanServerConnection mbsc = connector.getMBeanServerConnection();

			connector.connect();

			ObjectName pool_name = new ObjectName(
					"OMS:name=Runtime Status Pool");
			final RuntimeObjectPoolMBean dataMBean = JMX.newMBeanProxy(mbsc,
					pool_name, RuntimeObjectPoolMBean.class);
			System.out.println(dataMBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
