package com.summer.tech.javase7.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LangDemo {

	public static void main(String[] args) {
		// stringIntern();
		numberCache();
	}

	// 字符串内部化
	public static void stringIntern() {
		boolean value1 = "Hello" == "Hello";
		boolean value2 = (new String("Hello") == "Hello");
		boolean value3 = (new String("Hello").intern() == "Hello");
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
	}

	// Java 7把这种内部化机制扩大到了128到127之间的数字。根据Java语言规范,对于128到127范围内的short类型和int类型,
	// 以及\u0000到\u007f范围内的char类型,它们对应的包装类对象始终指向相同的对象,即通过“==”进行判断时的结果为true。
	// 为了满足这个要求,Byte、Short、Integer类的valueOf方法对于128到127范围内的值,以及Character类的valueOf方法对于0到127范围内的值,都会返回内部缓存的对象。
	// 如果希望缓存更多的值,可以通过Java虚拟机启动参数“java.lang.Integer.IntegerCache.high”来进行设置。例如,使用“Djava.lang.Integer.IntegerCache.high=256”之后,
	// 数值缓存的范围就变成了128到256,再次运行代码清单66会发现,value2的值变成true,因为129处于修改之后的缓存范围之内。
	public static void numberCache() {
		boolean value1 = Integer.valueOf(3) == Integer.valueOf(3);
		boolean value2 = Integer.valueOf(129) == Integer.valueOf(129);
		System.out.println(value1);
		System.out.println(value2);
	}

	// 创建进程的示例
	// java7之前只支持管道式的方式
	public static void startProcessNormal() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "netstat", "a");
		Process process = pb.start();
		InputStream input = process.getInputStream();
		Files.copy(input, Paths.get("netstat.txt"),
				StandardCopyOption.REPLACE_EXISTING);
	}

	// 进程的输入和输出的继承式处理方式的示例
	public static void dir() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir");
		pb.redirectOutput(Redirect.INHERIT);
		pb.start();
	}

	// 　进程的输入和输出的文件式处理方式的示例
	public static void listProcesses() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("wmic", "process");
		File output = Paths.get("tasks.txt").toFile();
		pb.redirectOutput(output);
		pb.start();
	}
}
