package com.summer.tech.javase7.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @ClassName: StreamReuse
 * @Description: 使用BufferedInputStream类进行流复用
 * @author 000807
 * @date 2018年5月4日 下午1:58:33
 *
 */
public class StreamReuse {

	private InputStream input;

	public StreamReuse(InputStream input) {
		if(!input.markSupported()) {
			this.input = new BufferedInputStream(input);
		}else {
			this.input = input;
		}
	}

	public InputStream getInputStream() {
		input.mark(Integer.MAX_VALUE);
		return input;
	}

	public void markUsed() throws IOException {
		input.reset();
	}
}
