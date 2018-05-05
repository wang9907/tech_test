package com.summer.tech.javase7.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class SvnInfoCleanVisitor extends SimpleFileVisitor<Path> {

	private boolean cleanMark = false;

	private boolean isSvnFolder(Path dir) {
		return ".svn".equals(dir.getFileName().toString());
	}

	public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs) {
		if(isSvnFolder(dir)) {
			cleanMark = true;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		if(cleanMark) {
			Files.setAttribute(file, "dos:readonly", false);
			Files.delete(file);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e)
			throws IOException {
		if(e == null && cleanMark) {
			Files.delete(dir);
			if(isSvnFolder(dir)) {
				cleanMark = false;
			}
		}
		return FileVisitResult.CONTINUE;
	}


}
