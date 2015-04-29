/**
 * File Name:UserNamePasswordRealm.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月29日下午8:24:06
 */
package com.jsprite.web.commons;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月29日下午8:24:06 <br>
 * E-mail:  <br> 
 */
public class UserNamePasswordRealm extends AuthorizingRealm {

	/**方法名称：doGetAuthorizationInfo <br>
	 * 描述： 授权  <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月29日下午8:24:07 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();  
        //User user = personDAO.getUser(userId);
		Object user = null;
        if( user != null ) {  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            /*for(Role role : user.getRoles() ) {  
                info.addRole(role.getName());  
                Set<Perms> set= role.getPermissions();  
                logger.info(set);  
                for(Perms perm:set){  
                    info.addStringPermission(perm.getActionName());  
                }  
            }  */
            return info;  
        } else {  
            return null;  
        }  
	}

	/**方法名称：doGetAuthenticationInfo <br>
	 * 描述： 身份验证 <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月29日下午8:24:07 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	 * @param arg0
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = token.getPassword().toString();
		//判断用户名密码是否正确
		if (isRight()) {
			return new SimpleAuthenticationInfo("id", password, getName());
		} else {
			return null;
		}
	}
	
	/**
	 * 方法名称: isRight<br>
	 * 描述：用户是否正确	<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2015年4月29日下午8:33:54<br/>
	 * @return
	 */
	private boolean isRight(){
		return true;
	}

}
