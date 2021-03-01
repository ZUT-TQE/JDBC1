package DAO;
import org.junit.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class test {
    CustomerImpl customerImpl=new CustomerImpl();
    @Test
    public void testInsert() throws SQLException, IOException, ClassNotFoundException {
        Connection connection=JDBCUtil.con();
        Person person = new Person(36, "dsaddsadsa", "dsadsad@qq.com", new Date(465465464654L));
        customerImpl.insert(connection,person);
        JDBCUtil.closeConnection(null,connection,null);
    }

    @Test
    public void Select() throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        Connection connection=JDBCUtil.con();
        Person person = customerImpl.selectByID(connection, 4);
        System.out.println(person);

    }
}
