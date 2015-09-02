package com.wangboo.batchSql.comp;

import javax.swing.JOptionPane;

/**
 * 常用对话框
 * @author wangboo
 *
 */
public class CommonDialog {

	/**
	 * 显示普通对话框（带描述）
	 * @param msg
	 */
	public static void showOkDialog(String title, String msg) {
		JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_OPTION);
	}
	
}
