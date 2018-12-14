package com.summer.tech.jmx.client.obj;

import java.io.Serializable;
import java.util.Date;
/**
 * ��װ����״̬����
 * @author Administrator
 *
 */
public class RuntimeLog implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String m_addr="";
	public String m_appName="";
	public String  m_status="";//����Ҫ����
	public String m_info="";//��¼������Ϣ
	public long   m_createTime=0;//��¼����ʱ��ʱ��


	
}
