package com.cmcc.slience.http.dictionary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CloseTipWin extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public int code = 0;
	
	/**
	 * Create the dialog.
	 */
	private CloseTipWin(final JFrame frame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CloseTipWin.class.getResource("/com/cmcc/slience/http/dictionary/img/dic_logo.png")));
		setTitle("\u5173\u95ED\u63D0\u793A");
		setBounds(100, 100, 315, 207);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(getContentPane());
		} catch(Exception e){
			//使用默认
		}
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CloseTipWin.class.getResource("/com/cmcc/slience/http/dictionary/img/close_tip.png")));
		label.setBounds(37, 23, 32, 37);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u60A8\u70B9\u51FB\u4E86\u5173\u95ED\u6309\u94AE\uFF0C\u60A8\u662F\u60F3\uFF1F");
		label_1.setBounds(79, 33, 173, 15);
		getContentPane().add(label_1);
		
		final JRadioButton rb_ok = new JRadioButton("\u9690\u85CF\u4F46\u4E0D\u9000\u51FA");
		rb_ok.setBounds(95, 54, 121, 23);
		getContentPane().add(rb_ok);
		
		final JRadioButton rb_cancel = new JRadioButton("\u76F4\u63A5\u9000\u51FA");
		rb_cancel.setBounds(95, 79, 121, 23);
		getContentPane().add(rb_cancel);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb_ok);
		bg.add(rb_cancel);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rb_ok.isSelected()){
					final SystemTray st = SystemTray.getSystemTray();
					Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/com/cmcc/slience/http/dictionary/img/dic_logo.png"));
					PopupMenu popupMenu = new PopupMenu();// 创建托盘
					MenuItem opentItem = new MenuItem("打开");// 创建菜单
					opentItem.addActionListener(new ActionListener() {
						// 为我们的菜单添加事件
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(true);
						}

					});
					MenuItem exItem = new MenuItem("退出");
					exItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					popupMenu.add(opentItem);// 添加打开菜单
					popupMenu.add(exItem);// 添加退出菜单
					final TrayIcon trayIcon = new TrayIcon(image, "字典", popupMenu);
					trayIcon.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 2) {// 鼠标双击
								frame.setVisible(true);
								st.remove(trayIcon);
							}
						}
					});
					try {
						st.add(trayIcon);
						frame.setVisible(false);
					} catch (Exception e1) {
						//do nothing
					}
					dispose();
				}
				if(rb_cancel.isSelected()){
					System.exit(0);
				}
			}
		});
		button.setBounds(39, 125, 93, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(165, 125, 93, 23);
		getContentPane().add(button_1);
	}
	
	public static void showDialog(JFrame frame){
		CloseTipWin win = new CloseTipWin(frame);
		win.setVisible(true);
	}
}
