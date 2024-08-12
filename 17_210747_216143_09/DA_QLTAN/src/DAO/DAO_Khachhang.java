/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.KhachHang;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class DAO_Khachhang {
    static Connection conn;
    String MaKH, TenKH, DiaChi, SDT;
    int TrangThai;

    public void Ketnoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<KhachHang> LaydsKH(){
        ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
        try {
            Ketnoi();
            String sql="select * from KHACHHANG where TRANGTHAI = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                KhachHang lsp = new KhachHang(rs.getString("MAKH"),rs.getString("TENKH"),rs.getString("DIACHI"),rs.getString("SDT"), rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    
    public void ThemKH(KhachHang kh){
        try {
            Ketnoi();
            CallableStatement st = conn.prepareCall("{call insert_KhachHang(?,?,?,?)}");
            st.setString(1, kh.MaKH);
            st.setString(2, kh.TenKH);
            st.setString(3, kh.DiaChi);
            st.setString(4, kh.SDT);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void XoaKH(KhachHang kh){
        try {
            Ketnoi();
            CallableStatement st=conn.prepareCall("{call Delete_KhachHang(?)}");
            st.setString(1, kh.MaKH);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SuaKH(KhachHang kh){
        try {
            Ketnoi();
            //String sql="insert into LOAISANPHAM values (MALOAISP, LOAISP)";
            CallableStatement st = conn.prepareCall("(edit_KhachHang(?,?,?,?))");
            st.setString(1, kh.MaKH);
            st.setString(2, kh.TenKH);
            st.setString(3, kh.DiaChi);
            st.setString(4, kh.SDT);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<KhachHang> TimKiemKH(String giatri){
        ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
        try {
            Ketnoi();
            CallableStatement st=conn.prepareCall("{call DanhsachKhanhHang_TheoTenKH(?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                KhachHang lsp = new KhachHang(rs.getString("MAKH"),rs.getString("TENKH"),rs.getString("DIACHI"),rs.getString("SDT"), rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    
    public String PhatSinhMa() {
        String MaKH = "";
        try {
            Ketnoi();
            CallableStatement st = conn.prepareCall("{call KHLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MAKH");
                String so = ma.substring(4).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                //System.out.println("ma"+MaSo);
                if (mamoi>= 1 && mamoi <= 9)
                    MaKH = "KH" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi  <= 99)
                    MaKH = "KH" + "0" + mamoi;
                else
                    MaKH = "KH" + mamoi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Khachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaKH;
    }
}
