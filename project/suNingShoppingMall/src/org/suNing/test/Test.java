package org.suNing.test;

import java.util.List;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.UpCityManagerDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.UpCityManagerDaoImpl;
import org.suNing.entity.Commodity;
import org.suNing.entity.UpCityManager;

public class Test {

	public static void main(String[] args) {
		
		
//		UpCityManagerDao man = new UpCityManagerDaoImpl();
//		
//		UpCityManager city =  man.getManager("1697129", "456789");
//		
//		if(city != null){
//			System.out.println("用户名为："+city.getManagerAccount()+"\n"+"密码为："+city.getManagerPwd());
//		}else{
//			System.out.println("账户或密码错误");
//		}
		
		
		
		/*CommodityDao dao = new CommodityDaoImpl();
		Commodity sp = new Commodity();
		sp.setCommodityName("米");
		
		sp.setCommodityStatusTypeId(Integer.parseInt(""));
		
		List<Commodity> com = dao.passPageConditionGetCommodity(2, 1, sp);
		for (Commodity mm : com) {
				System.out.println(mm.getCommodityName());
		}
*/
	}

}
