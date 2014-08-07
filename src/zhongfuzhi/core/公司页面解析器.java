package zhongfuzhi.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.database.公司类;
import zhongfuzhi.util.字符串加工类;
import zhongfuzhi.util.正则式提取类;
import zhongfuzhi.util.页面访问类;

public class 公司页面解析器 {
	// 公司规模模式
	private static final Pattern 公司规模模式 = Pattern.compile("公司规模：</dt>[\\s]+<dd>[\\s]+&nbsp;" + "([^<]+)" + "<");
	// 公司性质模式
	private static final Pattern 公司性质模式 = Pattern.compile("公司性质：</dt>[\\s]+<dd>[\\s]+" + "([^<]+)" + "<");
	// 公司行业模式
	private static final Pattern 公司行业模式 = Pattern.compile("公司行业：</dt>[\\s]+<dd>[\\s]+&nbsp;" + "([^<]+)" + "<");
	// 公司介绍前缀
	private static final String[] 公司介绍前缀数组 = { "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">", "<tr>", "<td>" };
	// 公司介绍后缀
	private static final String 公司介绍后缀 = "</td>";

	/**
	 * @param 公司主页
	 *            可能为null
	 * @param 公司电话
	 *            可能为null
	 * @param 公司地址
	 *            可能为null
	 * @return 见@see公司类.添加公司
	 */
	public static int 解析(String 公司名, String 网址, String 公司主页, String 公司电话, String 公司地址) {
		String 网页内容 = 页面访问类.读取(网址);
		Matcher 匹配 = 公司规模模式.matcher(网页内容);
		String 公司规模 = null;
		if (匹配.find()) {
			公司规模 = 正则式提取类.提取(匹配);
		}
		// 公司规模都无法解析，出错
		if (公司规模 == null) {
			return -1;
		}
		匹配 = 公司性质模式.matcher(网页内容);
		String 公司性质 = null;
		if (匹配.find()) {
			公司性质 = 正则式提取类.提取(匹配);
		}
		匹配 = 公司行业模式.matcher(网页内容);
		String 公司行业 = null;
		if (匹配.find()) {
			公司行业 = 正则式提取类.提取(匹配);
		}
		int 公司介绍开始下标 = -1;
		for (int i = 0; i < 公司介绍前缀数组.length; i++) {
			公司介绍开始下标 = 网页内容.indexOf(公司介绍前缀数组[i], 公司介绍开始下标);
			if (公司介绍开始下标 < 0) {
				break;
			}
		}
		String 公司介绍 = null;
		if (公司介绍开始下标 >= 0) {
			公司介绍开始下标 += 公司介绍前缀数组[公司介绍前缀数组.length - 1].length();
			int 公司介绍结束下标 = 网页内容.indexOf(公司介绍后缀, 公司介绍开始下标);
			if (公司介绍结束下标 >= 0) {
				公司介绍 = 网页内容.substring(公司介绍开始下标, 公司介绍结束下标);
				公司介绍 = 字符串加工类.去除装饰标签(公司介绍);
			}
		}
		if (!公司类.is已含该公司(公司名)) {
			return 公司类.添加公司(公司名, 公司规模, 公司性质, 公司行业, 公司介绍, 公司主页, 公司电话, 公司地址);
		} else {
			return 公司类.get公司ID(公司名);
		}
	}
}
