/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO_NhanVien.conn;
import Models.HoaDon;
import Models.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class DAO_HoaDon {
    static Connection conn;
    String  DateOrder,MaHD;
    int thanhtien;
    
    public void Ketnoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<HoaDon> LaydsHD(){
        ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
        try {
            Ketnoi();
            String sql="select * from HOADON";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MAHD"));
                
                hd.setDateOrder(rs.getString("NGAYMUA"));
                hd.setThanhtien(rs.getInt("TONGTIEN"));
                hd.setTT(rs.getString("TRANGTHAI"));
                ds.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_HoaDon.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }

    /*public void ThemCTHD(HoaDon hd){
        try {
            Ketnoi();
            String sql = "INSERT INTO HOADON VALUES (?,?,?,?)";
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
    }*/
     public void ThemCTHD(HoaDon hd){
        try {
            Ketnoi();
            String sql = "INSERT INTO HOADON VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareCall(sql);
            st.setString(1, hd.MaHD);
            st.setString(2, hd.DateOrder);
            st.setInt(3, hd.thanhtien);
            st.setString(4,hd.TT);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void XoaKH(HoaDon hd){
        try {
            Ketnoi();
            CallableStatement st=conn.prepareCall("{call Delete_HoaDon(?)}");
            st.setString(1, hd.MaHD);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public String PhatSinhMa() {
        String MaHD = "";
        try {
            Ketnoi();
            CallableStatement st = conn.prepareCall("{call HDLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MAHD");
                String so = ma.substring(4).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                //System.out.println("ma"+MaSo);
                if (mamoi>= 1 && mamoi <= 9)
                    MaHD = "HD" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi  <= 99)
                    MaHD = "HD" + "0" + mamoi;
                else
                    MaHD = "HD" + mamoi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaHD;
    }
}
