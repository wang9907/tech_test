/**
  * @Title: CustomRealm.java
  * @Package com.summer.tech.shiro.realm
  * @Description:
  * @author 000807
  * @date 2019年9月17日
  * @version V1.0
  */

package com.summer.tech.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: CustomRealm
 * @Description:
 * @author 000807
 * @date 2019年9月17日 下午6:33:43
 *
 */

public class CustomHashRealm extends AuthorizingRealm {

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public String getName() {
		return "customHashRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户身份信息
		String username = (String) token.getPrincipal();
		// 获取从数据库查询出来的用户密码
		String password = "";// 123+guokang的md5值
		//盐，随机数，此随机数也在数据库存储
		String salt = "guokang";
		// 返回认证信息由父类AuthenticationRealm进行认证
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password,ByteSource.Util.bytes(salt), getName());

		return authenticationInfo;
	}

}
