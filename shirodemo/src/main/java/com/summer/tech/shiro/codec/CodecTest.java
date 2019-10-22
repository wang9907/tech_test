/**
  * @Title: CodecTest.java
  * @Package com.summer.tech.shiro.codec
  * @Description:
  * @author 000807
  * @date 2019年9月23日
  * @version V1.0
  */

package com.summer.tech.shiro.codec;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.H64;
import org.apache.shiro.codec.Hex;

/**
 * @ClassName: CodecTest
 * @Description:
 * @author 000807
 * @date 2019年9月23日 下午4:17:20
 *
 */
public class CodecTest {

	public static void main(String[] args) {
		String str = "hello";
		String encode = Base64.encodeToString(str.getBytes());
		System.out.println(encode);
		String decode = Base64.decodeToString(encode);
		System.out.println(decode);

		String encodehex = Hex.encodeToString(str.getBytes());
		System.out.println(encodehex);
		String decodehex = new String(Hex.decode(encodehex));
		System.out.println(decodehex);

		String en64 = H64.encodeToString(str.getBytes());
		System.out.println(en64);
	}

}
