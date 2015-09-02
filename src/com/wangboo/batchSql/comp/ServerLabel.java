package com.wangboo.batchSql.comp;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JLabel;

/**
 * 单个服务器显示组件
 * @author wangboo
 *
 */
public class ServerLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> item;
	private int n;
	private static int WIDTH = 120, HEIGHT = 70;
	
	public ServerLabel(Map<String, Object> item, int n) {
		this.item = item;
		this.n = n;
		setText(getServerName());
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public String getServerName() {
		return (String) item.get("name");
	}
	
	public void init() {
		setBounds(ServerListView.SPACE_X, 
				n * (ServerListView.SPACE_Y + HEIGHT), 
				WIDTH, 
				HEIGHT);
	}
	
	
	
	public static int heightWithAmount(int space, int amt) {
		return (HEIGHT + space) * amt + space * 2;
	}
	
}
