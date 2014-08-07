package zhongfuzhi.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.swing.界面类;
import zhongfuzhi.util.正则式提取类;
import zhongfuzhi.util.页面访问类;

public class 搜索类
{
	// 总页数模式
	private static final Pattern 总页数模式 = Pattern.compile(">" + "([^<]+)" +"</a></li><li class=\"pagesDown-pos\">");
	// 职位页面URL模式
	private static final Pattern 职位页面URL模式 = Pattern.compile("class=\"add-span\"><a[\\s]+href=\"" + "([^\"]+)" + "\"");
	public static void 搜索(String 地区, String 关键字)
	{
		try
		{
			String 搜索页面URL = String.format("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%s&kw=%s", URLEncoder.encode(地区, "utf-8"), URLEncoder.encode(关键字, "utf-8"));
			String 网页内容 = 页面访问类.读取(搜索页面URL + "&p=1");
			// 搜索成功
			if (网页内容.indexOf("frmMain") >= 0)
			{
				Matcher 匹配 = 总页数模式.matcher(网页内容);
				// 至少有一页
				int 总页数 = 1;
				if (匹配.find())
				{
					String 总页数字符串 = 正则式提取类.提取(匹配);
					总页数 = Integer.parseInt(总页数字符串);
					界面类.单例.输出信息(String.format("检索结果共有%d页\n", 总页数));
				}
				for (int 页 = 1; 页 <= 总页数; 页++)
				{
					界面类.单例.输出信息(String.format("正在收录%d/%d页\n", 页, 总页数));
					if (页 != 1)
					{
						网页内容 = 页面访问类.读取(搜索页面URL + "&p=" + 页);
					}
					匹配 = 职位页面URL模式.matcher(网页内容);
					while (匹配.find())
					{
						String 职位页面URL = 匹配.group(1);
						// 过滤某些不是真正职位页面URL
						if (职位页面URL.indexOf("http://jobs.zhaopin.com") >= 0)
						{
							界面类.单例.输出信息("准备解析" + 职位页面URL + "\n");
							// 检测是否需要停止收录
							if (界面类.单例.is停止收录())
							{
								return;
							}
							职位页面解析器.解析(职位页面URL);
						}
					}
				}
			}
			else
			{
				界面类.单例.输出信息("对不起，暂时无符合您条件的职位。");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		finally
		{
			界面类.单例.被通知收录结束();
		}
	}
}