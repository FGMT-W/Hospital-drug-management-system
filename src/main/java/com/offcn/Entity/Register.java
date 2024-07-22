package com.offcn.Entity;

import java.util.Date;

public class Register {
	
	private String rid;
	private String name;
	private String idCard;
	private String siNumber;
	private double registerMoney;
	private String phone;
	private int isPay;
	private int sex;
	private int age;
	private int consultation;
	private int department;
	private int did;
	private Doctor doctor;
	private int status;
	private Date registerDate;
	private String remark; 
	
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Register(String rid, Doctor doctor) {
		super();
		this.rid = rid;
		this.doctor = doctor;
	}
	
	public Register(String rid, String name, String idCard, String siNumber, double registerMoney, String phone,
			int isPay, int sex, int age, int consultation, int department, int did, int status, Date registerDate,
			String remark) {
		super();
		this.rid = rid;
		this.name = name;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.isPay = isPay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.department = department;
		this.did = did;
		this.status = status;
		this.registerDate = registerDate;
		this.remark = remark;
	}
	
	public Register(String rid, String name, String idCard, String siNumber, double registerMoney, String phone,
			int isPay, int sex, int age, int consultation, int department, int did, Date registerDate, String remark) {
		super();
		this.rid = rid;
		this.name = name;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.isPay = isPay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.department = department;
		this.did = did;
		this.registerDate = registerDate;
		this.remark = remark;
	}
	
	public Register(String name, String idCard, String siNumber, double registerMoney, String phone, int isPay, int sex,
			int age, int consultation, int department, int did, Doctor doctor, int status, Date registerDate,
			String remark) {
		super();
		this.name = name;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.isPay = isPay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.department = department;
		this.did = did;
		this.doctor = doctor;
		this.status = status;
		this.registerDate = registerDate;
		this.remark = remark;
	}
	
	public Register(String rid, String name, String idCard, String siNumber, double registerMoney, String phone,
			int isPay, int sex, int age, int consultation, int department, int did, Doctor doctor, int status,
			Date registerDate, String remark) {
		super();
		this.rid = rid;
		this.name = name;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.isPay = isPay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.department = department;
		this.did = did;
		this.doctor = doctor;
		this.status = status;
		this.registerDate = registerDate;
		this.remark = remark;
	}
	

	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSiNumber() {
		return siNumber;
	}
	public void setSiNumber(String siNumber) {
		this.siNumber = siNumber;
	}
	public double getRegisterMoney() {
		return registerMoney;
	}
	public void setRegisterMoney(double registerMoney) {
		this.registerMoney = registerMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getConsultation() {
		return consultation;
	}
	public void setConsultation(int consultation) {
		this.consultation = consultation;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
