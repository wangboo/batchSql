package com.wangboo.batchSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

public class DataUtil {
	
	private static Logger log = Logger.getLogger(DataUtil.class);
	private static Connection conn;
	private static String dbName = "data";

	public static void init() throws Exception {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:"+dbName);
//		String filename = Utils.projectPathWithSufix(dbName);
//		File dbFile = new File(filename);
//		if(dbFile.length() == 0) {
			createTable();
//		}
	}
	
	/**
	 * 创建数据库文件
	 */
	private static void createTable() throws Exception {
		log.debug("创建数据库文件");
		Statement stmt = conn.createStatement();
		try{
			log.debug("创建数据库：servers");
			stmt.executeUpdate(Utils.readConfig("servers.sql"));
		}catch(Exception e) {
			log.error("ִ创建数据库：server fail", e);
			throw e;
		}finally{
			stmt.close();
		}
	}
	
	/**
	 * 加载服务器配置列表
	 * @return
	 */
	public static List<Map<String, Object>> loadServers() {
		MapListHandler handler = new MapListHandler();
		QueryRunner q = new QueryRunner();
		try{
			return q.query(conn, "select * from servers", handler);
		}catch(Exception e) {
			log.error("loadServers error", e);
			return null;
		}
	}
	
	/**
	 * 增加意向服务器连接配置
	 * @param name
	 * @param ip
	 * @param port
	 * @param db
	 * @param user
	 * @param pwd
	 */
	public static void addServer(String name, String host, int port, String db, String user, String pwd) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = String.format("insert into servers(name,host,port,user,pwd,db) values ('%s','%s',%d,'%s','%s','%s')", 
					name, host, port, user, pwd, db);
			stmt.execute(sql);
		} catch (SQLException e) {
			log.error("addServer error : ", e);
		}finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 *  ---- test blow -------
	 */
	public static void test() {
		QueryRunner q = new QueryRunner();
		MapListHandler rsh = new MapListHandler(); 
		try {
			List<Map<String, Object>> list = q.query(conn, "select * from servers", rsh);
			System.out.println("list = " + list.size());
			for(Map<String, Object> item : list) {
				log.debug("item : " + item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		addServer("1", "192.168.1.182", 3033, "jiyu", "root", "root");
	}
}
