package com.offcn.Service;

import java.util.List;

import com.offcn.Dao.MedicineDao;
import com.offcn.Dao.TrolleyDao;
import com.offcn.Entity.Trolley;
import com.offcn.Entity.Users;

public class TrolleyService {
	private TrolleyDao trolleyDao = new TrolleyDao();
	private MedicineDao medicineDao = new MedicineDao();
	
	//添加至订单
	public boolean addTrolley(String ids) {
		return trolleyDao.addTrolley(ids)>0?true:false;
	}
	
	//查询药品订单的全部记录
	public List<Trolley> findTrolleyList() {
		List<Trolley> tlist = trolleyDao.findTrolleyList();
		for(Trolley trolley : tlist) {
			trolley.setMedicine(medicineDao.findMedicineByMid(trolley.getMid()+""));
		}
		return tlist;
	}

	//修改商品数量
	public boolean updateNumber(int tid, int number) {
		return trolleyDao.updateNumber(tid,number)>0?true:false;
	}

	//删除操作
	public boolean deleteTrolley(String tid) {
		return trolleyDao.deleteTrolley(tid)>0?true:false;
	}

	//结算操作
	public boolean pay() {
		return trolleyDao.pay()>0?true:false;
	}
	
}
