package zhongfuzhi.util;

import java.util.regex.Matcher;

public class ����ʽ��ȡ�� {
	// ����matcher��groupIndex��ȡ��Ϣ
	public static String ��ȡ(Matcher matcher) {
		// ��ȡƥ����Ӵ�
		String group = matcher.group(1);
		// ȥ���ո񡢻���
		return �ַ����ӹ���.ȥ��װ�α�ǩ(group);
	}
}
