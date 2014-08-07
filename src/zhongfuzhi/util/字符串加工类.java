package zhongfuzhi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 字符串加工类
{
	private static final Pattern 通用标签模式 = Pattern.compile("(<[^>]+>)");
	private static final Pattern 换行标签模式 = Pattern.compile("<[br|BR][^>]*>|<div[^>]*>");
	private static final Pattern 连续换行压缩模式 = Pattern.compile("[\\n]{2,}");
	public static String 去除装饰标签(String 原字符串)
	{
		原字符串 = 原字符串.replaceAll("&nbsp;", " ");
		Matcher 匹配 = 换行标签模式.matcher(原字符串);
		原字符串 = 匹配.replaceAll("\n");
		匹配 = 连续换行压缩模式.matcher(原字符串);
		原字符串 = 匹配.replaceAll("\n");
		匹配 = 通用标签模式.matcher(原字符串);
		原字符串 = 匹配.replaceAll("");
		原字符串 = 原字符串.trim();
		return 原字符串;
	}
	/**
	 * 例如要求提取<div><div style="a">abc</div></div>中的abc，算法的作用在于找到与左括号匹配的右括号
	 * @param 原字符串 <div><div>abc</div></div>
	 * @param 内容开始字符串  <div>
	 * @param 内容结束字符串 </div>
	 * @param 开始标签模式 <div[^>]+>
	 * @param 标签结束字符串 </div>
	 * @return <div style="a">abc</div>
	 */
	public static String 提取标签内内容(String 原字符串, String 内容开始字符串, String 内容结束字符串, Pattern 开始标签模式)
	{
		int 内容开始下标 = 原字符串.indexOf(内容开始字符串);
		if (内容开始下标 >= 0)
		{
			内容开始下标 += 内容开始字符串.length();
			原字符串 = 原字符串.substring(内容开始下标);
			int 前几轮内容结束长度 = 0;
			int 标签未关闭数 = 1;
			while (标签未关闭数 > 0)
			{
				String 本轮查找关闭标签子串 = 原字符串.substring(前几轮内容结束长度);
				Matcher 匹配 = 开始标签模式.matcher(本轮查找关闭标签子串);
				int 本轮后方最近开始标签下标 = -1;
				int 本轮后方最近开始标签长度 = 0;
				if (匹配.find())
				{
					本轮后方最近开始标签下标 = 匹配.start();
					本轮后方最近开始标签长度 = 匹配.group().length();
				}
				本轮查找关闭标签子串 = 原字符串.substring(前几轮内容结束长度, 前几轮内容结束长度 + 本轮后方最近开始标签下标);
				String 查找关闭标签子串 = 本轮查找关闭标签子串;
				int 本轮内容结束下标 = 0;
				while (标签未关闭数 > 0)
				{
					int 结束标签下标 = 查找关闭标签子串.indexOf(内容结束字符串);
					if (结束标签下标 >= 0)
					{
						标签未关闭数--;
						查找关闭标签子串 = 查找关闭标签子串.substring(结束标签下标+内容结束字符串.length());
						本轮内容结束下标 += 结束标签下标+内容结束字符串.length();
					}
					else
					{
						前几轮内容结束长度 += 本轮后方最近开始标签下标 + 本轮后方最近开始标签长度;
						break;
					}
				}
				if (标签未关闭数 == 0)
				{
					原字符串 = 原字符串.substring(0, 前几轮内容结束长度 + 本轮内容结束下标 - 内容结束字符串.length());
				}
				else
				{
					标签未关闭数++;
				}
			}
			return 原字符串;
		}
		else
		{
			return null;
		}
	}
}
