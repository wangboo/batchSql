package com.wangboo.batchSql;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.apache.log4j.Logger;

import com.wangboo.batchSql.comp.NewServerDialog;
import com.wangboo.batchSql.comp.ServerListView;

public class MainFrame extends JFrame implements ActionListener, MenuListener {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MainFrame.class);
	/**
	 * 新建服务器菜单
	 */
	private JMenu newServerMenu;
	private JPanel content;
	private ServerListView serverListView;
	public int height;
	public int width;
	
	public MainFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		int screenWidth = kit.getScreenSize().width;
		int sceenHeight = kit.getScreenSize().height;
		width = (int) (screenWidth / 1.5);
		height = (int) (sceenHeight / 1.5);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JFrame.setDefaultLookAndFeelDecorated(false);
		JMenuBar menuBar = new JMenuBar();
		newServerMenu = new JMenu("新建服务器");
		newServerMenu.addMenuListener(this);
		menuBar.add(newServerMenu);
		this.setJMenuBar(menuBar);
		// content 
		content = new JPanel(new BorderLayout(5, 5));
		setContentPane(content);
		// 坐标菜单
		serverListView = new ServerListView();
		serverListView.loadData();
		content.add(serverListView, BorderLayout.WEST);
	}
	
	public void showFrame() {
		setVisible(true);
	}
	
	/**
	 * 重新绘制左边数据库连接菜单
	 */
	public void reloadServerList() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		if(e.getSource() == newServerMenu) {
			// 新建服务器
			log.debug("show new server dialog");
			new NewServerDialog(this).showDialog();
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		
	}

}
