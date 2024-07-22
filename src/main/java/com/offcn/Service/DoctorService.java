package com.offcn.Service;

import java.util.List;

import com.offcn.Dao.DoctorDao;
import com.offcn.Entity.Doctor;
import com.offcn.utils.PageTool;

public class DoctorService {
	private DoctorDao doctorDao = new DoctorDao();

	// 分页查询医生信息
	public List<Doctor> findDoctorsByPage(PageTool pageTool) {
		return doctorDao.findDoctorsByPage(pageTool);
	}

	// 查询医生数量
	public int getTotalCount() {
		return doctorDao.getTotalCount();
	}

	// 根据id查询医生
	public Doctor findDoctorByDid(int did) {
		return doctorDao.findDoctorsByDid(did);
	}

	// 修改医生信息
	public boolean updateDoctor(Doctor doctor) {
		return doctorDao.updateDoctor(doctor) > 0 ? true : false;
	}

	// 添加医生
	public boolean insertDoctor(Doctor doctor) {
		return doctorDao.insertDoctor(doctor) > 0 ? true : false;
	}

	// 删除医生
	public boolean deleteDoctor(String ids) {
		return doctorDao.deleteDoctor(ids) > 0 ? true : false;
	}

	// 分页查询医生
	public List<Doctor> findDoctorsByPage(Doctor doctor, PageTool pageTool) {
		return doctorDao.findDoctorsByPage(doctor, pageTool);
	}

	// 查询医生数量
	public int getTotalCount(Doctor doctor) {
		return doctorDao.getTotalCount(doctor);
	}

	// 根据科室查询医生
	public List<Doctor> findDoctorsByDepartment(int department) {
		return doctorDao.findDoctorsByDepartment(department);
	}

}
