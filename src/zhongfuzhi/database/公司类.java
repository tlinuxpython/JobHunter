package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class 公司类
{
	/**
	 * @return 返回插入公司的ID
	 */
	public static int 添加公司(String 名称, String 规模, String 性质, String 行业, String 介绍, String 主页, String 电话, String 地址)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("insert into enterprise(name,scale,nature,industry,introduction,homePage,telephone,address) values(?,?,?,?,?,?,?,?)");
			语句.setString(1, 名称);
			语句.setString(2, 规模);
			语句.setString(3, 性质);
			语句.setString(4, 行业);
			语句.setString(5, 介绍);
			语句.setString(6, 主页);
			语句.setString(7, 电话);
			语句.setString(8, 地址);
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
	public static boolean is已含该公司(String 名称)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("select ID from enterprise where name=?");
			语句.setString(1, 名称);
			ResultSet 结果集 = 语句.executeQuery();
			return 结果集.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static int get公司ID(String 名称)
	{
		try
		{
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("select ID from enterprise where name=?");
			语句.setString(1, 名称);
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
}