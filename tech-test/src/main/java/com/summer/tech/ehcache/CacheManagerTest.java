package com.summer.tech.ehcache;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.MemoryUnit;

import org.junit.Test;

public class CacheManagerTest {

	@Test
	public void testDefault() {
		CacheManager cacheManager = new CacheManager();
		// 输出当前cacheManager正在使用的配置对应的Xml格式文本
		System.out.println(cacheManager.getActiveConfigurationText());
	}

	@Test
	public void test() {
		// 新建一个CacheManager的配置信息
		Configuration configuration = new Configuration();
		// 新建一个缓存的配置信息
		CacheConfiguration cacheConfiguration = new CacheConfiguration()
				.name("test");
		// 指定当前缓存的最大堆内存值为100M
		cacheConfiguration.maxBytesLocalHeap(100, MemoryUnit.MEGABYTES);
		// 添加一个cache
		configuration.addCache(cacheConfiguration);
		configuration.dynamicConfig(false); // 不允许动态修改配置信息
		CacheManager cacheManager = new CacheManager(configuration);
		Cache cache = cacheManager.getCache("test");
		cache.put(new Element("test", "test"));
		System.out.println(cache.get("test").getObjectValue());
	}

	public void testInputStream() throws IOException {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("/ehcache.xml");
		CacheManager cacheManager = new CacheManager(is);
		is.close();
		System.out.println(cacheManager.getActiveConfigurationText());
	}

	@Test
	public void testXmlPath() {
		// 这个文件路径可以是相对路径，也可以是绝对路径。这里使用的是相对路径。
		CacheManager cacheManager = new CacheManager(
				"src/main/resources/ehcache/ehcache.xml");
		System.out.println(cacheManager.getActiveConfigurationText());
	}

	@Test
	public void testURL() {
		URL url = this.getClass().getResource("/ehcache.xml");
		CacheManager cacheManager = new CacheManager(url);
		System.out.println(cacheManager.getActiveConfigurationText());
	}

	public void test1() {
		// 以默认配置创建一个CacheManager单例
		CacheManager cacheManager = CacheManager.create();

		// 以config对应的配置创建CacheManager单例
		Configuration config = new Configuration();// 以某种方式获取的Configuration对象
		cacheManager = CacheManager.create(config);

		// 以configurationFileName对应的xml文件定义的配置创建CacheManager单例
		String configurationFileName = "";// xml配置文件对应的文件名称，包含路径
		cacheManager = CacheManager.create(configurationFileName);

		// 以is对应的配置信息创建CacheManager单例
		InputStream is = this.getClass().getResourceAsStream(""); // 以某种方式获取到的Xml配置信息对应的输入流
		cacheManager = CacheManager.create(is);

		// 以URL对应的配置信息创建CacheManager单例
		URL url = this.getClass().getResource(""); // 以某种方式获取到的Xml配置信息对应的URL
		cacheManager = CacheManager.create(url);
	}

	public void test3() {
		// 以默认配置创建一个CacheManager
		CacheManager cacheManager = CacheManager.newInstance();

		// 以config对应的配置创建CacheManager
		Configuration config = new Configuration();// 以某种方式获取的Configuration对象
		cacheManager = CacheManager.newInstance(config);

		// 以configurationFileName对应的xml文件定义的配置创建CacheManager
		String configurationFileName = "";// xml配置文件对应的文件名称，包含路径
		cacheManager = CacheManager.newInstance(configurationFileName);

		// 以is对应的配置信息创建CacheManager
		InputStream is = this.getClass().getResourceAsStream(""); // 以某种方式获取到的Xml配置信息对应的输入流
		cacheManager = CacheManager.newInstance(is);

		// 以URL对应的配置信息创建CacheManager
		URL url = this.getClass().getResource(""); // 以某种方式获取到的Xml配置信息对应的URL
		cacheManager = CacheManager.newInstance(url);
	}

	@Test
	public void test4() {
		CacheManager cacheManager = CacheManager.create();
		// 以默认配置添加一个名叫cacheName的Cache。
		cacheManager.addCache("cacheName");
		Cache cache = cacheManager.getCache("cacheName");
		Element ele = new Element("key", "value");
		// 把ele放入缓存cache中
		cache.put(ele);
	}

	@Test
	public void cache() {
		// 内存中保存的Element的最大数量
		int maxEntriesLocalHeap = 10000;
		CacheConfiguration cacheConfiguration = new CacheConfiguration(
				"cacheName", maxEntriesLocalHeap);
		cacheConfiguration.overflowToOffHeap(false);
		Cache cache = new Cache(cacheConfiguration);
		// 使用默认配置创建CacheManager
		CacheManager cacheManager = CacheManager.create();
		// 只有添加到CacheManager中的Cache才是有用的
		cacheManager.addCache(cache);
		cache.put(new Element("key", "value"));
		System.out.println(cache.get("key"));
	}

	@Test
	public void cache2() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("test"); // 指定cache名称
		cacheConfiguration.setMaxBytesLocalHeap("10M"); // 指定最大可用堆内存
		Configuration config = new Configuration(); // 构建一个空配置
		// 添加Cache配置信息到CacheManager的配置信息中
		config.addCache(cacheConfiguration);
		CacheManager cacheManager = CacheManager.create(config);
		System.out.println(cacheManager.getOriginalConfigurationText());
		Cache cache = cacheManager.getCache("test");
		cache.put(new Element("key", "value"));
	}
}
