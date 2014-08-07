package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class 职位类型类
{
	public static boolean is已含该类型(String 职位类型)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("select ID from jobType where name=?");
			语句.setString(1, 职位类型);
			ResultSet 结果集 = 语句.executeQuery();
			return 结果集.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static int get类型ID(String 职位类型)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("select ID from jobType where name=?");
			语句.setString(1, 职位类型);
			ResultSet 结果集 = 语句.executeQuery();
			if (结果集.next())
			{
				return 结果集.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * @return 返回插入职位类型的ID
	 */
	public static int 添加类型(String 职位类型)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("insert into jobType(name) values(?)");
			语句.setString(1, 职位类型);
			if (语句.executeUpdate() > 0)
			{
				ResultSet 结果集 = 连接.prepareStatement("select LAST_INSERT_ID()").executeQuery();
				if (结果集.next())
				{
					int 公司ID = 结果集.getInt(1);
					return 公司ID;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
}
