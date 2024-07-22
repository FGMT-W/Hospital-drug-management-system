package com.offcn.Service;

import java.util.List;

import com.offcn.Dao.DoctorDao;
import com.offcn.Dao.RegisterDao;
import com.offcn.Entity.Doctor;
import com.offcn.Entity.Register;
import com.offcn.utils.PageTool;

public class RegisterService {
	private RegisterDao registerDao = new RegisterDao();
	private DoctorDao doctorDao = new DoctorDao();

	// 分页查询挂号病人信息
	public List<Register> findRegisterByPage(PageTool pageTool) {
		return registerDao.findRegisterByPage(pageTool);
	}
	
	// 查询挂号病人数量
	public int getTotalCount(Register register) {
		return registerDao.getTotalCount(register);
	}

	// 分页查询挂号病人信息
	public List<Register> findRegistersByPage(Register register, PageTool pageTool) {
		List<Register> registers = registerDao.findRegisterByPage(register, pageTool);
		for (Register reg : registers) {
			reg.setDoctor(doctorDao.findDoctorsByDid(reg.getDid()));
		}
		return registers;
	}

	// 添加挂号病人
	public boolean insertRegister(Register register) {
		return registerDao.insertRegister(register) > 0 ? true : false;
	}

	// 通过id查询挂号病人
	public Register findRegisterByRid(String rid) {
		Register register = registerDao.findRegisterByRid(rid);
		register.setDoctor(doctorDao.findDoctorsByDid(register.getDid()));
		return register;
	}
	
	// 修改挂号病人
	public boolean updateRegister(Register register) {
		return registerDao.updateRegister(register)>0?true:false;
	}
	
	// 删除挂号病人
	public boolean deleteRegister(String ids) {
		return registerDao.deleteRegister(ids)>0?true:false;
	}
}
