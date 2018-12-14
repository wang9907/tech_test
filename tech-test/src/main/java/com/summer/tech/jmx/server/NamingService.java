package com.summer.tech.jmx.server;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NamingService {
	public NamingService() {
		this(1099);
	}

	public NamingService(int port) {
		setPort(port);
	}

	public void setPort(int port) {
		if (isRunning()) {
			throw new IllegalStateException(
					"NamingService is running, cannot change the port");
		} else {
			m_port = port;
			return;
		}
	}

	public int getPort() {
		return m_port;
	}

	public boolean isRunning() {
		return m_running;
	}

	public void start() throws RemoteException {
		if (!isRunning()) {
			m_registry = LocateRegistry.createRegistry(getPort());
			m_running = true;
		}
	}

	public void stop() throws NoSuchObjectException {
		if (isRunning())
			m_running = !UnicastRemoteObject.unexportObject(m_registry, true);
	}

	public String[] list() throws RemoteException {
		if (!isRunning())
			throw new IllegalStateException("NamingService is not running");
		else
			return m_registry.list();
	}

	public void unbind(String name) throws RemoteException, NotBoundException {
		if (!isRunning()) {
			throw new IllegalStateException("NamingService is not running");
		} else {
			m_registry.unbind(name);
			return;
		}
	}

	private int m_port;
	private Registry m_registry;
	private boolean m_running;
}