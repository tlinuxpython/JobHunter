package zhongfuzhi.util;

import java.util.regex.Matcher;

public class 正则式提取类 {
	// 根据matcher和groupIndex提取信息
	public static String 提取(Matcher matcher) {
		// 提取匹配的子串
		String group = matcher.group(1);
		// 去除空格、换行
		return 字符串加工类.去除装饰标签(group);
	}
}
