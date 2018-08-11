package com.summer.tech.javase7.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class NIOBufferDemo {

	public static void main(String[] args) throws Exception {
		NIOBufferDemo demo = new NIOBufferDemo();
		// demo.useByteBuffer();
		// demo.byteOrder();
		// demo.compact();
		// demo.viewBuffer();
		// demo.openAndWrite();
		// demo.readWriteAbsolute();
		// demo.loadWebPage("http://www.baidu.com");
		// demo.mapFile();
		//demo.loadWebPageUseSocket();
		// demo.startSimpleServer();
		// demo.usePath();
		// demo.listFiles();
		// demo.useFileAttributeView();
		// demo.checkUpdateRequired();
		// demo.calculate();
		//demo.addFileToZip(new File("D:\\lixiao\\idfile1.zip"), new File("D:\\lixiao\\idfile22.txt"));
		demo.addFileToZip2(new File("D:\\lixiao\\idfile1.zip"), new File("D:\\lixiao\\newname.txt"));
	}

	public void useByteBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put((byte) 1);
		buffer.put(new byte[3]);
		buffer.putChar('A');
		buffer.putFloat(0.11f);
		buffer.putLong(10, 100L);

		System.out.println(buffer.getChar(4));
	}

	public void byteOrder() {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(1);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println(buffer.getInt(0));
	}

	public void compact() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put(new byte[16]);
		buffer.flip();
		buffer.getInt();
		buffer.compact();
		int pos = buffer.position();
		System.out.println(pos);
	}

	public void viewBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.putInt(1);
		IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.put(2);
		int value = buffer.getInt();
		System.out.println(value);
	}

	public void openAndWrite() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("my.txt"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(64);
		buffer.putChar('A').flip();
		channel.write(buffer);
	}

	public void readWriteAbsolute() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("absolute.txt"),
				StandardOpenOption.CREATE, StandardOpenOption.READ,
				StandardOpenOption.WRITE);
		ByteBuffer writeBuffer = ByteBuffer.allocate(4).putChar('A')
				.putChar('B');
		writeBuffer.flip();
		channel.write(writeBuffer, 1024);
		ByteBuffer readBuffer = ByteBuffer.allocate(2);
		channel.read(readBuffer, 1026);
		readBuffer.flip();
		char result = readBuffer.getChar();
		System.out.println(result);
	}

	// 使用文件通道保存网页内容
	public void loadWebPage(String url) throws IOException {
		try (FileChannel destChannel = FileChannel.open(
				Paths.get("content.txt"), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {
			InputStream input = new URL(url).openStream();
			ReadableByteChannel srcChannel = Channels.newChannel(input);
			destChannel.transferFrom(srcChannel, 0, Integer.MAX_VALUE);
		}
	}

	// 使用字节缓冲区进行文件复制
	public void copyUseByteBuffer() throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);
		try (FileChannel src = FileChannel.open(Paths.get("srcFile.txt"),
				StandardOpenOption.READ)) {
			FileChannel dest = FileChannel.open(Paths.get("descFile.txt"),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			while (src.read(buffer) > 1 || buffer.position() != 0) {
				buffer.flip();
				dest.write(buffer);
				buffer.compact();
			}
		}
	}

	// 使用文件通道进行文件复制
	public void copyUseChannelTransfer() throws IOException {
		try (FileChannel src = FileChannel.open(Paths.get("srcFile.txt"),
				StandardOpenOption.READ)) {
			FileChannel dest = FileChannel.open(Paths.get("descFile.txt"),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			src.transferTo(0, src.size(), dest);
		}
	}

	// 内存映射文件
	public void mapFile() throws IOException {
		try (FileChannel channel = FileChannel.open(Paths.get("mapfile.txt"),
				StandardOpenOption.READ, StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {
			MappedByteBuffer buffer = channel
					.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
			System.out.println(channel.size());
			byte b = buffer.get(1024);
			System.out.println((char) b);
			buffer.put(2 * 1024, (byte) 'B');
			buffer.force();
		}
	}

	// 阻塞式客户端套接字
	public void loadWebPageUseSocket() throws IOException {
		try (FileChannel destChannel = FileChannel.open(Paths.get("socket.txt"),
				StandardOpenOption.READ, StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {
			SocketChannel sc = SocketChannel
					.open(new InetSocketAddress("www.baidu.com", 80));
			String request = "GET / HTTP/1.1\r\n\r\nHost: www.baidu.com\r\n\r\n";
			ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF8"));
			sc.write(header);
			destChannel.transferFrom(sc, 0, Integer.MAX_VALUE);
		}
	}

	// 阻塞式服务端套接字
	public void startSimpleServer() throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.bind(new InetSocketAddress("localhost", 10080));
		while (true) {
			try (SocketChannel sc = channel.accept()) {
				sc.write(ByteBuffer.wrap("Hello".getBytes("UTF8")));
			}
		}
	}

	// Path接口
	public void usePath() {
		Path path1 = Paths.get("folder1", "sub1");
		System.out.println(path1);
		Path path2 = Paths.get("folder2", "sub2");
		System.out.println(path2);
		path1.resolve(path2);
		System.out.println(path2);
		path1.resolveSibling(path2);
		System.out.println(path1.resolveSibling(path2));
		path1.relativize(path2);
		System.out.println(path2);
		path1.subpath(0, 1);
		System.out.println(path1);
		path1.startsWith(path2);
		path1.endsWith(path2);
	}

	// 目录列表流
	public void listFiles() throws IOException {
		Path path = Paths.get(
				"C:\\Users\\000807\\git\\tech_test\\techtest\\src\\main\\java\\com\\summer\\tech\\javase7");
		System.out.println(path.getFileName());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path,
				"*.java")) {
			for (Path entry : stream) {
				System.out.println(entry.getFileName());
			}
		}
	}

	// 删除Subversion元数据的目录遍历方式
	public class SvnInfoCleanVisitor extends SimpleFileVisitor<Path> {
		private boolean cleanMark = false;

		private boolean isSvnFolder(Path dir) {
			return ".svn".equals(dir.getFileName().toString());
		}

		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			if (isSvnFolder(dir)) {
				cleanMark = true;
			}
			return FileVisitResult.CONTINUE;
		}

		public FileVisitResult postVisitDirectory(Path dir, IOException e)
				throws IOException {
			if (e == null && cleanMark) {
				Files.delete(dir);
				if (isSvnFolder(dir)) {
					cleanMark = false;
				}
			}
			return FileVisitResult.CONTINUE;
		}

		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			if (cleanMark) {
				Files.setAttribute(file, "dos：readonly", false);
				Files.delete(file);
			}
			return FileVisitResult.CONTINUE;
		}
	}

	// 文件属性视图
	public void useFileAttributeView() throws IOException {
		Path path = Paths.get("content.txt");
		DosFileAttributeView view = Files.getFileAttributeView(path,
				DosFileAttributeView.class);
		if (view != null) {
			DosFileAttributes attrs = view.readAttributes();
			System.out.println(attrs.isReadOnly());
		}
	}

	// 文件属性视图
	public void checkUpdateRequired() throws IOException {
		Path path = Paths.get("content.txt");
		FileTime lastModifiedTime = (FileTime) Files.getAttribute(path,
				"lastModifiedTime");
		System.out.println(new Timestamp(lastModifiedTime.toMillis()));
	}

	// 目录监视服务
	public void calculate() throws IOException, InterruptedException {
		WatchService service = FileSystems.getDefault().newWatchService();
		Path path = Paths.get("").toAbsolutePath();
		System.out.println(path.toString());
		path.register(service, StandardWatchEventKinds.ENTRY_CREATE);
		while (true) {
			WatchKey key = service.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				Path createPath = (Path) event.context();
				long size = Files.size(createPath);
				System.out.println(createPath + " ==>" + size);
			}
			key.reset();
		}
	}

	// 文件操作实用方法
	public void manipulateFiles() throws IOException {
		Path newFile = Files.createFile(Paths.get("new.txt").toAbsolutePath());
		List<String> content = new ArrayList<String>();
		content.add("Hello");
		content.add("Word");
		Files.write(newFile, content, Charset.forName("UTF8"));
		Files.size(newFile);
		byte[] bytes = Files.readAllBytes(newFile);
		System.out.println(bytes);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Files.copy(newFile, output);
		Files.delete(newFile);
	}

	// 向已有的zip文件添加新文件的传统做法
	public void addFileToZip(File zipFile, File fileToAdd) throws IOException {
		File tempFile = File.createTempFile(zipFile.getName(), null);
		System.out.println(tempFile.getAbsolutePath());
		tempFile.delete();
		zipFile.renameTo(tempFile);
		System.out.println(zipFile.getAbsolutePath());
		try (ZipInputStream input = new ZipInputStream(
				new FileInputStream(tempFile));
				ZipOutputStream output = new ZipOutputStream(
						new FileOutputStream(zipFile))) {
			ZipEntry entry = input.getNextEntry();
			byte[] buf = new byte[8192];
			while (entry != null) {
				String name = entry.getName();
				if (!name.equals(fileToAdd.getName())) {
					output.putNextEntry(new ZipEntry(name));
					int len = 0;
					while ((len = input.read(buf)) > 0) {
						output.write(buf, 0, len);
					}
				}
				entry = input.getNextEntry();
			}
			try (InputStream newFileInput = new FileInputStream(fileToAdd)) {
				output.putNextEntry(new ZipEntry(fileToAdd.getName()));
				int len = 0;
				while ((len = newFileInput.read(buf)) > 0) {
					output.write(buf, 0, len);
				}
				output.closeEntry();
			}
		}
		tempFile.delete();
	}

	// 基于zip/jar文件系统实现的添加新文件到已有zip文件的做法
	public void addFileToZip2(File zipFile, File fileToAdd) throws IOException {
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		try (FileSystem fs = FileSystems
				.newFileSystem(URI.create("jar:" + zipFile.toURI()), env)) {
			Path pathToAddFile = fileToAdd.toPath();
			Path pathInZipfile = fs.getPath("/" + fileToAdd.getName());
			System.out.println(pathInZipfile.toString());
			Files.copy(pathToAddFile, pathInZipfile,
					StandardCopyOption.REPLACE_EXISTING);
		}
	}

	// 向异步文件通道中写入数据
	public void asyncWrite()
			throws IOException, InterruptedException, ExecutionException {
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(
				Paths.get("large.bin"), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(32 * 1024 * 1024);
		Future<Integer> result = channel.write(buffer, 0);
		Integer len = result.get();
		System.out.println(len);
	}

	// 异步套接字通道
	public void startAsyncSimpleServer() throws IOException {
		AsynchronousChannelGroup group = AsynchronousChannelGroup
				.withFixedThreadPool(10, Executors.defaultThreadFactory());
		final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel
				.open(group).bind(new InetSocketAddress(10080));
		serverChannel.accept(null,
				new CompletionHandler<AsynchronousSocketChannel, Void>() {
					public void completed(AsynchronousSocketChannel result,
							Void attachment) {

					}

					@Override
					public void failed(Throwable exc, Void attachment) {

					}
				});
	}

}
