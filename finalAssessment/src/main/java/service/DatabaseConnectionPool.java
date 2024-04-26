package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionPool {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/qgfinalassessment";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Lfx520520";
    private static final int INITIAL_POOL_SIZE = 10;//初始大小
    //private static final int MAXIMUM_NUMBER_OF_CONNECTIONS = 10;//最大连接数
    //私有化构造器
    private DatabaseConnectionPool(){}
    private static List<Connection> pool;

    //它在初始化时会调用 initializePool() 方法来创建连接池。
    public static void SimpleConnectionPool() {
        initializePool();
    }

    //该方法用于初始化连接。它使用了一个循环创建初始大小的数据库连接，并将他们添加到连接池中。如果在创建连接的过程中发彺了异常，将抛出一个运行时的异常
    private static void initializePool() {
        pool = new ArrayList<>(INITIAL_POOL_SIZE);
        //用于存放数据库连接
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                pool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing the connection pool.");
        }
    }

    //该方法用于从连接池中获取一个数据库连接。它是同步方法，确保在多线程环境下的安全性。如果连接池为空，则抛出一个 SQLException 异常。否则，它从连接池中移除一个连接并返回。
    public static synchronized Connection getConnection() throws SQLException {
        if (pool.isEmpty()) {
            throw new SQLException("Connection pool exhausted.");
        }
        Connection connection = pool.remove(pool.size() - 1);
        return connection;
    }

    //该方法用于释放一个数据库连接，并将其返回到连接池中。如果连接不为 null 并且未关闭，则将连接添加到连接池中。
    public static synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    pool.add(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //该方法用于关闭连接池。它遍历连接池中的所有连接，并关闭每一个未关闭的连接。最后，清空连接池中的内容。
    public static synchronized void closePool() throws SQLException {
        for (Connection connection : pool) {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        pool.clear();
    }
}

