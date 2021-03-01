import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;


import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    /**
     *方式一
     */
    @Test
    public void m() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("tianqien");

        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }


    /**
     * 方式二
     * 通过配置文件利用DruidDataSourceFactory工厂创建DataSource接口
     */
    private static DruidDataSource source;
    {
        try {
            Properties properties=new Properties();
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);

            source= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void m2() throws Exception {

        DruidPooledConnection connection = source.getConnection();

        System.out.println(connection);
    }

    
}
