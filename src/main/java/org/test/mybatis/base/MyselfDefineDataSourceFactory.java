/**
 * 
 */

package org.test.mybatis.base;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author V.Caifeng.Fan
 * @ClassName MyselfDefineDataSourceFactory
 * @Description: TODO
 * @date 2017年8月17日 下午4:34:10
 */
public class MyselfDefineDataSourceFactory extends UnpooledDataSourceFactory {

	public MyselfDefineDataSourceFactory() {
		this.dataSource = new DruidDataSource();
	}
}
