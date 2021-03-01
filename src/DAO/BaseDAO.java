package DAO;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;


public abstract class BaseDAO<T> {     //抽象类不能被实例化，用来继承，使用其方法
    Class<T> tClass=null;
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();    //获取带泛型的父类
        ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;//获得BaseDAO<Person>
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取了父类的泛型参数，参数可能有多个，所以为数组
        tClass= (Class) actualTypeArguments[0]; //泛型的第一个参数
    }

    public void canshu() throws SQLException, IOException, ClassNotFoundException {
        Connection connection=JDBCUtil.con();
        Person person=new Person(1,"465465","5564654@126.com",new Date(4564654655L));
        charu(connection,person);
    }


    @Test
    public void charu(Connection connection,Person person) throws SQLException, IOException, ClassNotFoundException {
        String sql="insert into customers(name,email,birth) values(?,?,?)";
        update(connection,sql,person.getName(),person.getEmail(),person.getBirth());
        JDBCUtil.closeConnection(null,connection);
    }

    //此处T与父类保持一致，所以就不能用泛型方法了，用了泛型类之后这个类的所有地方的T都与泛型类的T一致(包括子类的T也与父类T一致)
    public T chaxun(Connection connection, String sql, Object...args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        if (resultSet.next()){
            T t= tClass.newInstance();
            for (int i = 0; i < columnCount; i++) {
                String columnName=resultSetMetaData.getColumnName(i+1);
                Object object = resultSet.getObject(columnName);
                Field field = t.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,object);
            }
            return t;
        }
        JDBCUtil.closeConnection(preparedStatement,null,resultSet);
        return null;
    }

    public void update(Connection connection,String sql,Object...args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        preparedStatement.execute();


        JDBCUtil.closeConnection(preparedStatement,null);
    }

    public void insert(Connection connection,String sql,Object...args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        preparedStatement.execute();
        JDBCUtil.closeConnection(preparedStatement,null);
    }
}
