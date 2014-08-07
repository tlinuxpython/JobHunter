package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ��˾��
{
	/**
	 * @return ���ز��빫˾��ID
	 */
	public static int ��ӹ�˾(String ����, String ��ģ, String ����, String ��ҵ, String ����, String ��ҳ, String �绰, String ��ַ)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("insert into enterprise(name,scale,nature,industry,introduction,homePage,telephone,address) values(?,?,?,?,?,?,?,?)");
			���.setString(1, ����);
			���.setString(2, ��ģ);
			���.setString(3, ����);
			���.setString(4, ��ҵ);
			���.setString(5, ����);
			���.setString(6, ��ҳ);
			���.setString(7, �绰);
			���.setString(8, ��ַ);
			if (���.executeUpdate() > 0)
			{
				ResultSet ����� = ����.prepareStatement("select LAST_INSERT_ID()").executeQuery();
				if (�����.next())
				{
					int ��˾ID = �����.getInt(1);
					return ��˾ID;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	public static boolean is�Ѻ��ù�˾(String ����)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("select ID from enterprise where name=?");
			���.setString(1, ����);
			ResultSet ����� = ���.executeQuery();
			return �����.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static int get��˾ID(String ����)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("select ID from enterprise where name=?");
			���.setString(1, ����);
			ResultSet ����� = ���.executeQuery();
			if (�����.next())
			{
				return �����.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
}