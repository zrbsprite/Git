package com.cmcc.slience.senior.adaptor.def;

public class UserAdapter extends UserNameServiceImpl {

	@Override
	public String getUserName() {
		return "我是缺省适配器，只对关心的接口予以实现，不关心的采用缺省实现方式";
	}
	
	@Override
	public int getUserAge() {
		return 23;
	}
}
