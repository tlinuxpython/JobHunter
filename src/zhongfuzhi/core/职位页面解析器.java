package zhongfuzhi.core;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.database.公司类;
import zhongfuzhi.database.职位类型类;
import zhongfuzhi.database.职位类;
import zhongfuzhi.swing.界面类;
import zhongfuzhi.util.字符串加工类;
import zhongfuzhi.util.正则式提取类;
import zhongfuzhi.util.页面访问类;

public class 职位页面解析器 {
	// 公司名模式
	private static final Pattern 公司名模式 = Pattern.compile("companylink3[^>]*>" + "([^<]+)" + "<");
	// 公司URL模式
	private static final Pattern 公司页面URL模式 = Pattern.compile("tocompanylink3'\\);\" href=\"" + "([^\"]+)" + "\"");
	// 公司主页模式
	private static final Pattern 公司主页模式 = Pattern.compile("公司主页：" + "([^<]+)" + "<");
	// 公司电话模式
	private static final Pattern 公司电话模式 = Pattern.compile("真：" + "([^<]+)" + "<");
	// 公司地址模式
	private static final Pattern 公司地址模式 = Pattern.compile("<div>公司地址：" + "([^<]+)" + "<");
	// 职位类型模式
	private static final Pattern 职位类型模式 = Pattern.compile("职位类别：</span><strong><[^>]+><[^>]+>" + "([^<]+)" + "<");
	// 职位名称模式
	private static final Pattern 职位名称模式 = Pattern.compile("inner-left fl\">[\\s]+<h1>" + "([^<]+)" + "<");
	// 工作经验模式
	private static final Pattern 工作经验模式 = Pattern.compile("工作经验：</span><strong>" + "([^<]+)" + "<");
	// 工作性质模式
	private static final Pattern 工作性质模式 = Pattern.compile("工作性质：</span><strong>" + "([^<]+)" + "<");
	// 最低学历模式
	private static final Pattern 最低学历模式 = Pattern.compile("最低学历：</span><strong>" + "([^<]+)" + "<");
	// 职位月薪模式
	private static final Pattern 职位月薪模式 = Pattern.compile("职位月薪：</span><strong>" + "([^<]+)" + "<");
	// 招聘人数模式
	private static final Pattern 招聘人数模式 = Pattern.compile("招聘人数：</span><strong>" + "([^<]+)" + "<");
	// 职位描述开始字符串
	private static final String 职位描述开始字符串 = "<div class=\"tab-inner-cont\">";
	// 职位描述结束字符串
	private static final String 职位描述结束字符串 = "</div>";
	// 职位描述标签开始模式
	private static final Pattern 职位描述标签开始模式 = Pattern.compile("<div[^>]+>");
	// 发布日期模式
	private static final Pattern 发布日期模式 = Pattern.compile("freshdate\">" + "([^<]+)" + "<");
	// 工作地点模式
	private static final Pattern 工作地点模式 = Pattern.compile("工作地点：</span><strong><a[^>]+>" + "([^<]+)" + "<");

	public static void 解析(String 网址) {
		String 网页内容 = 页面访问类.读取(网址);
		// 公司名
		Matcher 匹配 = 公司名模式.matcher(网页内容);
		String 公司名 = null;
		int 公司ID = -1;
		if (匹配.find()) {
			公司名 = 正则式提取类.提取(匹配);
		} else {
			// 公司名都解析不出来，说明页面乱码，返回
			界面类.单例.输出信息(String.format("解析%s失败\n", 网址));
			return;
		}
		String 职位名称 = null;
		匹配 = 职位名称模式.matcher(网页内容);
		if (匹配.find()) {
			职位名称 = 正则式提取类.提取(匹配);
		}
		boolean is有更新 = false;
		Date 发布日期 = null;
		匹配 = 发布日期模式.matcher(网页内容);
		if (匹配.find()) {
			发布日期 = Date.valueOf(正则式提取类.提取(匹配));
		}
		int 职位ID = 职位类.getID(职位名称, 公司名);
		if (职位ID >= 0) {
			界面类.单例.输出信息(String.format("<%s,%s>已存在，", 公司名, 职位名称));
			Date 原先日期 = 职位类.get发布日期(职位ID);
			if (发布日期.after(原先日期)) {
				is有更新 = true;
				界面类.单例.输出信息(String.format("且%s is after %s，需更新\n", 发布日期, 原先日期));
			} else {
				界面类.单例.输出信息("无需更新\n");
				return;
			}
		} else {
			界面类.单例.输出信息(String.format("准备导入新记录:<%s,%s>\n", 公司名, 职位名称));
		}
		if (!公司类.is已含该公司(公司名)) {
			// 公司URL模式
			匹配 = 公司页面URL模式.matcher(网页内容);
			String 公司URL = null;
			if (匹配.find()) {
				公司URL = 正则式提取类.提取(匹配);
				String 公司主页 = null;
				String 公司电话 = null;
				String 公司地址 = null;
				匹配 = 公司主页模式.matcher(网页内容);
				if (匹配.find()) {
					公司主页 = 正则式提取类.提取(匹配);
				}
				匹配 = 公司电话模式.matcher(网页内容);
				if (匹配.find()) {
					公司电话 = 正则式提取类.提取(匹配);
				}
				匹配 = 公司地址模式.matcher(网页内容);
				if (匹配.find()) {
					公司地址 = 正则式提取类.提取(匹配);
				}
				公司ID = 公司页面解析器.解析(公司名, 公司URL, 公司主页, 公司电话, 公司地址);
				if (公司ID < 0) {
					界面类.单例.输出信息(String.format("无法解析公司页面：%s，略过该职位信息\n", 公司URL));
					return;
				}
			} else {
				// 公司url找不到则返回
				界面类.单例.输出信息(String.format("无法解析公司URL：%s，略过该职位信息\n", 公司URL));
				return;
			}
		} else {
			公司ID = 公司类.get公司ID(公司名);
		}
		String 职位类型 = null;
		匹配 = 职位类型模式.matcher(网页内容);
		if (匹配.find()) {
			职位类型 = 正则式提取类.提取(匹配);
		}
		int 职位类型ID = -1;
		if (职位类型类.is已含该类型(职位类型)) {
			职位类型ID = 职位类型类.get类型ID(职位类型);
		} else {
			职位类型ID = 职位类型类.添加类型(职位类型);
		}
		String 工作经验 = null;
		匹配 = 工作经验模式.matcher(网页内容);
		if (匹配.find()) {
			工作经验 = 正则式提取类.提取(匹配);
		}
		String 工作性质 = null;
		匹配 = 工作性质模式.matcher(网页内容);
		if (匹配.find()) {
			工作性质 = 正则式提取类.提取(匹配);
		}
		String 最低学历 = null;
		匹配 = 最低学历模式.matcher(网页内容);
		if (匹配.find()) {
			最低学历 = 正则式提取类.提取(匹配);
		}
		String 职位月薪 = null;
		匹配 = 职位月薪模式.matcher(网页内容);
		if (匹配.find()) {
			职位月薪 = 正则式提取类.提取(匹配);
		}
		String 招聘人数 = null;
		匹配 = 招聘人数模式.matcher(网页内容);
		if (匹配.find()) {
			招聘人数 = 正则式提取类.提取(匹配);
		}
		String 职位描述 = 字符串加工类.提取标签内内容(网页内容, 职位描述开始字符串, 职位描述结束字符串, 职位描述标签开始模式);
		职位描述 = 字符串加工类.去除装饰标签(职位描述);
		String 工作地点 = null;
		匹配 = 工作地点模式.matcher(网页内容);
		if (匹配.find()) {
			工作地点 = 正则式提取类.提取(匹配);
		}
		if (is有更新) {
			职位类.更新(职位ID, 工作经验, 工作性质, 最低学历, 职位月薪, 招聘人数, 职位描述, 发布日期, 工作地点, 职位类型ID);
		} else {
			职位类.添加(职位名称, 工作经验, 工作性质, 最低学历, 职位月薪, 招聘人数, 职位描述, 发布日期, 工作地点, 职位类型ID, 公司ID);
		}
	}
}
