/**
  * @Title: HashTest.java
  * @Package com.summer.tech.shiro.hash
  * @Description:
  * @author 000807
  * @date 2019年9月17日
  * @version V1.0
  */

package com.summer.tech.shiro.hash;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @ClassName: HashTest
 * @Description:
 * @author 000807
 * @date 2019年9月17日 下午8:03:21
 *
 */

public class HashTest {

	public static void main(String[] args) {
		// md5加密，不加盐
		String password_md5 = new Md5Hash("111111").toString();
		System.out.println("md5加密，不加盐=" + password_md5);

		// md5加密，加盐，一次散列
		String password_md5_sale_1 = new Md5Hash("123", "guokang", 1).toString();
		System.out.println("md5加密，加盐，一次散列=" + password_md5_sale_1);

		String password_md5_sale_2 = new Md5Hash("111111", "kangguo", 1).toString();
		System.out.println("md5加密，加盐，一次散列=" + password_md5_sale_2);

		// 使用SimpleHash
		String simpleHash = new SimpleHash("MD5", "111111", "guokang", 1).toString();
		System.out.println(simpleHash);
	}
}
