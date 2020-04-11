package com.summer.tech.spring.di.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathResourceTest {
	public static void main(String[] args) throws IOException {

		Resource resource = new ClassPathResource("test.properties");
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path:" + resource.getFile().getAbsolutePath());
		System.out.println(resource.isOpen());
	}

	private static void dumpStream(Resource resource) {
		InputStream is = null;
		try {
			// 1.获取文件资源
			is = resource.getInputStream();
			// 2.读取资源
			byte[] descBytes = new byte[is.available()];
			is.read(descBytes);
			System.out.println(new String(descBytes));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 3.关闭资源
				is.close();
			} catch (IOException e) {
			}
		}
	}

    public void testClasspathResourceByClassLoader() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        Resource resource = new ClassPathResource("test.properties",cl);
        if(resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
    }
}
