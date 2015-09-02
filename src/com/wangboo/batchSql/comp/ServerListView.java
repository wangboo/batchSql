package com.wangboo.batchSql.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.wangboo.batchSql.DataUtil;

/**
 * 数据库连接组件
 * @author wangboo
 *
 */
public class ServerListView extends JScrollPane {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServerListView.class);
	public static final int SPACE_X = 3, SPACE_Y = 3;
	
	private List<Map<String, Object>> serverList;
	private List<ServerLabel> serverLabelList = new ArrayList<>();
	private JPanel content;
	private final int S_WIDTH = 120;

	public ServerListView() {
		content = new JPanel();
		setViewportView(content);
		setSize(S_WIDTH, 1000);
		content.setSize(S_WIDTH, 1000);
		content.setMinimumSize(new Dimension(S_WIDTH, 1000));
		content.setBackground(Color.red);
	}
	
	public void loadData() {
		serverList = DataUtil.loadServers();
//		content.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		int minSize = serverList.size() < 20 ? 20 : serverList.size();
		content.setLayout(new GridLayout(minSize, 1, 3, 3));
		for(ServerLabel sl : serverLabelList) {
			content.remove(sl);
		}
		serverLabelList.clear();
		for(int i=0;i<serverList.size();i++) {
			Map<String, Object> item = serverList.get(i);
			ServerLabel sl = new ServerLabel(item, i);
			content.add(sl);
			serverLabelList.add(sl);
			sl.init();
		}
	}
	
}
