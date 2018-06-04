package com.summer.tech.network.tcpchat;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatMap<K,V> {

	public Map<K,V> map = Collections.synchronizedMap(new HashMap<K,V>());
	public synchronized void removeByValue(Object value){
		for(Object key:map.keySet()){
			if(map.get(key) == value){
				map.remove(value);
			}
		}
	}
	
	public synchronized Set<V> valueSet(){
		Set<V> result = new HashSet<V>();
		//遍历Map,将Map的Value存入set
		for(K key:map.keySet()){
			result.add(map.get(key));
		}
		return result;
	}
	
	public synchronized K getKey(V val){
		for(K key:map.keySet()){
			if(map.get(key)==val || map.get(key).equals(val)){
				return key;
			}
		}
		return null;
	}
}
