package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.suNing.dao.UserOrderFormDao;
import org.suNing.entity.UserOrderForm;
import org.suNing.utli.BaseDao;

public class UserOrderFormDaoImpl extends BaseDao implements UserOrderFormDao {

	@Override
	public int addOrderForm(UserOrderForm uof) {
		
		String sql = "insert into Tbl_UserOrderForm(orderFormNumber,userAccount,commodityId,addCommodityCount,"
								+ "orderFormStateId,nextOneTime)values(?,?,?,?,1,getdate())";
		Object[] params = {uof.getOrderFormNumber(),uof.getUserAccount(),uof.getCommodityId(),uof.getAddCommodityCount()};
		return super.executeUpdate(sql, params);
	}
	/**
	 * 这个是通过购物车来添加商品订单的
	 * @param uof
	 * @return
	 */
	public int addOrderForm2(UserOrderForm uof) {
			
			String sql = "insert into Tbl_UserOrderForm(orderFormNumber,userAccount,commodityId,addCommodityCount,"
									+ "orderFormStateId,nextOneTime)values(?,?,?,?,2,getdate())";
			Object[] params = {uof.getOrderFormNumber(),uof.getUserAccount(),uof.getCommodityId(),uof.getAddCommodityCount()};
			return super.executeUpdate(sql, params);
		}
	
	
	@Override
	public List<UserOrderForm> getAllUserOrderForm(int pageNum, String userAccount, int orderFormStatus) {
		List<UserOrderForm> uofList = new ArrayList<UserOrderForm>();
		String sql = "select top 10 o.id oid,o.orderFormNumber 订单编号,u.userAccount 账户号,  C.id cid,  "
				+ "  C.commodityName 名称,c.commodityPrice 价格, "
				+ " C.commodityBrandName 品牌,C.commodityImage 图片,s.id sid ,s.OrderFormType 订单状态, "
				+ " o.addCommodityCount 下单数量,o.nextOneTime 下单时间 "
				+ " from Tbl_UserOrderForm as O inner join Tbl_OrderFormState as S on O.orderFormStateId=S.id  "
				+ " inner join Tbl_Commodity as C on O.commodityId=C.id inner join Tbl_User as U on O.userAccount=U.userAccount "
				+ " where o.id not in "
				+ " (select top (?) od.id from Tbl_UserOrderForm od inner join Tbl_OrderFormState fs on od.orderFormStateId=fs.id  "
				+ "  inner join Tbl_User us on od.userAccount=us.userAccount "
				+ " where 1=1 ";
		
		List<Object> list = new ArrayList<Object>();
		list.add((pageNum-1)*10);
		sql += conditionSelect(list, userAccount, orderFormStatus);
		sql+="  order by od.id  desc  ";
		sql+= " ) ";
		sql+= conditionSelect(list, userAccount, orderFormStatus);
		sql+="  order by o.id  desc  ";
		
		Object[] params = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			params[i] = list.get(i);
		}
		ResultSet res = super.executeQuery(sql, params);
		
		try {
			while(res.next()){
				UserOrderForm order = new UserOrderForm();
				order.setId(res.getInt("oid"));
				order.setOrderFormNumber(res.getString("订单编号"));
				order.setUserAccount(res.getString("账户号"));
				order.setCommodityId(res.getInt("cid"));
				order.getCommodity().setCommodityName(res.getString("名称"));
				order.getCommodity().setCommodityPrice(res.getDouble("价格"));
				order.getCommodity().setCommodityBrandName(res.getString("品牌"));
				order.getCommodity().setCommodityImage(res.getString("图片"));
				order.setOrderFormStateId(res.getInt("sid"));
				order.getOfs().setOrderFormType(res.getString("订单状态"));
				order.setAddCommodityCount(res.getInt("下单数量"));
				order.setNextOneTime(res.getTimestamp("下单时间"));
				uofList.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uofList;
	}
	
	
	public String conditionSelect(List<Object> list, String userAccount, int orderStatus ){
		
		String sql = "  and O.userAccount like ?  ";
		list.add("%"+userAccount+"%");
		if(orderStatus > 0){
			sql+= "  and orderFormStateId = ?  ";
			list.add(orderStatus);
		}
		return sql;
	}
	
	
	
	@Override
	public int getConditionUserOrderForm(String userAccount, int orderFormStatus) {
		int result = 0;
		String sql = "select count(1) c from Tbl_UserOrderForm O where 1=1  ";
		List<Object> objs = new ArrayList<Object>();
		sql+= conditionSelect(objs, userAccount, orderFormStatus);
		Object[] params = new Object[objs.size()];
		for (int i = 0; i < objs.size(); i++) {
			params[i] = objs.get(i);
			
		}
		ResultSet res  = super.executeQuery(sql, params);
		try {
			if(res.next()){
				result=res.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteOrderForm(int id) {
		
		String sql ="delete from Tbl_UserOrderForm where id = ?";
		return super.executeUpdate(sql, id);
	}

	@Override
	public int updateUserOrderState(int orderFomrStateId, int OrderFormId) {
		
		String sql = "update Tbl_UserOrderForm set orderFormStateId =? where id = ?";
		
		return super.executeUpdate(sql, orderFomrStateId,OrderFormId);
	}

	@Override
	public int getNewAddOrderForm() {

		String sql = "select top 1 id from Tbl_UserOrderForm order by id desc";
		int result = 0;
		ResultSet res = super.executeQuery(sql);
		try {
			if(res.next()){
				result = res.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
