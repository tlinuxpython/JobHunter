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

import zhongfuzhi.core.搜索类;

public class 界面类 extends JFrame {
	private static final long serialVersionUID = 6727507647346827736L;
	static {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}
	public static final 界面类 单例 = new 界面类();
	private JLabel 关键字标签 = new JLabel("关键字:");
	private JComboBox 关键字组合框 = new JComboBox();
	private JLabel 地区标签 = new JLabel("地区:");
	private JComboBox 地区组合框 = new JComboBox();
	private JButton 收录按钮 = new JButton("收录");
	private JTextArea 信息输出文本区域 = new JTextArea(15, 30);
	private Thread 收录线程;
	private boolean is停止收录;

	private 界面类() {
		setTitle("www.zhaopin.com自动收录系统");
		设置关键字组合框();
		设置地区组合框();
		JPanel 上方面板 = new JPanel();
		上方面板.add(关键字标签);
		上方面板.add(关键字组合框);
		上方面板.add(地区标签);
		上方面板.add(地区组合框);
		上方面板.add(收录按钮);
		add(上方面板, BorderLayout.NORTH);
		信息输出文本区域.setEditable(false);
		JScrollPane 显示区域 = new JScrollPane(信息输出文本区域);
		显示区域.setBorder(new TitledBorder("信息显示区域"));
		add(显示区域);
		收录按钮.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (收录按钮.getText().equals("收录")) {
					收录按钮.setEnabled(false);
					is停止收录 = false;
					信息输出文本区域.setText(null);
					收录线程 = new Thread(new Runnable() {
						@Override
						public void run() {
							搜索类.搜索(地区组合框.getEditor().getItem().toString(), 关键字组合框.getEditor().getItem().toString());
						}
					});
					收录线程.start();
					收录按钮.setText("暂停");
					收录按钮.setEnabled(true);
				} else {
					收录按钮.setEnabled(false);
					if (收录线程.isAlive()) {
						is停止收录 = true;
					}
					收录按钮.setText("收录");
				}
			}
		});
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void 设置地区组合框() {
		地区组合框.setEditable(true);
		地区组合框.addItem("北京");
		地区组合框.addItem("江西");
	}

	private void 设置关键字组合框() {
		关键字组合框.setEditable(true);
		关键字组合框.addItem("手机软件");
	}

	public boolean is停止收录() {
		return is停止收录;
	}

	public void 输出信息(String 信息) {
		信息输出文本区域.append(信息);
		信息输出文本区域.setCaretPosition(信息输出文本区域.getText().length());
	}

	public void 被通知收录结束() {
		收录按钮.setText("收录");
		收录按钮.setEnabled(true);
	}
}
