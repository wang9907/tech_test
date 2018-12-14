package com.summer.tech.jmx.client.obj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.management.NotificationBroadcasterSupport;

import org.apache.log4j.Logger;

public class RuntimeObjectPool extends NotificationBroadcasterSupport
		implements RuntimeObjectPoolMBean {
	private static Logger logger = Logger
			.getLogger(RuntimeObjectPool.class.getName());
	private Map<String, Object> m_runtimeObjects = new TreeMap<String, Object>();

	public void initRuntimeObject(String addr, String app, int expire_dur) {

		if (this.getRuntimeObject(addr, app) != null)
			return;
		String key = addr + ":" + app;

		RuntimeObject rt_obj = new RuntimeObject();

		rt_obj.m_addr = addr;
		rt_obj.m_appName = app;
		rt_obj.m_info = "";
		Date tm_now = new Date();
		rt_obj.m_expireDuration = expire_dur;
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;// 下次需要更新的时间
		this.m_runtimeObjects.put(key, rt_obj);

	}

	public RuntimeObject getRuntimeObject(String addr, String app) {
		String key = addr + ":" + app;

		if (m_runtimeObjects.containsKey(key) == false)
			return null;

		return (RuntimeObject) m_runtimeObjects.get(key);

	}

	public void printGreeting(String val) {
		System.out.println("OUTPUT:" + val);
	}

	public void updateInfo(String addr, String app, String info) {

		if (this.getRuntimeObject(addr, app) == null) {
			System.out.println("The entry is not exist");
			return;
		}

		RuntimeObject rt_obj = this.getRuntimeObject(addr, app);

		rt_obj.m_info = info;
		Date tm_now = new Date();
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;// 更新超时时间

		String key = addr + ":" + app;
		m_runtimeObjects.put(key, rt_obj);

		System.out.println("Update runtime " + key + " " + info);
		insert(rt_obj);
	}

	/**
	 * 正常心跳报告
	 *
	 * @param addr
	 * @param app
	 */
	public void reportAlive(String addr, String app) {

		RuntimeObject rt_obj = this.getRuntimeObject(addr, app);
		Date tm_now = new Date();
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;
		rt_obj.m_info = "";
		rt_obj.m_status = RuntimeObject.STATUS_OK;// 状态为正常
		String key = addr + ":" + app;
		this.m_runtimeObjects.put(key, rt_obj);
		insert(rt_obj);
	}

	/**
	 * 超时未更新
	 *
	 * @param addr
	 * @param app
	 */

	public void reportTimeout(String addr, String app) {
		logger.info("开始reportTimeout：" + addr + "   " + app);
		RuntimeObject rt_obj = this.getRuntimeObject(addr, app);
		rt_obj.m_status = RuntimeObject.STATUS_TIMEOUT;
		Date tm_now = new Date();
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;
		String key = addr + ":" + app;
		this.m_runtimeObjects.put(key, rt_obj);
		insert(rt_obj);
		logger.info("结束reportTimeout：" + addr + "   " + app);
	}

	/**
	 * 报告错误
	 *
	 * @param addr
	 * @param app
	 * @param info
	 */

	public void reportFatal(String addr, String app, String info) {

		RuntimeObject rt_obj = this.getRuntimeObject(addr, app);
		Date tm_now = new Date();
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;
		rt_obj.m_status = RuntimeObject.STATUS_FATAL;// 状态为错误
		rt_obj.m_info = info;
		String key = addr + ":" + app;
		this.m_runtimeObjects.put(key, rt_obj);
		insert(rt_obj);
	}

	/**
	 * 反馈信息
	 */

	public void reportInfo(String addr, String app, String info) {

		RuntimeObject rt_obj = this.getRuntimeObject(addr, app);
		Date tm_now = new Date();
		rt_obj.m_updatedTime = tm_now.getTime();
		rt_obj.m_nextExpiredTime = rt_obj.m_updatedTime
				+ rt_obj.m_expireDuration;

		rt_obj.m_info = info;
		String key = addr + ":" + app;
		this.m_runtimeObjects.put(key, rt_obj);
		insert(rt_obj);
	}

	public List<RuntimeObject> getRuntimeObjectList() {
		List<RuntimeObject> obj_lst = new ArrayList<RuntimeObject>();
		Set<String> keys = this.m_runtimeObjects.keySet();
		for (String key : keys) {
			RuntimeObject obj = (RuntimeObject) m_runtimeObjects.get(key);
			obj_lst.add(obj);
		}
		return obj_lst;
	}

	/**
	 * 返回指定地址和应用的日志记录
	 *
	 * @param addr
	 * @param app
	 * @return
	 */
	public List<RuntimeLog> queryRuntimeLogList(String addr, String app) {
		return null;
	}

	// 插入数据库日志
	void insert(RuntimeObject runtimeObject) {

	}
}