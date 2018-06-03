package com.summer.tech.network.chat;

import java.net.SocketAddress;

public class UserInfo {

	// 图标
	private String icon;
	// 名字
	private String name;
	// MulticastSocket地址
	private SocketAddress address;
	// 失去联系的次数
	private int lost;
	// 交谈窗口
	private ChatFrame charFrame;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SocketAddress getAddress() {
		return address;
	}

	public void setAddress(SocketAddress address) {
		this.address = address;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public ChatFrame getCharFrame() {
		return charFrame;
	}

	public void setCharFrame(ChatFrame charFrame) {
		this.charFrame = charFrame;
	}

	public int hashCode() {
		return address.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == UserInfo.class) {
			return ((UserInfo) obj).getAddress().equals(address);
		}
		return false;
	}

}
