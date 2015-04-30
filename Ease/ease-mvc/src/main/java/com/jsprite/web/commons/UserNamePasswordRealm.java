/**
 * File Name:UserNamePasswordRealm.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月29日下午8:24:06
 */
package com.jsprite.web.commons;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jsprite.web.model.UserModel;
import com.jsprite.web.service.UserService;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月29日下午8:24:06 <br>
 * E-mail:  <br> 
 */
public class UserNamePasswordRealm extends AuthorizingRealm {

	private UserService userService;
	
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
		String username = (String) principals.getPrimaryPrincipal();
		UserModel vo = new UserModel();
		vo.setUserName(username);
		//从数据库查询
        UserModel user = userService.findUser(vo);
        if(user!=null){
        	SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        	//角色
        	//获取用户角色
        	Set<String> roles = new HashSet<String>();
        	roles.add("admin");
        	authorizationInfo.setRoles(roles);
        	//权限
        	Set<String> permissions = new HashSet<String>();
        	authorizationInfo.setStringPermissions(permissions);
        	return authorizationInfo;
        }else{
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
		String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
		//判断用户名密码是否正确
        UserModel vo = new UserModel();
        vo.setUserName(userName);
        UserModel user = userService.findUser(vo);
        try {
        	String validPassword = new Md5Hash(password, user.getSalt()).toString();
        	if(validPassword.equals(user.getPassword())){
        		return new SimpleAuthenticationInfo(userName, password, getName());
        	}else{
        		throw new AuthenticationException("用户名或密码错误");
        	}
		} catch (Exception e) {
			throw new AuthenticationException(e.getMessage());
		}
			
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
