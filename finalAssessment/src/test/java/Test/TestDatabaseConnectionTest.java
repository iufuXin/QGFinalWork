package Test;

import service.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestDatabaseConnectionTest {
    public static void main(String[] args) throws Exception {
        DatabaseConnectionPool.SimpleConnectionPool();
        Connection connection = DatabaseConnectionPool.getConnection();
        //定义sql语句
        String sql = "insert into testconn (name) values (?);";
        //获取sql执行的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"liufu");
        int count = preparedStatement.executeUpdate();
        if ( count > 0){
            System.out.println("添加成功！！！");
        }
        DatabaseConnectionPool.releaseConnection(connection);
        DatabaseConnectionPool.closePool();





    }


}