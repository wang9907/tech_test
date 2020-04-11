package com.summer.tech.spring.di.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class ResourceLoaderTest {

	public static void main(String[] args) throws IOException {

	}

	public void testResourceLoad1() {
		ResourceLoader loader = new DefaultResourceLoader();

		Resource resource = loader.getResource("classpath:test.properties");
		// 验证返回的是ClassPathResource
	}

	public void testResourceLoad2() {
		ResourceLoader loader = new DefaultResourceLoader();

		Resource resource2 = loader.getResource("file:test.properties");
		// 验证返回的是ClassPathResource
	}

	public void testResourceLoad3() {
		ResourceLoader loader = new DefaultResourceLoader();

		Resource resource3 = loader.getResource("test.properties");
		// 验证返默认可以加载ClasspathResource
	}

	public void testClasspathPrefix() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 只加载一个绝对匹配Resource，且通过ResourceLoader.getResource进行加载
		Resource[] resources = resolver
				.getResources("classpath:META-INF/INDEX.LIST");
		// 只加载一个匹配的Resource，且通过ResourceLoader.getResource进行加载
		resources = resolver.getResources("classpath:META-INF/*.LIST");
	}

	public void testClasspathAsteriskPrefix() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 将加载多个绝对匹配的所有Resource
		// 将首先通过ClassLoader.getResources("META-INF")加载非模式路径部分
		// 然后进行遍历模式匹配
		Resource[] resources = resolver
				.getResources("classpath*:META-INF/INDEX.LIST");
		// 将加载多个模式匹配的Resource
		resources = resolver.getResources("classpath*:META-INF/*.LIST");
	}

    public void testClasspathAsteriskPrefixLimit() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();      //将首先通过ClassLoader.getResources("")加载目录，
        //将只返回文件系统的类路径不返回jar的跟路径
        //然后进行遍历模式匹配
        Resource[] resources = resolver.getResources("classpath*:asm-*.txt");
        //将通过ClassLoader.getResources("asm-license.txt")加载
        //asm-license.txt存在于com.springsource.net.sf.cglib-2.2.0.jar
        resources = resolver.getResources("classpath*:asm-license.txt");
        //将只加载文件系统类路径匹配的Resource
        resources = resolver.getResources("classpath*:LICENS*");
    }
}