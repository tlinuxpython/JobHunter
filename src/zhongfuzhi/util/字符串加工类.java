package zhongfuzhi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class �ַ����ӹ���
{
	private static final Pattern ͨ�ñ�ǩģʽ = Pattern.compile("(<[^>]+>)");
	private static final Pattern ���б�ǩģʽ = Pattern.compile("<[br|BR][^>]*>|<div[^>]*>");
	private static final Pattern ��������ѹ��ģʽ = Pattern.compile("[\\n]{2,}");
	public static String ȥ��װ�α�ǩ(String ԭ�ַ���)
	{
		ԭ�ַ��� = ԭ�ַ���.replaceAll("&nbsp;", " ");
		Matcher ƥ�� = ���б�ǩģʽ.matcher(ԭ�ַ���);
		ԭ�ַ��� = ƥ��.replaceAll("\n");
		ƥ�� = ��������ѹ��ģʽ.matcher(ԭ�ַ���);
		ԭ�ַ��� = ƥ��.replaceAll("\n");
		ƥ�� = ͨ�ñ�ǩģʽ.matcher(ԭ�ַ���);
		ԭ�ַ��� = ƥ��.replaceAll("");
		ԭ�ַ��� = ԭ�ַ���.trim();
		return ԭ�ַ���;
	}
	/**
	 * ����Ҫ����ȡ<div><div style="a">abc</div></div>�е�abc���㷨�����������ҵ���������ƥ���������
	 * @param ԭ�ַ��� <div><div>abc</div></div>
	 * @param ���ݿ�ʼ�ַ���  <div>
	 * @param ���ݽ����ַ��� </div>
	 * @param ��ʼ��ǩģʽ <div[^>]+>
	 * @param ��ǩ�����ַ��� </div>
	 * @return <div style="a">abc</div>
	 */
	public static String ��ȡ��ǩ������(String ԭ�ַ���, String ���ݿ�ʼ�ַ���, String ���ݽ����ַ���, Pattern ��ʼ��ǩģʽ)
	{
		int ���ݿ�ʼ�±� = ԭ�ַ���.indexOf(���ݿ�ʼ�ַ���);
		if (���ݿ�ʼ�±� >= 0)
		{
			���ݿ�ʼ�±� += ���ݿ�ʼ�ַ���.length();
			ԭ�ַ��� = ԭ�ַ���.substring(���ݿ�ʼ�±�);
			int ǰ�������ݽ������� = 0;
			int ��ǩδ�ر��� = 1;
			while (��ǩδ�ر��� > 0)
			{
				String ���ֲ��ҹرձ�ǩ�Ӵ� = ԭ�ַ���.substring(ǰ�������ݽ�������);
				Matcher ƥ�� = ��ʼ��ǩģʽ.matcher(���ֲ��ҹرձ�ǩ�Ӵ�);
				int ���ֺ������ʼ��ǩ�±� = -1;
				int ���ֺ������ʼ��ǩ���� = 0;
				if (ƥ��.find())
				{
					���ֺ������ʼ��ǩ�±� = ƥ��.start();
					���ֺ������ʼ��ǩ���� = ƥ��.group().length();
				}
				���ֲ��ҹرձ�ǩ�Ӵ� = ԭ�ַ���.substring(ǰ�������ݽ�������, ǰ�������ݽ������� + ���ֺ������ʼ��ǩ�±�);
				String ���ҹرձ�ǩ�Ӵ� = ���ֲ��ҹرձ�ǩ�Ӵ�;
				int �������ݽ����±� = 0;
				while (��ǩδ�ر��� > 0)
				{
					int ������ǩ�±� = ���ҹرձ�ǩ�Ӵ�.indexOf(���ݽ����ַ���);
					if (������ǩ�±� >= 0)
					{
						��ǩδ�ر���--;
						���ҹرձ�ǩ�Ӵ� = ���ҹرձ�ǩ�Ӵ�.substring(������ǩ�±�+���ݽ����ַ���.length());
						�������ݽ����±� += ������ǩ�±�+���ݽ����ַ���.length();
					}
					else
					{
						ǰ�������ݽ������� += ���ֺ������ʼ��ǩ�±� + ���ֺ������ʼ��ǩ����;
						break;
					}
				}
				if (��ǩδ�ر��� == 0)
				{
					ԭ�ַ��� = ԭ�ַ���.substring(0, ǰ�������ݽ������� + �������ݽ����±� - ���ݽ����ַ���.length());
				}
				else
				{
					��ǩδ�ر���++;
				}
			}
			return ԭ�ַ���;
		}
		else
		{
			return null;
		}
	}
}
