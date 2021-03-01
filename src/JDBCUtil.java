import org.apache.commons.dbutils.DbUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

    public class JDBCUtil {

    public static Connection con() throws IOException, ClassNotFoundException, SQLException {
        //通过ClassLoader.getSystemClassLoader()也可以获得类加载器配置文件输入流
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void closeConnection(PreparedStatement preparedStatement,Connection connection) throws SQLException {
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }

    public static void closeConnection(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) throws SQLException {

        /**
         *  关闭资源方式一
         *  if(preparedStatement!=null){
         *        preparedStatement.close();
         *  }
         *  if (connection!=null){
         *        connection.close();
         *  }
         *  if(resultSet!=null){
         *        resultSet.close();
         *  }
         */
        /**
         * 方式二
         * DbUtils.close(connection);
         * DbUtils.close(preparedStatement);
         * DbUtils.close(resultSet);
         */

        //方式三
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(preparedStatement);
        DbUtils.closeQuietly(resultSet);
    }
}
