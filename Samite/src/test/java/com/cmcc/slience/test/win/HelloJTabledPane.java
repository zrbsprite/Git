package com.cmcc.slience.test.win;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JToolBar;

public class HelloJTabledPane {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					HelloJTabledPane window = new HelloJTabledPane();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelloJTabledPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 10, 414, 242);
		frame.getContentPane().add(tabbedPane);
		
		JTextPane textPane = new JTextPane();
		Icon icon = new ImageIcon(this.getClass().getResource("/com/cmcc/slience/http/dictionary/img/script.png"));
		tabbedPane.addTab(null,textPane);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		JLabel iconLabel = new JLabel("Test.java");
		iconLabel.setIcon(icon);
		panel.add(iconLabel);
		JLabel middle = new JLabel(" ");
		panel.add(middle);
		JLabel close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.dispose();
			}
		});
		panel.add(close);
		tabbedPane.setTabComponentAt(0, panel);
	}
}
