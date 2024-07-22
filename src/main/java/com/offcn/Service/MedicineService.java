package com.offcn.Service;

import java.util.List;

import com.offcn.Dao.MedicineDao;
import com.offcn.Entity.Medicine;
import com.offcn.utils.PageTool;

public class MedicineService {
	private MedicineDao medicineDao = new MedicineDao();

	// 查询药品数量
	public int getTotalCount(Medicine medicine) {
		return medicineDao.getTotalCount(medicine);
	}

	// 分页查询药品
	public List<Medicine> findMedicineByPage(Medicine medicine, PageTool pageTool) {
		return medicineDao.findMedicineByPage(medicine, pageTool);
	}

	// 添加药品
	public boolean insertMedicine(Medicine medicine) {
		return medicineDao.insertMedicine(medicine) > 0 ? true : false;
	}

	// 删除药品
	public boolean deleteMedicine(String ids) {
		return medicineDao.deleteMedicine(ids) > 0 ? true : false;
	}

	// 通过id查询药品
	public Medicine findMedicineByMid(String mid) {
		return medicineDao.findMedicineByMid(mid);
	}
	
	// 修改药品
	public boolean updateMedicine(Medicine medicine) {
		return medicineDao.updateMedicine(medicine) > 0 ? true : false;
	}

}
