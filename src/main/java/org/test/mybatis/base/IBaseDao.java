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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author V.Caifeng.Fan 
 * @param <E> db entity
 *
 */
public interface IBaseDao<E> {
	 
    /**
     * insert  entity
     * 
     * @param e  entity instance 
     *           
     *  instance which has an id value
     */
    public void insert(E e);
 
   /* *//**
     * insert some entity
     * 
     * @param data  entity list which will insert into db
     *            
     * @return insert num
     *//*
    public int batchInsert(List<E> data);*/
 
 
    /**
     * update some data
     * @param e  new entity data
     * @param conditions  update condition ---> where 
     * @return  update nummber
     */
    public int updateByMapConditions(@Param("setValue")E e, @Param("condition")Map<String, Object> conditions);
 
    /**
     * @param e new entity data and contains primary id
     * @return update nummber
     */
    public int updateByPrimary(E e);
    
    /**
     * @param e query conditions entity
     * @return  query result entity list
     */
    public List<E> selectByEs(E e);
 
    /**
     * @param query conditions map
     *            
     * @return query result entity list
     */
    
    public List<E> selectByMapConditions(Map<String, Object> conditions);
 
    /**
     * query by primary id
     * 
     * @param primary
     *          
     * @return query result
     */
    public E selectByPrimary(long primary);
    
    /**
     *  query by page   limit offset,pagesize
     * @param e  query condition
     * @param offset   
     * @param pagesize 
     * @return page result data
     */
  
    public List <E> selectByPageCondition(@Param("condition")E e,@Param("offset")int offset,@Param("pagesize")int pagesize );
 
    /**
     * select All data
     * 
     * @return result list
     */
    public List<E> selectAll();
 
    /**
     * @param conditions  count condition
     * @return data nummber
     */
    public long countByMapConditions(Map<String, Object> conditions);
    
    /**
     * @param e   count condition
     * @return data nummber
     */
    public long countByE(E e);
 
    /**
     * @param conditions  delete conditions
     *           
     * @return delete nummber
     */
    public int deleteByMapContitions(Map<String, Object> conditions);
    /**
     * @param e  delete conditions
     *           
     * @return delete nummber
     */
    public int deleteByE(E e);
 
    /**
     * @param primary 
     * @return delete nummber
     */
    public int deleteByPrimary(long primary);
 
}
