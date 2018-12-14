package com.summer.tech.jmx.client.obj;

import java.io.Serializable;
import java.util.Date;
/**
 * 封装运行状态数据
 * @author Administrator
 *
 */
public class RuntimeObject implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static int STATUS_OK = 0; //状态正常
	public static int STATUS_REMIND=1;//提醒
	public static int STATUS_FATAL=-1;//错误
	public static int STATUS_WARNING=-2;//异常警告
	public static int STATUS_TIMEOUT=-3;//超时未报告
	public int    m_expireDuration=0;//不报告则超时
	public String m_addr="";
	public String m_appName="";
	public  int   m_status=0;

	public String m_info="";//记录运行信息
	public long   m_updatedTime=0;//记录更新时间时间
	public long   m_nextExpiredTime=0L;//超时时间，如果超过时间未更新则报错

	//检查当前时间是否超时

	public boolean checkTimeout() {
		if(this.m_expireDuration==0)
			return false;//如果不设置超时则不检查

		Date tm_now=new Date();
		if(tm_now.getTime()>this.m_nextExpiredTime)
			return true;
		else
			return false;

	}

	public String getStatusText()
	{
		if(m_status==STATUS_OK)
			return "正常";
	    if(m_status==STATUS_FATAL)
			return "错误";
		if(m_status==STATUS_WARNING)
			return "异常警告";
		if(m_status==STATUS_TIMEOUT)
			return "超时未报告";
		return "未知状态";
	}

}

