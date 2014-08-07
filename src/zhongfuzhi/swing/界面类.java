package zhongfuzhi.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import zhongfuzhi.core.������;

public class ������ extends JFrame {
	private static final long serialVersionUID = 6727507647346827736L;
	static {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}
	public static final ������ ���� = new ������();
	private JLabel �ؼ��ֱ�ǩ = new JLabel("�ؼ���:");
	private JComboBox �ؼ�����Ͽ� = new JComboBox();
	private JLabel ������ǩ = new JLabel("����:");
	private JComboBox ������Ͽ� = new JComboBox();
	private JButton ��¼��ť = new JButton("��¼");
	private JTextArea ��Ϣ����ı����� = new JTextArea(15, 30);
	private Thread ��¼�߳�;
	private boolean isֹͣ��¼;

	private ������() {
		setTitle("www.zhaopin.com�Զ���¼ϵͳ");
		���ùؼ�����Ͽ�();
		���õ�����Ͽ�();
		JPanel �Ϸ���� = new JPanel();
		�Ϸ����.add(�ؼ��ֱ�ǩ);
		�Ϸ����.add(�ؼ�����Ͽ�);
		�Ϸ����.add(������ǩ);
		�Ϸ����.add(������Ͽ�);
		�Ϸ����.add(��¼��ť);
		add(�Ϸ����, BorderLayout.NORTH);
		��Ϣ����ı�����.setEditable(false);
		JScrollPane ��ʾ���� = new JScrollPane(��Ϣ����ı�����);
		��ʾ����.setBorder(new TitledBorder("��Ϣ��ʾ����"));
		add(��ʾ����);
		��¼��ť.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (��¼��ť.getText().equals("��¼")) {
					��¼��ť.setEnabled(false);
					isֹͣ��¼ = false;
					��Ϣ����ı�����.setText(null);
					��¼�߳� = new Thread(new Runnable() {
						@Override
						public void run() {
							������.����(������Ͽ�.getEditor().getItem().toString(), �ؼ�����Ͽ�.getEditor().getItem().toString());
						}
					});
					��¼�߳�.start();
					��¼��ť.setText("��ͣ");
					��¼��ť.setEnabled(true);
				} else {
					��¼��ť.setEnabled(false);
					if (��¼�߳�.isAlive()) {
						isֹͣ��¼ = true;
					}
					��¼��ť.setText("��¼");
				}
			}
		});
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void ���õ�����Ͽ�() {
		������Ͽ�.setEditable(true);
		������Ͽ�.addItem("����");
		������Ͽ�.addItem("����");
	}

	private void ���ùؼ�����Ͽ�() {
		�ؼ�����Ͽ�.setEditable(true);
		�ؼ�����Ͽ�.addItem("�ֻ����");
	}

	public boolean isֹͣ��¼() {
		return isֹͣ��¼;
	}

	public void �����Ϣ(String ��Ϣ) {
		��Ϣ����ı�����.append(��Ϣ);
		��Ϣ����ı�����.setCaretPosition(��Ϣ����ı�����.getText().length());
	}

	public void ��֪ͨ��¼����() {
		��¼��ť.setText("��¼");
		��¼��ť.setEnabled(true);
	}
}
