package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 此方法用于规范针对于customer表的常用操作
 */
public interface CustomerDAO {      //接口的方法不能具体实现，创建一个类再去具体实现接口的方法
    void insert(Connection connection, Person person) throws SQLException, IOException, ClassNotFoundException;  //将person对象添加到数据库

    /**
     * 根据id找到对应记录
     */
    Person selectByID(Connection connection,int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


}
