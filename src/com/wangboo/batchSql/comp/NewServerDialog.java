package com.wangboo.batchSql.comp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.wangboo.batchSql.DataUtil;
import com.wangboo.batchSql.MainFrame;
import com.wangboo.batchSql.mysql.MysqlClient;
import com.wangboo.batchSql.mysql.MysqlTestConnectionCallback;

/**
 * 新建服务器对话框
 * @author wangboo
 *
 */
public class NewServerDialog extends JDialog implements ActionListener, MysqlTestConnectionCallback {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(NewServerDialog.class);
	private static final int LABEL_HEIGHT = 50;
	private static final int LABEL_WIDTH = 380;
	
	private LabelAndInputFieldView serverNameView;
	private LabelAndInputFieldView ipView;
	private LabelAndInputFieldView portView;
	private LabelAndInputFieldView dbView;
	private LabelAndInputFieldView userView;
	private LabelAndInputFieldView pwdView;
	private MainFrame 			mainFrame;
	private JPanel			layout;
	private FlowLayout 		flowLayout;
	
	private JButton testConnBtn, submitBtn;
	
	
	public NewServerDialog(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 360);
		// 设置布局
		flowLayout = new FlowLayout(FlowLayout.LEADING, 10, 5);
		layout = new JPanel(flowLayout);
		setContentPane(layout);
		serverNameView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 	"    连接名");
		ipView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 			"  数据库ip");
		portView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 		"        端口");
		dbView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 			"  数据库名");
		userView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 		"     用户名");
		pwdView = new LabelAndInputFieldView(LABEL_WIDTH, LABEL_HEIGHT, 		"        密码");
		layout.add(serverNameView);
		layout.add(ipView);
		layout.add(portView);
		layout.add(dbView);
		layout.add(userView);
		layout.add(pwdView);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		testConnBtn = new JButton("测试连接");
		submitBtn = new  JButton("添加");
		panel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(testConnBtn, BorderLayout.WEST);
		panel.add(submitBtn, BorderLayout.EAST);
		testConnBtn.addActionListener(this);
		submitBtn.addActionListener(this);
		layout.add(panel);
	}

	public void showDialog() {
		setLocationRelativeTo(mainFrame);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == testConnBtn) {
			MysqlClient.testConnection(this, getConnectionUrl(), getUser(), getPwd());
		}else if(e.getSource() == submitBtn) {
			// 增加一个服务器配置
			DataUtil.addServer(getServerName(), getHost(), getPort(), getDB(), getUser(), getPwd());
			mainFrame.reloadServerList();
		}
		
	}
	
	public String getUser() {
		return userView.getIputString();
	}
	
	public String getPwd() {
		return userView.getIputString();
	}
	
	public String getHost() {
		return ipView.getIputString();
	}
	
	public int getPort() {
		return portView.getIputInteger();
	}
	
	public String getDB() {
		return dbView.getIputString();
	}
	
	public String getServerName() {
		return dbView.getIputString();
	}
	
	public String getConnectionUrl() {
		return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDB() + "?useUnicode=true&characterEncoding=utf8";
	}

	@Override
	public void testConnectionCallback(boolean isConnected, String failDesc) {
		log.debug("testConnectionCallback: isConnected : " + isConnected + ", failDesc = " + failDesc);
		if(isConnected) {
			CommonDialog.showOkDialog("连接成功！", "测试结果");
		}else {
			CommonDialog.showOkDialog("连接失败：" + failDesc, "测试结果");
		}
	}
	
}
