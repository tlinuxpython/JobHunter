package zhongfuzhi.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.database.��˾��;
import zhongfuzhi.util.�ַ����ӹ���;
import zhongfuzhi.util.����ʽ��ȡ��;
import zhongfuzhi.util.ҳ�������;

public class ��˾ҳ������� {
	// ��˾��ģģʽ
	private static final Pattern ��˾��ģģʽ = Pattern.compile("��˾��ģ��</dt>[\\s]+<dd>[\\s]+&nbsp;" + "([^<]+)" + "<");
	// ��˾����ģʽ
	private static final Pattern ��˾����ģʽ = Pattern.compile("��˾���ʣ�</dt>[\\s]+<dd>[\\s]+" + "([^<]+)" + "<");
	// ��˾��ҵģʽ
	private static final Pattern ��˾��ҵģʽ = Pattern.compile("��˾��ҵ��</dt>[\\s]+<dd>[\\s]+&nbsp;" + "([^<]+)" + "<");
	// ��˾����ǰ׺
	private static final String[] ��˾����ǰ׺���� = { "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">", "<tr>", "<td>" };
	// ��˾���ܺ�׺
	private static final String ��˾���ܺ�׺ = "</td>";

	/**
	 * @param ��˾��ҳ
	 *            ����Ϊnull
	 * @param ��˾�绰
	 *            ����Ϊnull
	 * @param ��˾��ַ
	 *            ����Ϊnull
	 * @return ��@see��˾��.��ӹ�˾
	 */
	public static int ����(String ��˾��, String ��ַ, String ��˾��ҳ, String ��˾�绰, String ��˾��ַ) {
		String ��ҳ���� = ҳ�������.��ȡ(��ַ);
		Matcher ƥ�� = ��˾��ģģʽ.matcher(��ҳ����);
		String ��˾��ģ = null;
		if (ƥ��.find()) {
			��˾��ģ = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		// ��˾��ģ���޷�����������
		if (��˾��ģ == null) {
			return -1;
		}
		ƥ�� = ��˾����ģʽ.matcher(��ҳ����);
		String ��˾���� = null;
		if (ƥ��.find()) {
			��˾���� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		ƥ�� = ��˾��ҵģʽ.matcher(��ҳ����);
		String ��˾��ҵ = null;
		if (ƥ��.find()) {
			��˾��ҵ = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		int ��˾���ܿ�ʼ�±� = -1;
		for (int i = 0; i < ��˾����ǰ׺����.length; i++) {
			��˾���ܿ�ʼ�±� = ��ҳ����.indexOf(��˾����ǰ׺����[i], ��˾���ܿ�ʼ�±�);
			if (��˾���ܿ�ʼ�±� < 0) {
				break;
			}
		}
		String ��˾���� = null;
		if (��˾���ܿ�ʼ�±� >= 0) {
			��˾���ܿ�ʼ�±� += ��˾����ǰ׺����[��˾����ǰ׺����.length - 1].length();
			int ��˾���ܽ����±� = ��ҳ����.indexOf(��˾���ܺ�׺, ��˾���ܿ�ʼ�±�);
			if (��˾���ܽ����±� >= 0) {
				��˾���� = ��ҳ����.substring(��˾���ܿ�ʼ�±�, ��˾���ܽ����±�);
				��˾���� = �ַ����ӹ���.ȥ��װ�α�ǩ(��˾����);
			}
		}
		if (!��˾��.is�Ѻ��ù�˾(��˾��)) {
			return ��˾��.��ӹ�˾(��˾��, ��˾��ģ, ��˾����, ��˾��ҵ, ��˾����, ��˾��ҳ, ��˾�绰, ��˾��ַ);
		} else {
			return ��˾��.get��˾ID(��˾��);
		}
	}
}
