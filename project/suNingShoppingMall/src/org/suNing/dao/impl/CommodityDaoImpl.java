package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.CommodityDao;
import org.suNing.entity.Commodity;
import org.suNing.entity.CommodityStatus;
import org.suNing.utli.BaseDao;

public class CommodityDaoImpl extends BaseDao implements CommodityDao {

	/**
	 *获取所有商品  商品销量调用这个
	 */
	
	public List<Commodity> getAllCommodity() {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = "select top 3  c.id as comId,c.commodityName,c.commodityPrice,c.commodityImage,B.id broadId  "
							+ "from Tbl_Commodity as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id "
									 + "inner join Tbl_CommodityBroad as B on L.commodityBroadId=B.id   order by c.commoditySalesVolume desc";
		ResultSet res = super.executeQuery(sql);
		try {
			while(res.next()){
				Commodity commodity = new Commodity();
				commodity.setId(res.getInt("comId"));
				commodity.setCommodityName(res.getString("commodityName"));
				commodity.setCommodityPrice(res.getDouble("commodityPrice"));
				commodity.setCommodityImage(res.getString("commodityImage"));
				commodity.setCommodityBroadId(res.getInt("broadId"));
				
				list.add(commodity);
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
		return list;
	}

	/**
	 * 通过商品大类别获取商品
	 */
	public List<Commodity> passCommodityBroadGet(int commodityCount,int commodityBroadId) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = "select top (?)  c.id as comId,c.commodityName,c.commodityPrice,c.commodityImage, B.id broId  "
							+ "from Tbl_Commodity as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id "
									 + "inner join Tbl_CommodityBroad as B on L.commodityBroadId=B.id  where B.id = ?  order by c.id desc";
		ResultSet res = super.executeQuery(sql, commodityCount,commodityBroadId);
		try {
			while(res.next()){
				Commodity commodity = new Commodity();
				commodity.setId(res.getInt("comId"));
				commodity.setCommodityName(res.getString("commodityName"));
				commodity.setCommodityPrice(res.getDouble("commodityPrice"));
				commodity.setCommodityImage(res.getString("commodityImage"));
				commodity.setCommodityBroadId(res.getInt("broId"));
				
				
				list.add(commodity);
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
		return list;
	}
	
	@Override
	public List<Commodity> searchResultConditionGetCommodity( int pageNum, Commodity condition) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = "select top 10 C.id comId, commodityTypeId,L.cltName, commodityName, commodityPrice, commodityBrandName, commodityInventory, commoditySalesVolume,  "
				+ "commodityBrowseCount, commodityImage, commodityStatusTypeId,S.commodityStatusType,B.id broadId  "
				+ "from Tbl_Commodity as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id  "
				+ " inner join Tbl_CommodityBroad as B on L.commodityBroadId=B.id  "
				+ " inner join Tbl_CommodityStatus as S on C.commodityStatusTypeId=S.id  "
				+ "where C.id not in(select top (?) co.id from Tbl_Commodity as co inner join Tbl_CommodityLittleType as li on co.commodityTypeId=li.id  "
				+ "where 1=1  and (co.commodityName like (?) or co.commodityBrandName like (?) or li.cltName like (?)  ))  "
				+ "and (C.commodityName like (?) or C.commodityBrandName like (?) or L.cltName like (?))";
		
		Object[] params = new Object[7];
		// 把查询条件循环赋值给sql 语句 
		for (int i = 0; i < params.length; i++) {
			if(i == 0){
				params[i] = (pageNum-1)*10;
			}else{
				params[i]="%"+condition.getCommodityName()+"%";
			}
		}
		ResultSet res = super.executeQuery(sql,params);
		try {
			while(res.next()){
				Commodity commodity = new Commodity();
				commodity.setId(res.getInt("comId"));
				commodity.setCommodityName(res.getString("commodityName"));
				commodity.setCommodityPrice(res.getDouble("commodityPrice"));
				commodity.setCommodityImage(res.getString("commodityImage"));
				commodity.setCommodityBroadId(res.getInt("broadId"));
				list.add(commodity);
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
		
		return list;
	}
	

	/**
	 * 分页查询 并且加条件
	 */
	public List<Commodity> passPageConditionGetCommodity(int pageSize, int pageNum, Commodity condition) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = "select top (?) C.id, commodityTypeId,L.cltName, commodityName, commodityPrice, "
				+ "commodityBrandName, commodityInventory, commoditySalesVolume, commodityBrowseCount, "
				+ "commodityImage, commodityStatusTypeId,S.commodityStatusType  "
				+ "from Tbl_Commodity as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id  "
				+ "inner join Tbl_CommodityStatus as S on C.commodityStatusTypeId=S.id  "
				+ "where C.id not in(select top (?) co.id from Tbl_Commodity as co inner join Tbl_CommodityLittleType as li on co.commodityTypeId=li.id   "
				+ "where 1=1";
		List<Object> listobj = new ArrayList<Object>();
		listobj.add(pageSize);
		listobj.add((pageNum-1)*pageSize);
		sql+=setCondition(condition, listobj);
		sql+=")";
		sql+=setCondition(condition, listobj);
		Object[] params = new Object[listobj.size()];
		for (int i = 0; i < listobj.size(); i++) {
			params[i] = listobj.get(i);
		}
		ResultSet res = super.executeQuery(sql, params);
		try {
			while(res.next()){
					Commodity commodity = new Commodity();
					commodity.setId(res.getInt("id"));
					commodity.setCommodityTypeId(res.getInt("commodityTypeId"));
					commodity.getCommodityLittle().setCltName(res.getString("cltName"));
					commodity.setCommodityName(res.getString("commodityName"));
					commodity.setCommodityPrice(res.getDouble("commodityPrice"));
					commodity.setCommodityBrandName(res.getString("commodityBrandName"));
					commodity.setCommodityInventory(res.getInt("commodityInventory"));
					commodity.setCommoditySalesVolume(res.getInt("commoditySalesVolume"));
					commodity.setCommodityBrowseCount(res.getInt("commodityBrowseCount"));
					commodity.setCommodityImage(res.getString("commodityImage"));
					commodity.setCommodityStatusTypeId(res.getInt("commodityStatusTypeId"));
					commodity.getCommodityStats().setCommodityStatusType(res.getString("commodityStatusType"));
					list.add(commodity);
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
		
		return list;
	}
	/**
	 * 拼接分页查询添加
	 * @param condition
	 * @param obj
	 * @return
	 */
	private String setCondition(Commodity condition,List<Object> obj){
		StringBuffer sql = new StringBuffer();
		if(condition != null){
			if(condition.getCommodityName() != null && !"".equals(condition.getCommodityName())){
				sql.append("  and (  commodityName like (?) or  commodityBrandName like (?) or  cltName like (?))");
				obj.add("%"+condition.getCommodityName()+"%");
				obj.add("%"+condition.getCommodityName()+"%");
				obj.add("%"+condition.getCommodityName()+"%");
			}
			if(condition.getCommodityTypeId() > 0){
				sql.append("  and  commodityTypeId=(?)");
				obj.add(condition.getCommodityTypeId());
			}
			if(condition.getCommodityStatusTypeId()>0){
				sql.append("  and  commodityStatusTypeId=(?)");
				obj.add(condition.getCommodityStatusTypeId());
			}
		}
		return sql.toString();
	}

	@Override
	public int getCommodityCount() {
		String sql = "select count(1) c from Tbl_Commodity";
		int result = 0;
		ResultSet res = super.executeQuery(sql);
		try {
			if(res.next()){
				result = res.getInt("c");
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
	/**
	 * 这是加上查询条件的 商品数量
	 * @param condition
	 * @return
	 */
	public int getCommodityCount(Commodity condition) {
		String sql = "select count(1) c from Tbl_Commodity  as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id where 1=1 ";
		int result = 0;
		List<Object>listParams = new ArrayList<Object>();
		sql+= setCondition(condition ,listParams);
		Object[] obj = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			obj[i] = listParams.get(i);
		}
		ResultSet res = super.executeQuery(sql,obj);
		try {
			if(res.next()){
				result = res.getInt("c");
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
	public int deleteCommodity(int id) {
		String sql = "delete from Tbl_Commodity where id = ?";
		return super.executeUpdate(sql,id);
	}

	/**
	 * 添加商品
	 */
	public int addCommodity(Commodity commodity) {
		
		String sql = "insert into Tbl_Commodity( commodityTypeId, commodityName, commodityPrice, commodityBrandName, "
				+ "commodityInventory, commodityImage, commodityStatusTypeId)  values(?,?,?,?,?,?,?)";
		Object[] params = {commodity.getCommodityTypeId(),commodity.getCommodityName(),commodity.getCommodityPrice(),
				commodity.getCommodityBrandName(),commodity.getCommodityInventory(),commodity.getCommodityImage(),
				commodity.getCommodityStatusTypeId()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public int getMaxCommodityId() {
		String sql = "select max(id) as id from Tbl_Commodity";
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
	@Override
	public Commodity idGetCommodity(int commodityId) {
		Commodity commodity = null;
		String sql = "select  c.id comid,c.commodityName,c.commodityTypeId,c.commodityStatusTypeId, c.commodityPrice,c.commodityImage,commodityInventory ,commodityBrandName, "
				+ "commoditySalesVolume ,commodityBrowseCount,S.commodityStatusType "
				+ "from Tbl_Commodity as C  inner join Tbl_CommodityLittleType as L on C.commodityTypeId=L.id  "
				+ "inner join Tbl_CommodityBroad as B on L.commodityBroadId=B.id "
				+ "inner join Tbl_CommodityStatus as S on C.commodityStatusTypeId=S.id "
				+ "where c.id = ? ";
		ResultSet res = super.executeQuery(sql, commodityId);
		try {
			if(res.next()){
				commodity = new Commodity();
				commodity.setId(res.getInt("comid"));
				commodity.setCommodityTypeId(res.getInt("commodityTypeId"));
				commodity.setCommodityName(res.getString("commodityName"));
				commodity.setCommodityPrice(res.getDouble("commodityPrice"));
				commodity.setCommodityBrandName(res.getString("commodityBrandName"));
				commodity.setCommodityInventory(res.getInt("commodityInventory"));
				commodity.setCommodityImage(res.getString("commodityImage"));
				commodity.setCommodityStatusTypeId(res.getInt("commodityStatusTypeId"));
				commodity.setCommodityBrowseCount(res.getInt("commodityBrowseCount"));
				commodity.setCommoditySalesVolume(res.getInt("commoditySalesVolume"));
				commodity.getCommodityStats().setCommodityStatusType(res.getString("commodityStatusType"));
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
		return commodity;
	}

	@Override
	public int updateCommodity(Commodity commodity) {

		String sql = "update Tbl_Commodity set commodityTypeId = ?,commodityName=?,commodityPrice=?, "
				+ "commodityBrandName=?,commodityInventory=?,commodityImage=?,commodityStatusTypeId=? where id = ?";
		Object[] params = {commodity.getCommodityTypeId(),commodity.getCommodityName(),commodity.getCommodityPrice(),
				commodity.getCommodityBrandName(),commodity.getCommodityInventory(),commodity.getCommodityImage(),
				commodity.getCommodityStatusTypeId(),commodity.getId()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public List<Commodity> typeIdGetCommodity(int typeId) {
		List<Commodity> list = new ArrayList<Commodity>();
		String sql = "select commodityTypeId, commodityName, commodityPrice, commodityBrandName, commodityInventory, "
				+ "commodityImage, commodityStatusTypeId from Tbl_Commodity where commodityTypeId= ?";
		ResultSet res = super.executeQuery(sql, typeId);
		try {
			while(res.next()){
				Commodity commodity = new Commodity();
				commodity.setCommodityTypeId(res.getInt("commodityTypeId"));
				commodity.setCommodityName(res.getString("commodityName"));
				commodity.setCommodityPrice(res.getDouble("commodityPrice"));
				commodity.setCommodityBrandName(res.getString("commodityBrandName"));
				commodity.setCommodityInventory(res.getInt("commodityInventory"));
				commodity.setCommodityImage(res.getString("commodityImage"));
				commodity.setCommodityStatusTypeId(res.getInt("commodityStatusTypeId"));
				list.add(commodity);
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
		return list;
	}

	@Override
	public int updateCommodityBrowseCount(int thisCount,int comId) {
		
		String sql = "update Tbl_Commodity set commodityBrowseCount=? where id = ?";
		
		return super.executeUpdate(sql, thisCount+1,comId);
	}

	@Override
	public int updateCommodityCount( int commodityCount,int commodityId) {
		String sql = "update Tbl_Commodity set commodityInventory-=? where id = ?";
		return super.executeUpdate(sql, commodityCount,commodityId);
	}

	@Override
	public int udpateCommoditySalesVolume(int commodityCount ,int commodityId) {
		
		String sql = "update Tbl_Commodity set commoditySalesVolume+=? where id = ?";
		
		return super.executeUpdate(sql, commodityCount,commodityId);
	}

	



	
	

}
