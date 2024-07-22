package com.offcn.Entity;

public class Trolley {
	private int tid;//主键自增
	private int uid;//关联用户表中用户编号
	private Users users;//辅助查询
	private String mid;//关联商品表中商品编号
	private Medicine medicine;//辅助查询
	private int number;//商品数量
	
	public Trolley() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Trolley(int tid, int uid, String mid, Medicine medicine, int number) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.mid = mid;
		this.medicine = medicine;
		this.number = number;
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "Trolley [tid=" + tid + ", uid=" + uid + ", users=" + users + ", mid=" + mid + ", medicine=" + medicine
				+ ", number=" + number + ", orders_number=" + "]";
	}
	
}
