package com.cmcc.slience.senior.adaptor.def;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月9日下午2:34:55 <br>
 * E-mail: <br>
 *
 */
public abstract class UserNameServiceImpl implements UserService {

	@Override
	public String getUserName() {
		return "缺省适配器";
	}

	@Override
	public int getUserAge() {
		return 0;
	}

	@Override
	public String getUserSex() {
		return null;
	}

	@Override
	public String getUserWeight() {
		return null;
	}

}
