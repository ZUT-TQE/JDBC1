package DAO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerImpl extends BaseDAO<Person> implements CustomerDAO{
    @Override
    public void insert(Connection connection, Person person) throws SQLException{
        String sql="insert into customers(name,email,birth) values(?,?,?)";
        update(connection,sql,person.getName(),person.getEmail(),person.getBirth());
    }

    @Override
    public Person selectByID(Connection connection, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="select id,name,email,birth from customers where id=?";
        Person person = chaxun(connection, sql, id);
        return person;
    }
}
