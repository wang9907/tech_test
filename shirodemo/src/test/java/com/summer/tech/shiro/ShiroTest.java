/**
  * @Title: TestLoginLogout.java
  * @Package com.summer.tech.shiro
  * @Description:
  * @author 000807
  * @date 2019年9月17日
  * @version V1.0
  */

package com.summer.tech.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @ClassName: TestLoginLogout
 * @Description:
 * @author 000807
 * @date 2019年9月17日 上午11:07:22
 *
 */

public class ShiroTest {

	@Test
	public void testLoginLogout() {
		// 构建SecurityManager工厂，IniSecurityManager可以从ini文件中初始化SecurityManager环境
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-crypto.ini");
		// 通过工厂创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		// 将securityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		// 创建一个Subject实例，该实例认证要使用上面创建的securityManager
		Subject subject = SecurityUtils.getSubject();
		// 创建token令牌，记录用户认证的身份和凭证即账号和密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("用户认证状态：" + isAuthenticated);

		subject.logout();

		isAuthenticated = subject.isAuthenticated();
		System.out.println("用户认证状态：" + isAuthenticated);

	}
}
