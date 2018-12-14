package com.summer.tech.jmx.client.obj;

import java.util.List;

public interface RuntimeObjectPoolMBean {
	
	public void printGreeting(String val);
	
	public void updateInfo(String addr, String app, String info);
	
	public List<RuntimeObject> getRuntimeObjectList(); 	
	
	public void reportAlive(String addr, String app);
	
	public void reportTimeout(String addr, String app);
	
	public void reportFatal(String addr, String app, String info);
	
	public void reportInfo(String addr, String app, String info);
	
	public List<RuntimeLog> queryRuntimeLogList(String addr, String app);
	
	
}