1 新建数据库的User表  name and age 
2 修改jdbc.properties
3 修改 BaseService 中的resource属性

使用 druid的时候:
1)**BaseService 中的resource改成   mybatis_config_druid.xml
2)**使用mybatis_config_druid.xml作为mybatis的配置文件\
3)**使用MyselfDefineDataSourceFactory 类