package zhongfuzhi.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class 职位类 {
	public static int getID(String 职位名, String 公司名) {
		try {
			Connection 连接 = 数据库连接类.get连接();
			int 公司ID = 公司类.get公司ID(公司名);
			if (公司ID >= 0) {
				PreparedStatement 语句 = 连接.prepareStatement("select ID from job where name=? and enterpriseID=?");
				语句.setString(1, 职位名);
				语句.setInt(2, 公司ID);
				ResultSet 结果集 = 语句.executeQuery();
				if (结果集.next()) {
					return 结果集.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static Date get发布日期(int ID) {
		try {
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("select publishDate from job where ID=?");
			语句.setInt(1, ID);
			ResultSet 结果集 = 语句.executeQuery();
			if (结果集.next()) {
				return 结果集.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean 更新(int ID, String 工作经验, String 工作性质, String 最低学历, String 职位月薪, String 招聘人数, String 职位描述, Date 发布日期, String 工作地点, int 职位类型ID) {
		try {
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("update job set experience=?,nature=?,educationRequest=?,wage=?,vacancies=?,description=?,publishDate=?,location=?,jobTypeID=?) where ID=?");
			语句.setString(1, 工作经验);
			语句.setString(2, 工作性质);
			语句.setString(3, 最低学历);
			语句.setString(4, 职位月薪);
			语句.setString(5, 招聘人数);
			语句.setString(6, 职位描述);
			语句.setDate(7, 发布日期);
			语句.setString(8, 工作地点);
			语句.setInt(9, 职位类型ID);
			语句.setInt(10, ID);
			return 语句.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean 添加(String 职位名, String 工作经验, String 工作性质, String 最低学历, String 职位月薪, String 招聘人数, String 职位描述, Date 发布日期, String 工作地点, int 职位类型ID, int 公司ID) {
		try {
			Connection 连接 = 数据库连接类.get连接();
			PreparedStatement 语句 = 连接.prepareStatement("insert into job(name,experience,nature,educationRequest,wage,vacancies,description,publishDate,location,jobTypeID,enterpriseID) values(?,?,?,?,?,?,?,?,?,?,?)");
			语句.setString(1, 职位名);
			语句.setString(2, 工作经验);
			语句.setString(3, 工作性质);
			语句.setString(4, 最低学历);
			语句.setString(5, 职位月薪);
			语句.setString(6, 招聘人数);
			语句.setString(7, 职位描述);
			语句.setDate(8, 发布日期);
			语句.setString(9, 工作地点);
			语句.setInt(10, 职位类型ID);
			语句.setInt(11, 公司ID);
			return 语句.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
