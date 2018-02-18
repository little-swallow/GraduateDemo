package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.demo.bean.SourceBean;

public class SourceDao {
	DBconnection dBconnection = new DBconnection();
	
	public boolean uploadfile(SourceBean sourceBean,int id) throws Exception{
		Connection conn = dBconnection.getConn();
		String sql = "insert into source_table values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement prest = dBconnection.getprep(conn, sql);	
		prest.setString(1, sourceBean.getSname());
		prest.setString(2, sourceBean.getSuname());
		prest.setString(3, sourceBean.getDescribe());
		prest.setString(4, sourceBean.getSourcesort());
		prest.setString(5, sourceBean.getIntro());
		prest.setInt(6, id);
		prest.setInt(7, sourceBean.getViewcount());
		prest.setInt(8, sourceBean.getDlcount());
		prest.setString(9, sourceBean.getSdate());
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public ArrayList<SourceBean> selectsource() throws Exception{
		ArrayList<SourceBean> Sourceinfo = new ArrayList<SourceBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sid,Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sview,Sdownload,Stime from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid order by Sview desc";
		prest = dBconnection.getprep(connection, sql);
		rs = prest.executeQuery();
		while (rs.next()) {
			SourceBean sourceBean = new SourceBean();
			sourceBean.setSid(rs.getInt("Sid"));
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setViewcount(rs.getInt("Sview"));
			sourceBean.setDlcount(rs.getInt("Sdownload"));
			sourceBean.setSdate(rs.getString("Stime"));
			Sourceinfo.add(sourceBean);
		}
		return Sourceinfo;
	} 
	
	public ArrayList<SourceBean> selectbysort(String sort) throws Exception{
		ArrayList<SourceBean> Sourceinfo = new ArrayList<SourceBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sid,Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sview,Sdownload,Stime from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid and Ssort=? order by Sview desc";
		prest = dBconnection.getprep(connection, sql);
		prest.setString(1, sort);
		rs = prest.executeQuery();
		while (rs.next()) {
			SourceBean sourceBean = new SourceBean();
			sourceBean.setSid(rs.getInt("Sid"));
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setViewcount(rs.getInt("Sview"));
			sourceBean.setDlcount(rs.getInt("Sdownload"));
			sourceBean.setSdate(rs.getString("Stime"));
			Sourceinfo.add(sourceBean);
		}
		return Sourceinfo;
	} 
	
	public ArrayList<SourceBean> selectbyuid(int id) throws Exception{
		ArrayList<SourceBean> Sourceinfo = new ArrayList<SourceBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sid,Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sview,Sdownload,Stime from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid and user_table.Uid=? order by Sview desc";
		prest = dBconnection.getprep(connection, sql);
		prest.setInt(1, id);
		rs = prest.executeQuery();
		while (rs.next()) {
			SourceBean sourceBean = new SourceBean();
			sourceBean.setSid(rs.getInt("Sid"));
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setViewcount(rs.getInt("Sview"));
			sourceBean.setDlcount(rs.getInt("Sdownload"));
			sourceBean.setSdate(rs.getString("Stime"));
			Sourceinfo.add(sourceBean);
		}
		return Sourceinfo;
	} 
	
	public SourceBean singleinfo(int index) throws Exception {
		SourceBean sourceBean = new SourceBean();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sintro,Stime,Sview from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid and Sid=?";
		prest = dBconnection.getprep(connection, sql);
		prest.setInt(1, index);
		rs = prest.executeQuery();
		while (rs.next()) {
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setIntro(rs.getString("Sintro"));
			sourceBean.setSdate(rs.getString("Stime"));
			sourceBean.setViewcount(rs.getInt("Sview"));
		}
		return sourceBean; 		
	}	
	
	public boolean updateview(int sid,int count) throws Exception{
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		String sql = "update source_table set Sview=? where Sid = ?";
		prest = dBconnection.getprep(connection, sql);
		prest.setInt(1,count);
		prest.setInt(2,sid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;	
	} 
	
//	public int selectdown(String sname) throws Exception {
//		int count = 0;
//		Connection connection  = dBconnection.getConn();
//		PreparedStatement prest =  null;
//		ResultSet rs = null;
//		String sql = "select Sdownload from source_table where Suname = ?";
//		prest = dBconnection.getprep(connection, sql);
//		prest.setString(1, sname);
//		rs = prest.executeQuery();
//		while (rs.next()) {
//			count = rs.getInt("Sdownload");
//		}
//		return count; 		
//	}	
//	
	public boolean updatedown(String sname) throws Exception{
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		String sql = "update source_table set Sdownload=Sdownload+1 where Suname = ?";
		prest = dBconnection.getprep(connection, sql);
		prest.setString(1,sname);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;	
	} 
	
	public boolean deletefile(String suname) throws Exception{
		Connection conn = dBconnection.getConn();
		String sql = "delete from source_table where Suname= ?";
		PreparedStatement prest = dBconnection.getprep(conn, sql);	
		prest.setString(1, suname);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public ArrayList<SourceBean> searchsource(String search) throws Exception{
		ArrayList<SourceBean> Sourceinfo = new ArrayList<SourceBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sid,Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sview,Sdownload,Stime from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid and "
				+ "(Ssort like ? or Sdiscribe like ?) order by Sview desc";
		prest = dBconnection.getprep(connection, sql);
		prest.setString(1, "%"+search+"%");
		prest.setString(2, "%"+search+"%");
		rs = prest.executeQuery();
		while (rs.next()) {
			SourceBean sourceBean = new SourceBean();
			sourceBean.setSid(rs.getInt("Sid"));
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setViewcount(rs.getInt("Sview"));
			sourceBean.setDlcount(rs.getInt("Sdownload"));
			sourceBean.setSdate(rs.getString("Stime"));
			Sourceinfo.add(sourceBean);
		}
		return Sourceinfo;
	}
	
	public ArrayList<SourceBean> selectallsource() throws Exception{
		ArrayList<SourceBean> Sourceinfo = new ArrayList<SourceBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Sid,Sname,Suname,Sdiscribe,Ssort,Uname,"
				+ "Sview,Sdownload,Stime from source_table,user_table "
				+ "where source_table.Uid = user_table.Uid";
		prest = dBconnection.getprep(connection, sql);
		rs = prest.executeQuery();
		while (rs.next()) {
			SourceBean sourceBean = new SourceBean();
			sourceBean.setSid(rs.getInt("Sid"));
			sourceBean.setSname(rs.getString("Sname"));
			sourceBean.setSuname(rs.getString("Suname"));
			sourceBean.setDescribe(rs.getString("Sdiscribe"));
			sourceBean.setSourcesort(rs.getString("Ssort"));
			sourceBean.setUpname(rs.getString("Uname"));
			sourceBean.setViewcount(rs.getInt("Sview"));
			sourceBean.setDlcount(rs.getInt("Sdownload"));
			sourceBean.setSdate(rs.getString("Stime"));
			Sourceinfo.add(sourceBean);
		}
		return Sourceinfo;
	} 
}
