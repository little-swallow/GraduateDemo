package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.bean.AdminBean;

public class AdminDao {
	DBconnection dBconnection = new DBconnection();
	public boolean login(AdminBean adminBean) throws Exception{
		Connection connection = dBconnection.getConn();
		String sql = "select * from admin_table where Aname=? and Apass=?";
		ResultSet rs = null;
		PreparedStatement prest = dBconnection.getprep(connection, sql);
		prest.setString(1, adminBean.getAdname());
		prest.setString(2, adminBean.getAdpass());
		rs = prest.executeQuery();
		while(rs.next()){
			return true;
		}
		return false;
	}
}
