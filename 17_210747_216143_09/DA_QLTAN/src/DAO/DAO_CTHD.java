/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.ChiTietHoaDon;
import Models.HoaDon;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class DAO_CTHD {
    static Connection conn;
    private int ID;
    private String MaSP;
    private int SoLuong, Gia;
    
    public void Ketnoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<ChiTietHoaDon> LaydsCTHD(){
        ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
        try {
            Ketnoi();
            String sql="select * from CHITIETHOADON";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                ChiTietHoaDon lsp = new ChiTietHoaDon(rs.getInt("ID"),rs.getString("MASP"),rs.getInt("SOLUONG"), rs.getInt("GIATIEN"));
                ds.add(lsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    
    public void ThemCTHD(ChiTietHoaDon cthd){
        try {
            Ketnoi();
            String sql = "INSERT INTO CHITIETHOADON VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareCall(sql);
            st.setInt(1, cthd.ID);
            st.setString(2, cthd.MaSP);
            st.setInt(3, cthd.SoLuong);
            st.setInt(4, cthd.Gia);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void XoaCTHD(int id){
        try {
            Ketnoi();
            String sql = "DELETE FROM CHITIETHOADON WHERE ID=?";
            PreparedStatement st = conn.prepareCall(sql);
            //CallableStatement st=conn.prepareCall("{call Delete_KhachHang(?)}");
            st.setInt(1, id);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
