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

package org.test.mybatis.base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author V.Caifeng.Fan
 * @param <E>
 *            db entity
 * @param <I>
 *            dao interface
 *
 */
public class BaseService<E, I extends IBaseDao<E>> {

	// private static final String resource = "mybatis_config.xml";
	private static final String resource = "mybatis_config_druid.xml";

	private Class daoInterfaceClass;

	private SqlSessionFactory sqlSessionFactory;

	protected BaseService() {

		initDaoInterfaceClass();
		initSqlSessionFactory();
	}

	private void initDaoInterfaceClass() {

		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		daoInterfaceClass = (Class) params[1];
	}

	private void initSqlSessionFactory() {

		InputStream inputStream = null;
		if (sqlSessionFactory == null) {
			synchronized (SqlSessionFactory.class) {
				if (sqlSessionFactory == null) {
					try {
						inputStream = Resources.getResourceAsStream(resource);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				}
			}
		}
	}

	/**
	 * insert entity
	 * 
	 * @param <T>
	 * 
	 * @param e
	 *            entity instance
	 * 
	 * @return instance which has an id value
	 */

	@SuppressWarnings("unchecked")
	public <T> E insert(E e) {

		IBaseDao<E> dao = null;
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println(sqlSessionFactory);
		try {
			dao = session.getMapper(daoInterfaceClass);
			dao.insert(e);
			session.commit();

		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return e;
	}

	/**
	 * insert some entity
	 * 
	 * @param data
	 *            entity list which will insert into db
	 * @param clazz
	 *            class of dao interface
	 * @return insert num
	 */
	@SuppressWarnings("unchecked")
	public List<E> batchInsert(List<E> data) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		List<E> retList = new ArrayList<E>();
		try {
			dao = session.getMapper(daoInterfaceClass);
			for (E e2 : data) {
				dao.insert(e2);
				retList.add(e2);
			}
			session.commit();

		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retList;

	}

	/**
	 * update some data
	 * 
	 * @param e
	 *            new entity data
	 * @param conditions
	 *            update condition ---> where
	 * @param clazz
	 *            class of dao interface
	 * @return update nummber
	 */
	@SuppressWarnings("unchecked")
	public int updateByMapConditions(E e, Map<String, Object> conditions) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		int updateNum = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			updateNum = dao.updateByMapConditions(e, conditions);
			session.commit();

		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return updateNum;
	}

	/**
	 * @param e
	 *            new entity data and contains primary id
	 * @param clazz
	 *            class of dao interface
	 * @return update nummber
	 */
	@SuppressWarnings("unchecked")
	public int updateByPrimary(E e) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		int updateNum = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			updateNum = dao.updateByPrimary(e);
			session.commit();

		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return updateNum;
	}

	/**
	 * @param e
	 *            query conditions entity
	 * @return query result entity list
	 * @param clazz
	 *            class of dao interface
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<E> selectByEs(E e) throws SQLException {

		SqlSession session = sqlSessionFactory.openSession();
		// DataSource ds =
		// session.getConfiguration().getEnvironment().getDataSource();
		// if (ds instanceof DruidDataSource) {
		// System.out.println(ds.getConnection());
		// // System.out.println("Yes");
		// System.out.println(((DruidDataSource) ds).getMaxActive());
		// System.out.println(((DruidDataSource) ds).getMaxIdle());
		// } else {
		// System.out.println("No");
		// }

		IBaseDao<E> dao = null;
		List<E> retList = new ArrayList<E>();
		try {
			dao = session.getMapper(daoInterfaceClass);
			retList = dao.selectByEs(e);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retList;
	}

	/**
	 * @param conditions
	 *            map
	 * @param clazz
	 *            class of dao interface
	 * @return query result entity list
	 */

	@SuppressWarnings("unchecked")
	public List<E> selectByMapConditions(Map<String, Object> conditions) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		List<E> retList = new ArrayList<E>();
		try {
			dao = session.getMapper(daoInterfaceClass);
			retList = dao.selectByMapConditions(conditions);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retList;
	}

	/**
	 * query by primary id
	 * 
	 * @param primary
	 * @param clazz
	 *            class of dao interface
	 * @return query result
	 */
	@SuppressWarnings("unchecked")
	public E selectByPrimary(long primary) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		E retE = null;
		try {
			dao = session.getMapper(daoInterfaceClass);
			retE = dao.selectByPrimary(primary);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retE;
	}

	/**
	 * query by pagecondition
	 * 
	 * @param e
	 * @param pageNow
	 * @param pageSize
	 * @param clazz
	 *            class of dao interface
	 * @return page result entity
	 */

	@SuppressWarnings("unchecked")
	public List<E> selectByPageCondition(E e, int pageNow, int pageSize) {

		int offset = 0;
		if (pageNow > 0) {
			offset = (pageNow - 1) * pageSize;
		}
		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		List<E> retList = new ArrayList<E>();
		try {
			dao = session.getMapper(daoInterfaceClass);
			retList = dao.selectByPageCondition(e, offset, pageSize);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retList;
	}

	/**
	 * select All data
	 * 
	 * @param clazz
	 *            class of dao interface
	 * @return result list
	 */
	@SuppressWarnings("unchecked")
	public List<E> selectAll() {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		List<E> retList = new ArrayList<E>();
		try {
			dao = session.getMapper(daoInterfaceClass);
			retList = dao.selectAll();
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return retList;
	}

	/**
	 * @param clazz
	 *            class of dao interface
	 * @param conditions
	 *            count condition
	 * @return data nummber
	 */
	@SuppressWarnings("unchecked")
	public long countByMapConditions(Map<String, Object> conditions) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		long count = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			count = dao.countByMapConditions(conditions);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * @param clazz
	 *            class of dao interface
	 * @param e
	 *            count condition entity
	 * @return data nummber
	 */
	@SuppressWarnings("unchecked")
	public long countByE(E e) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		long count = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			count = dao.countByE(e);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * @param conditions
	 *            delete conditions
	 * @param clazz
	 *            class of dao interface
	 * @return delete nummber
	 */
	@SuppressWarnings("unchecked")
	public int deleteByMapContitions(Map<String, Object> conditions) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		int delNum = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			delNum = dao.deleteByMapContitions(conditions);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return delNum;
	}

	/**
	 * @param e
	 *            delete conditions
	 * @param clazz
	 *            class of dao interface
	 * @return delete nummber
	 */
	@SuppressWarnings("unchecked")
	public int deleteByE(E e) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		int delNum = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			delNum = dao.deleteByE(e);
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return delNum;
	}

	/**
	 * @param ids
	 * @param clazz
	 *            class of dao interface
	 * @return delete nummber
	 */
	@SuppressWarnings("unchecked")
	public int deleteByPrimary(long[] ids) {

		SqlSession session = sqlSessionFactory.openSession();
		IBaseDao<E> dao = null;
		int delNum = 0;
		try {
			dao = session.getMapper(daoInterfaceClass);
			for (int i = 0; i < ids.length; i++) {
				delNum += dao.deleteByPrimary(ids[i]);
			}
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			e1.printStackTrace();
		} finally {
			session.close();
		}
		return delNum;
	}

	/**
	 * @return the daoInterfaceClass
	 */
	public Class getDaoInterfaceClass() {

		return daoInterfaceClass;
	}

	/**
	 * @return the sqlSessionFactory
	 */
	public SqlSessionFactory getSqlSessionFactory() {

		return sqlSessionFactory;
	}

}
