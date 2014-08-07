package zhongfuzhi.core;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zhongfuzhi.database.��˾��;
import zhongfuzhi.database.ְλ������;
import zhongfuzhi.database.ְλ��;
import zhongfuzhi.swing.������;
import zhongfuzhi.util.�ַ����ӹ���;
import zhongfuzhi.util.����ʽ��ȡ��;
import zhongfuzhi.util.ҳ�������;

public class ְλҳ������� {
	// ��˾��ģʽ
	private static final Pattern ��˾��ģʽ = Pattern.compile("companylink3[^>]*>" + "([^<]+)" + "<");
	// ��˾URLģʽ
	private static final Pattern ��˾ҳ��URLģʽ = Pattern.compile("tocompanylink3'\\);\" href=\"" + "([^\"]+)" + "\"");
	// ��˾��ҳģʽ
	private static final Pattern ��˾��ҳģʽ = Pattern.compile("��˾��ҳ��" + "([^<]+)" + "<");
	// ��˾�绰ģʽ
	private static final Pattern ��˾�绰ģʽ = Pattern.compile("�棺" + "([^<]+)" + "<");
	// ��˾��ַģʽ
	private static final Pattern ��˾��ַģʽ = Pattern.compile("<div>��˾��ַ��" + "([^<]+)" + "<");
	// ְλ����ģʽ
	private static final Pattern ְλ����ģʽ = Pattern.compile("ְλ���</span><strong><[^>]+><[^>]+>" + "([^<]+)" + "<");
	// ְλ����ģʽ
	private static final Pattern ְλ����ģʽ = Pattern.compile("inner-left fl\">[\\s]+<h1>" + "([^<]+)" + "<");
	// ��������ģʽ
	private static final Pattern ��������ģʽ = Pattern.compile("�������飺</span><strong>" + "([^<]+)" + "<");
	// ��������ģʽ
	private static final Pattern ��������ģʽ = Pattern.compile("�������ʣ�</span><strong>" + "([^<]+)" + "<");
	// ���ѧ��ģʽ
	private static final Pattern ���ѧ��ģʽ = Pattern.compile("���ѧ����</span><strong>" + "([^<]+)" + "<");
	// ְλ��нģʽ
	private static final Pattern ְλ��нģʽ = Pattern.compile("ְλ��н��</span><strong>" + "([^<]+)" + "<");
	// ��Ƹ����ģʽ
	private static final Pattern ��Ƹ����ģʽ = Pattern.compile("��Ƹ������</span><strong>" + "([^<]+)" + "<");
	// ְλ������ʼ�ַ���
	private static final String ְλ������ʼ�ַ��� = "<div class=\"tab-inner-cont\">";
	// ְλ���������ַ���
	private static final String ְλ���������ַ��� = "</div>";
	// ְλ������ǩ��ʼģʽ
	private static final Pattern ְλ������ǩ��ʼģʽ = Pattern.compile("<div[^>]+>");
	// ��������ģʽ
	private static final Pattern ��������ģʽ = Pattern.compile("freshdate\">" + "([^<]+)" + "<");
	// �����ص�ģʽ
	private static final Pattern �����ص�ģʽ = Pattern.compile("�����ص㣺</span><strong><a[^>]+>" + "([^<]+)" + "<");

	public static void ����(String ��ַ) {
		String ��ҳ���� = ҳ�������.��ȡ(��ַ);
		// ��˾��
		Matcher ƥ�� = ��˾��ģʽ.matcher(��ҳ����);
		String ��˾�� = null;
		int ��˾ID = -1;
		if (ƥ��.find()) {
			��˾�� = ����ʽ��ȡ��.��ȡ(ƥ��);
		} else {
			// ��˾����������������˵��ҳ�����룬����
			������.����.�����Ϣ(String.format("����%sʧ��\n", ��ַ));
			return;
		}
		String ְλ���� = null;
		ƥ�� = ְλ����ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			ְλ���� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		boolean is�и��� = false;
		Date �������� = null;
		ƥ�� = ��������ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			�������� = Date.valueOf(����ʽ��ȡ��.��ȡ(ƥ��));
		}
		int ְλID = ְλ��.getID(ְλ����, ��˾��);
		if (ְλID >= 0) {
			������.����.�����Ϣ(String.format("<%s,%s>�Ѵ��ڣ�", ��˾��, ְλ����));
			Date ԭ������ = ְλ��.get��������(ְλID);
			if (��������.after(ԭ������)) {
				is�и��� = true;
				������.����.�����Ϣ(String.format("��%s is after %s�������\n", ��������, ԭ������));
			} else {
				������.����.�����Ϣ("�������\n");
				return;
			}
		} else {
			������.����.�����Ϣ(String.format("׼�������¼�¼:<%s,%s>\n", ��˾��, ְλ����));
		}
		if (!��˾��.is�Ѻ��ù�˾(��˾��)) {
			// ��˾URLģʽ
			ƥ�� = ��˾ҳ��URLģʽ.matcher(��ҳ����);
			String ��˾URL = null;
			if (ƥ��.find()) {
				��˾URL = ����ʽ��ȡ��.��ȡ(ƥ��);
				String ��˾��ҳ = null;
				String ��˾�绰 = null;
				String ��˾��ַ = null;
				ƥ�� = ��˾��ҳģʽ.matcher(��ҳ����);
				if (ƥ��.find()) {
					��˾��ҳ = ����ʽ��ȡ��.��ȡ(ƥ��);
				}
				ƥ�� = ��˾�绰ģʽ.matcher(��ҳ����);
				if (ƥ��.find()) {
					��˾�绰 = ����ʽ��ȡ��.��ȡ(ƥ��);
				}
				ƥ�� = ��˾��ַģʽ.matcher(��ҳ����);
				if (ƥ��.find()) {
					��˾��ַ = ����ʽ��ȡ��.��ȡ(ƥ��);
				}
				��˾ID = ��˾ҳ�������.����(��˾��, ��˾URL, ��˾��ҳ, ��˾�绰, ��˾��ַ);
				if (��˾ID < 0) {
					������.����.�����Ϣ(String.format("�޷�������˾ҳ�棺%s���Թ���ְλ��Ϣ\n", ��˾URL));
					return;
				}
			} else {
				// ��˾url�Ҳ����򷵻�
				������.����.�����Ϣ(String.format("�޷�������˾URL��%s���Թ���ְλ��Ϣ\n", ��˾URL));
				return;
			}
		} else {
			��˾ID = ��˾��.get��˾ID(��˾��);
		}
		String ְλ���� = null;
		ƥ�� = ְλ����ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			ְλ���� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		int ְλ����ID = -1;
		if (ְλ������.is�Ѻ�������(ְλ����)) {
			ְλ����ID = ְλ������.get����ID(ְλ����);
		} else {
			ְλ����ID = ְλ������.�������(ְλ����);
		}
		String �������� = null;
		ƥ�� = ��������ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			�������� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		String �������� = null;
		ƥ�� = ��������ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			�������� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		String ���ѧ�� = null;
		ƥ�� = ���ѧ��ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			���ѧ�� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		String ְλ��н = null;
		ƥ�� = ְλ��нģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			ְλ��н = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		String ��Ƹ���� = null;
		ƥ�� = ��Ƹ����ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			��Ƹ���� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		String ְλ���� = �ַ����ӹ���.��ȡ��ǩ������(��ҳ����, ְλ������ʼ�ַ���, ְλ���������ַ���, ְλ������ǩ��ʼģʽ);
		ְλ���� = �ַ����ӹ���.ȥ��װ�α�ǩ(ְλ����);
		String �����ص� = null;
		ƥ�� = �����ص�ģʽ.matcher(��ҳ����);
		if (ƥ��.find()) {
			�����ص� = ����ʽ��ȡ��.��ȡ(ƥ��);
		}
		if (is�и���) {
			ְλ��.����(ְλID, ��������, ��������, ���ѧ��, ְλ��н, ��Ƹ����, ְλ����, ��������, �����ص�, ְλ����ID);
		} else {
			ְλ��.���(ְλ����, ��������, ��������, ���ѧ��, ְλ��н, ��Ƹ����, ְλ����, ��������, �����ص�, ְλ����ID, ��˾ID);
		}
	}
}
