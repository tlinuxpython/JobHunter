package zhongfuzhi.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.swing.������;
import zhongfuzhi.util.����ʽ��ȡ��;
import zhongfuzhi.util.ҳ�������;

public class ������
{
	// ��ҳ��ģʽ
	private static final Pattern ��ҳ��ģʽ = Pattern.compile(">" + "([^<]+)" +"</a></li><li class=\"pagesDown-pos\">");
	// ְλҳ��URLģʽ
	private static final Pattern ְλҳ��URLģʽ = Pattern.compile("class=\"add-span\"><a[\\s]+href=\"" + "([^\"]+)" + "\"");
	public static void ����(String ����, String �ؼ���)
	{
		try
		{
			String ����ҳ��URL = String.format("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%s&kw=%s", URLEncoder.encode(����, "utf-8"), URLEncoder.encode(�ؼ���, "utf-8"));
			String ��ҳ���� = ҳ�������.��ȡ(����ҳ��URL + "&p=1");
			// �����ɹ�
			if (��ҳ����.indexOf("frmMain") >= 0)
			{
				Matcher ƥ�� = ��ҳ��ģʽ.matcher(��ҳ����);
				// ������һҳ
				int ��ҳ�� = 1;
				if (ƥ��.find())
				{
					String ��ҳ���ַ��� = ����ʽ��ȡ��.��ȡ(ƥ��);
					��ҳ�� = Integer.parseInt(��ҳ���ַ���);
					������.����.�����Ϣ(String.format("�����������%dҳ\n", ��ҳ��));
				}
				for (int ҳ = 1; ҳ <= ��ҳ��; ҳ++)
				{
					������.����.�����Ϣ(String.format("������¼%d/%dҳ\n", ҳ, ��ҳ��));
					if (ҳ != 1)
					{
						��ҳ���� = ҳ�������.��ȡ(����ҳ��URL + "&p=" + ҳ);
					}
					ƥ�� = ְλҳ��URLģʽ.matcher(��ҳ����);
					while (ƥ��.find())
					{
						String ְλҳ��URL = ƥ��.group(1);
						// ����ĳЩ��������ְλҳ��URL
						if (ְλҳ��URL.indexOf("http://jobs.zhaopin.com") >= 0)
						{
							������.����.�����Ϣ("׼������" + ְλҳ��URL + "\n");
							// ����Ƿ���Ҫֹͣ��¼
							if (������.����.isֹͣ��¼())
							{
								return;
							}
							ְλҳ�������.����(ְλҳ��URL);
						}
					}
				}
			}
			else
			{
				������.����.�����Ϣ("�Բ�����ʱ�޷�����������ְλ��");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		finally
		{
			������.����.��֪ͨ��¼����();
		}
	}
}