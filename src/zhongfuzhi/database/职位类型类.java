package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ְλ������
{
	public static boolean is�Ѻ�������(String ְλ����)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("select ID from jobType where name=?");
			���.setString(1, ְλ����);
			ResultSet ����� = ���.executeQuery();
			return �����.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static int get����ID(String ְλ����)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("select ID from jobType where name=?");
			���.setString(1, ְλ����);
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
	/**
	 * @return ���ز���ְλ���͵�ID
	 */
	public static int �������(String ְλ����)
	{
		try
		{
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("insert into jobType(name) values(?)");
			���.setString(1, ְλ����);
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
}
