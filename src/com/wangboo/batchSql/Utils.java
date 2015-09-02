package com.wangboo.batchSql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

	/**
	 * ��ȡϵͳ��ǰ��Ŀ·��
	 * @return
	 */
	public static String projectPath() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * ��Ŀ·���µ��ļ�·��
	 * @param sufix
	 * @return
	 */
	public static String projectPathWithSufix(String sufix) {
		if (sufix.startsWith("/") || sufix.startsWith("\\\\")) {
			return projectPath() + sufix;
		}else {
			return projectPath() + "/" + sufix;
		}
	}
	
	/**
	 * ��ȡ�����ļ�
	 * @param name
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static String readConfig(String name) throws FileNotFoundException {
		File file = new File(name);
		InputStream fis = ClassLoader.getSystemResourceAsStream(name);
		byte[] bytes = new byte[(int) file.length()];
		try {
			fis.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {}
			}
		}
		return new String(bytes);
	}
	
	
}
