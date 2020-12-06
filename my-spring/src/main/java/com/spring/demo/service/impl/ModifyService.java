package com.spring.demo.service.impl;

import com.spring.demo.service.IModifyService;
import com.spring.framework.annotation.Service;

/**
 * 增删改业务
 * @author Tom
 *
 */
@Service
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	@Override
	public String add(String name, String addr) throws Exception {
		throw new Exception("我爱晓晓！！！");
		//return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	@Override
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	@Override
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
