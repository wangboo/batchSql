package com.wangboo.batchSql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

	/**
	 * 获取系统当前项目路径
	 * @return
	 */
	public static String projectPath() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * 项目路径下的文件路径
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
	 * 读取配置文件
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
