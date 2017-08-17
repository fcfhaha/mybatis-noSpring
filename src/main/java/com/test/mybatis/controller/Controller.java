/**
 * 
 */

package com.test.mybatis.controller;

import java.util.Map;

import com.delta.ams.common.util.RequestUtil;
import com.test.mybatis.entity.User;
import com.test.mybatis.service.UserServiceImpl;

/**
 * @author V.Caifeng.Fan
 * @ClassName Controller
 * @Description: TODO
 * @date 2017年8月17日 上午9:32:21
 */
public class Controller {

	UserServiceImpl impl = new UserServiceImpl();

	public User saveLineConfig(Map<String, String[]> param) {

		User user = null;
		try {
			user = RequestUtil.getFirstUrlDefParam(param, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return impl.insert(user);
	}

}
