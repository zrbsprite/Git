package com.cmcc.slience.http.dictionary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.http.entity.ContentType;

@SuppressWarnings("serial")
public class BaiDuDictionaryWin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					BaiDuDictionaryWin frame = new BaiDuDictionaryWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaiDuDictionaryWin() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(getContentPane());
		} catch(Exception e){
			//使用默认
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(BaiDuDictionaryWin.class.getResource("/com/cmcc/slience/http/dictionary/img/dic_logo.png")));
		setResizable(false);
		setTitle("\u82F1\u6C49\u4E92\u8BD1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 369);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textPane.setFont(new Font("宋体", Font.PLAIN, 18));
		textPane.setEditable(false);
		textPane.setBounds(10, 91, 530, 240);
		textPane.setContentType(ContentType.TEXT_HTML.toString());
		contentPane.add(textPane);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if(text.equals("")){
					return;
				}
				String output = BaiduDictionaryUtil.run(text);
				if(output!=null){
					textPane.setText(output);
				}else{
					textPane.setText("抱歉,未能查询到结果！");
				}
			}
		});
		button.setIcon(new ImageIcon(BaiDuDictionaryWin.class.getResource("/com/cmcc/slience/http/dictionary/img/search.png")));
		button.setBounds(448, 14, 89, 46);
		contentPane.add(button);
		
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(textField.getText().equals("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD\u6216\u8BCD\u7EC4")){
					textField.setSelectionStart(0);
					textField.setSelectionEnd(0);
				}
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setSelectionStart(0);
				textField.setSelectionEnd(0);
			}
			@Override
			public void focusLost(FocusEvent e) {
				int end = textField.getSelectionEnd();
				if(end==0){
					textField.setForeground(Color.GRAY);
					textField.setText("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD\u6216\u8BCD\u7EC4");
				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getModifiers()!=KeyEvent.VK_BACK_SPACE){
					textField.setForeground(Color.BLACK);
					int end = textField.getSelectionEnd();
					textField.setText(textField.getText().substring(0,end));
				}
			}
		});
		textField.setForeground(Color.GRAY);
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setText("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD\u6216\u8BCD\u7EC4");
		textField.setToolTipText("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD\u6216\u8BCD\u7EC4");
		textField.setBounds(10, 14, 438, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		setLocationRelativeTo(null);
	}
	
	@Override  
    protected void processWindowEvent(WindowEvent e) {  
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
        	if(SystemTray.isSupported()){
        		CloseTipWin.showDialog(this);
        	}
        }else{
        	super.processWindowEvent(e); //该语句会执行窗口事件的默认动作(如：隐藏)  
        }
    }
}
