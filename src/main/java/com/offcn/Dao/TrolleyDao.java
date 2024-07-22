package com.offcn.Dao;

import java.sql.SQLException;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offcn.Entity.Medicine;
import com.offcn.Entity.Trolley;

public class TrolleyDao {
	//连接数据库
	QueryRunner qrRunner = new QueryRunner(new ComboPooledDataSource());

	//添加至订单
	public int addTrolley(String ids) {
		int flag = 0;
		List<Medicine> medicine = null;
		//查询订单的药品类型
		String Select_sql = "SELECT * FROM medicine WHERE mid IN ("+ids+")";
		try {
			medicine = qrRunner.query(Select_sql, new BeanListHandler<Medicine>(Medicine.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//添加至订单表
		String Insert_sql = "INSERT INTO trolley(mid)VALUES(?)";
		for(Medicine m : medicine) {
			try {
				flag = qrRunner.update(Insert_sql,m.getMid());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(flag > 0) {
			return flag;
		}else {
			return 0;
		}
	}

	//根据mid查询药品订单全部购物记录
	public List<Trolley> findTrolleyList() {
		String sql = "SELECT * FROM trolley";
		try {
			return qrRunner.query(sql, new BeanListHandler<Trolley>(Trolley.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//修改商品数量
	public int updateNumber(int tid, int number) {
		String sql = "UPDATE trolley SET number=? WHERE tid=?";
		try {
			return qrRunner.update(sql,number,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	//根据购物车记录编号删除
	public int deleteTrolley(String tid) {
		String sql = "DELETE FROM trolley WHERE tid=?";
		try {
			return qrRunner.update(sql,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	//结算操作
	public int pay() {
		String sql = "DELETE FROM trolley";
			try {
				return qrRunner.update(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

}
