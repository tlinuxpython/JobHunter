package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class 数据库连接类
{
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/10lab?user=root&password=123456";
	public static Connection get连接() throws SQLException
	{
		return DriverManager.getConnection(connectionUrl);
	}
}
