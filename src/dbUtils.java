import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import sun.plugin2.main.server.ResultHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class dbUtils {

    /**
     * 利用dbutils包下的queryRunner类进行增删改
     *      update-->增删改
     */
    @Test
    public void m() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="insert into customers(name,email,birth) values(?,?,?)";
        int update = queryRunner.update(connection, sql, "蔡徐坤", "caixukun@qq.com", "1285-09-29");
        System.out.println(update);
        JDBCUtil.closeConnection(null,connection);
    }

    @Test
    public void n() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="select id,name,email,birth from customers where id=?";
        BeanHandler<Person> beanHandler=new BeanHandler<>(Person.class);  //此处无空参构造器，所以需要传入Person类
        //ResultHandler-->结果集处理器
        //传入结果集处理器实例，而Interface ResultSetHandler<T>是接口，需要使用用实现这个接口的类
        Person person = queryRunner.query(connection, sql, beanHandler, 1);
        System.out.println(person);
        JDBCUtil.closeConnection(null,connection);
    }


    @Test
    public void n1() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="select id,name,email,birth from customers where id<?";
        BeanListHandler<Person> beanHandler=new BeanListHandler<>(Person.class);  //此处无空参构造器，所以需要传入Person类
        //ResultHandler-->结果集处理器
        //传入结果集处理器实例，而Interface ResultSetHandler<T>是接口，需要使用用实现这个接口的类
        List<Person> people = queryRunner.query(connection, sql, beanHandler, 10);
        people.forEach(System.out::println);
        JDBCUtil.closeConnection(null,connection);
    }

    @Test
    public void n2() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="select id,name,email,birth from customers where id=?";
        MapHandler mapHandler=new MapHandler();
        //ResultHandler-->结果集处理器
        Map<String, Object> objectMap = queryRunner.query(connection, sql, mapHandler, 8);
        System.out.println(objectMap);
        JDBCUtil.closeConnection(null,connection);
    }


    @Test
    public void n3() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="select count(*) from customers";
        String sql1="select max(birth) from customers";
        ScalarHandler scalarHandler = new ScalarHandler();
        /**
         *
         *  Object query = queryRunner.query(connection, sql, scalarHandler);
         *  Object query1 = queryRunner.query(connection, sql1, scalarHandler);
         */
        Long query = (Long) queryRunner.query(connection, sql, scalarHandler);
        Date query1 = (Date) queryRunner.query(connection, sql1, scalarHandler);
        System.out.println(query);
        System.out.println(query1);
        JDBCUtil.closeConnection(null,connection);
    }


    /**
     * 自定义ResultSetHandler<T>，当已经实现该接口的类不能满足需求时可以自定义
     */
    @Test
    public void n4() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection=JDBCUtil.con();
        String sql="select id,name,email,birth from customers where id=?";
        //使用匿名内部类创建ResultSetHandler对象(接口)
        ResultSetHandler<Person> resultSetHandler=new ResultSetHandler<Person>() {
            //ResultSet resultSet存放的是sql语句String sql="select id,name,email,birth from customers where id=?";的结果集
            @Override
            public Person handle(ResultSet resultSet) throws SQLException {
                if(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String email = resultSet.getString(3);
                    Date birth = resultSet.getDate(4);
                    Person person=new Person(id,name,email,birth);
                    return person;
                }
                return null;
            }
        };
        //ResultHandler-->结果集处理器
        //传入结果集处理器实例，而Interface ResultSetHandler<T>是接口，需要使用用实现这个接口的类
        Person person = queryRunner.query(connection, sql, resultSetHandler, 2);
        System.out.println(person);
        JDBCUtil.closeConnection(null,connection);
    }
}


