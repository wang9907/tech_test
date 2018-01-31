package com.summer.tech.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferTest {

	public static void main(String[] args) {
		RandomAccessFile fromFile;
		try {
			fromFile = new RandomAccessFile("fromFile.txt", "rw");
			FileChannel fromChannel = fromFile.getChannel();

			RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
			FileChannel toChannel = toFile.getChannel();

			long position = 0;
			long count = fromChannel.size();

			toChannel.transferFrom(fromChannel, position, count);

			toFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		 * FileChannel fromChannel = fromFile.getChannel();
		 *
		 * RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		 * FileChannel toChannel = toFile.getChannel();
		 *
		 * long position = 0; long count = fromChannel.size();
		 *
		 * fromChannel.transferTo(position, count, toChannel);
		 */

	}

}
