package com.lbl.thread.t8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @description: 模拟数据库连接池
 *               初始化时建立一定个数的连接放到linklist里，获取连接时去池中拿连接，如果池中没有连接则等待，释放一个连接时，可以叫醒获取连接的线程。
 * @author: libl
 * @date: 2019年5月25日
 */
public class MyDataSource {
	private static final int INIT_COUNT = 10;
	private static final String DRIVER_CLASS = "";
	private static final String URL = "";
	private static final String PASSWORD = "";
	private static final String USER = "";
	private static LinkedList<Connection> pool = new LinkedList<>();

	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();

	static {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public MyDataSource() {
		for (int i = 0; i < INIT_COUNT; i++) {
			try {
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pool.add(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		Connection result = null;
		lock.lock();
		try {
			while (pool.size() <= 0) {
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (!pool.isEmpty()) {
				result = pool.removeFirst();
			}
			return result;
		} finally {
			lock.unlock();
		}
	}

	public void release(Connection conn) {
		if (conn != null) {
			lock.lock();
			try {
				pool.add(conn);
				c.signal();
			} finally {
				lock.unlock();
			}
		}
	}

}
