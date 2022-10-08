package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.entity.CommodityBroad;
import org.suNing.entity.CommodityLittleType;
import org.suNing.utli.BaseDao;

/**
 * 商品小类 实现类
 * @author ASUS
 *
 */
public class CommodityLittleTypeDaoImpl extends BaseDao implements CommodityLittleTypeDao {

	@Override
	public List<CommodityLittleType> getAllCommodityLittle( int pageSize,int pageNum) {
		List<CommodityLittleType> listLittleType = new ArrayList<CommodityLittleType>();
		String sql = "select top (?) T.id 类别编号,T.cltName 类别名称,B.commodityBroadName 大类名称,B.id 大类id  "
				+ "from Tbl_CommodityLittleType as T inner join Tbl_CommodityBroad as B on T.commodityBroadId=B.id where T.id not in(select top (?) id from Tbl_CommodityLittleType) order by t.id";
		
		
		ResultSet res = super.executeQuery(sql, pageSize,(pageNum-1)*pageSize);
		
			try {
				while(res.next()){
					CommodityLittleType littleType = new CommodityLittleType();
					littleType.setId(res.getInt("类别编号"));
					littleType.setCltName(res.getString("类别名称"));
					littleType.getCommodityBroad().setId(res.getInt("大类id"));
					littleType.getCommodityBroad().setCommodityBroadName(res.getString("大类名称"));
					listLittleType.add(littleType);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return listLittleType;
	}

	@Override
	public CommodityLittleType getCommodityLittle(int littleType) {
		String sql = "select id,cltName,commodityBroadId from Tbl_CommodityLittleType where id = ?";
		CommodityLittleType little = null;
		ResultSet res = super.executeQuery(sql, littleType);
		try {
			if(res.next()){
				little = new CommodityLittleType();
				little.setId(res.getInt("id"));
				little.setCltName(res.getString("cltName"));
				little.setCommodityBroadId(res.getInt("commodityBroadId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return little;
	}

	@Override
	public int deleteCommodityLittle(int littleTypeId) {
		String sql = "delete from Tbl_CommodityLittleType where id = ?";
		return super.executeUpdate(sql, littleTypeId);
	}

	@Override
	public int updateCommodityLittle(CommodityLittleType littleType) {
		String sql = "update Tbl_CommodityLittleType set cltName=?,commodityBroadId= ? where id = ?";
		return super.executeUpdate(sql, littleType.getCltName(),littleType.getCommodityBroadId(),littleType.getId());
	}

	@Override
	public int addCommodityLittle(CommodityLittleType littleType) {
		String sql = "insert into Tbl_CommodityLittleType(cltName,commodityBroadId)values(?,?)";
		return super.executeUpdate(sql, littleType.getCltName(),littleType.getCommodityBroadId());
	}

	@Override
	public int getCommodityLittleCount() {
		String sql = "select count(1) c from Tbl_CommodityLittleType ";
		ResultSet res = super.executeQuery(sql);
		int result = 0;
		try {
			if(res.next()){
				result = res.getInt("c");
			}
		} catch (SQLException e) {
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
	public List<CommodityLittleType> getAllCommodityLittle() {
		List<CommodityLittleType> listLittleType = new ArrayList<CommodityLittleType>();
		String sql = "select T.id 类别编号,T.cltName 类别名称 from Tbl_CommodityLittleType as T ";
		
		
		ResultSet res = super.executeQuery(sql);
		
			try {
				while(res.next()){
					CommodityLittleType littleType = new CommodityLittleType();
					littleType.setId(res.getInt("类别编号"));
					littleType.setCltName(res.getString("类别名称"));
					listLittleType.add(littleType);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return listLittleType;
	}

	@Override
	public List<CommodityLittleType> getAllCommodityLittle(int broadTypeId) {
		List<CommodityLittleType> listLittleType = new ArrayList<CommodityLittleType>();
		String sql = "select T.id 类别编号,T.cltName 类别名称 from Tbl_CommodityLittleType as T  where  T.commodityBroadId = ?";
		
		
		ResultSet res = super.executeQuery(sql,broadTypeId);
		
			try {
				while(res.next()){
					CommodityLittleType littleType = new CommodityLittleType();
					littleType.setId(res.getInt("类别编号"));
					littleType.setCltName(res.getString("类别名称"));
					listLittleType.add(littleType);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return listLittleType;
	}

}
