/*
 * Delta CONFIDENTIAL
 *
 * (C) Copyright Delta Electronics, Inc. 2016 All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the
 * property of Delta Electronics. The intellectual and technical
 * concepts contained herein are proprietary to Delta Electronics
 * and are protected by trade secret, patent law or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Delta Electronics.
 */

package com.test.mybatis.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.test.mybatis.base.BaseService;

import com.test.mybatis.dao.IUserDao;
import com.test.mybatis.entity.User;

/**
 * @author V.Caifeng.Fan
 *
 */
public class UserServiceImpl extends BaseService<User, IUserDao> {

	// 扩展接口的方法
	public void deleteUser(int id) {

		SqlSession session = getSqlSessionFactory().openSession();
		try {
			IUserDao dao = session.getMapper(IUserDao.class);
			dao.deleteByPrimary(id);
			session.commit();

		} catch (Exception e1) {
			// todo
			e1.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void main(String[] args) throws SQLException {

		UserServiceImpl testImpl = new UserServiceImpl();
		User user = new User();
		user.setAge(13);
		user.setName("ooo");
		testImpl.deleteUser(11);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("name", "ddd");
		conditions.put("age", 0);
		// testImpl.insert(user);
		user.setId(10);
		// testImpl.updateByMapConditions(user, conditions, IUserDao.class);
		// testImpl.updateByPrimary(user);
		List<User> selectByEs = testImpl.selectByEs(user);
		// List<User> selectByEs = testImpl.selectByPageCondition(user,1,2,
		// IUserDao.class);
		// List<User> selectByEs = testImpl.selectAll(IUserDao.class);
		// long countByMapConditions = testImpl.deleteByPrimary(new long[] { 4,
		// 6 }, IUserDao.class);
		System.out.println(selectByEs);

	}
}
