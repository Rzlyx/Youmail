package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.HomePageImgDao;
import org.suNing.entity.HomePageImg;
import org.suNing.utli.BaseDao;

public class HomePageImgDaoImpl extends BaseDao implements HomePageImgDao {

	@Override
	public List<HomePageImg> getAllHomePageImg() {
		List<HomePageImg> list = new ArrayList<HomePageImg>();
		String sql = "select id,img from homePageImg";
		ResultSet res = super.executeQuery(sql);
		try {
			while(res.next()){
				HomePageImg hpi = new HomePageImg();
				hpi.setId(res.getInt("id"));
				hpi.setImg(res.getString("img"));
				list.add(hpi);
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
		return list;
	}

	@Override
	public int addHomePageImg(HomePageImg hpi) {
		String sql ="insert into homePageImg(img)values(?)";
		return super.executeUpdate(sql, hpi.getImg());
	}

	@Override
	public int UpdateHomePageImg(HomePageImg hpi) {
		String sql ="update homePageImg set img=?";
		return super.executeUpdate(sql, hpi.getImg());
	}

	@Override
	public int deleteHomePageImg(int hpi) {
		String sql ="delete from homePageImg where id =?";
		return super.executeUpdate(sql, hpi);
	}

}
