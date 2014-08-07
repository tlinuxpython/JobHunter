package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ְλ�� {
	public static int getID(String ְλ��, String ��˾��) {
		try {
			Connection ���� = ���ݿ�������.get����();
			int ��˾ID = ��˾��.get��˾ID(��˾��);
			if (��˾ID >= 0) {
				PreparedStatement ��� = ����.prepareStatement("select ID from job where name=? and enterpriseID=?");
				���.setString(1, ְλ��);
				���.setInt(2, ��˾ID);
				ResultSet ����� = ���.executeQuery();
				if (�����.next()) {
					return �����.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static Date get��������(int ID) {
		try {
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("select publishDate from job where ID=?");
			���.setInt(1, ID);
			ResultSet ����� = ���.executeQuery();
			if (�����.next()) {
				return �����.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean ����(int ID, String ��������, String ��������, String ���ѧ��, String ְλ��н, String ��Ƹ����, String ְλ����, Date ��������, String �����ص�, int ְλ����ID) {
		try {
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("update job set experience=?,nature=?,educationRequest=?,wage=?,vacancies=?,description=?,publishDate=?,location=?,jobTypeID=?) where ID=?");
			���.setString(1, ��������);
			���.setString(2, ��������);
			���.setString(3, ���ѧ��);
			���.setString(4, ְλ��н);
			���.setString(5, ��Ƹ����);
			���.setString(6, ְλ����);
			���.setDate(7, ��������);
			���.setString(8, �����ص�);
			���.setInt(9, ְλ����ID);
			���.setInt(10, ID);
			return ���.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean ���(String ְλ��, String ��������, String ��������, String ���ѧ��, String ְλ��н, String ��Ƹ����, String ְλ����, Date ��������, String �����ص�, int ְλ����ID, int ��˾ID) {
		try {
			Connection ���� = ���ݿ�������.get����();
			PreparedStatement ��� = ����.prepareStatement("insert into job(name,experience,nature,educationRequest,wage,vacancies,description,publishDate,location,jobTypeID,enterpriseID) values(?,?,?,?,?,?,?,?,?,?,?)");
			���.setString(1, ְλ��);
			���.setString(2, ��������);
			���.setString(3, ��������);
			���.setString(4, ���ѧ��);
			���.setString(5, ְλ��н);
			���.setString(6, ��Ƹ����);
			���.setString(7, ְλ����);
			���.setDate(8, ��������);
			���.setString(9, �����ص�);
			���.setInt(10, ְλ����ID);
			���.setInt(11, ��˾ID);
			return ���.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
