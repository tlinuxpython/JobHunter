package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ���ݿ�������
{
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/10lab?user=root&password=123456";
	public static Connection get����() throws SQLException
	{
		return DriverManager.getConnection(connectionUrl);
	}
}
