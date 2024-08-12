/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author PC
 */
public class DAO_NhanVien {
    static Connection conn;
    String MaNV, TenNV, Chucvu, SDT, GT, NVL;
    int Luong, TrangThai;
    
    
    public void Ketnoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<NhanVien> LaydsNV(){
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            Ketnoi();
            String sql="select * from NHANVIEN where TRANGTHAI = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                NhanVien nv = new NhanVien(MaNV, TenNV, Chucvu, SDT, GT, TrangThai, Luong, NVL);
                nv.setMaNV(rs.getString("MANV"));
                nv.setTenNV(rs.getString("TENNV"));
                nv.setChucVu(rs.getString("CHUCVU"));
                nv.setSDT(rs.getString("SDT"));
                //java.sql.Date NVL1 = rs.getDate("NGAYVAOLAM");
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                //String formatedDate = sdf.format(NVL1);
                //if(NVL1 != null  ){
                nv.setGt(rs.getString("GIOITINH"));
              
                //}
                nv.setTrangThai(rs.getInt("TRANGTHAI"));
                nv.setLuong(rs.getInt("LUONG"));
                nv.setNVL(rs.getString("NGAYVAOLAM"));
                ds.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    
    public void ThemNV(NhanVien nv){
        try {
            Ketnoi();
            CallableStatement st = conn.prepareCall("{call insert_NhanVien(?,?,?,?,?,?,?)}");
            st.setString(1, nv.MaNV);
            st.setString(2, nv.TenNV);
            st.setString(3, nv.ChucVu);
            st.setString(4, nv.SDT);
            //Date utilDate = nv.NVL;
            //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            //String formatedDate = sdf.format(sqlDate);
            st.setString(5, nv.NVL);
            st.setString(6, nv.gt);
            st.setInt(7, nv.Luong);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void XoaNV(NhanVien nv){
        try {
            Ketnoi();
            CallableStatement st=conn.prepareCall("{call Delete_NhanVien(?)}");
            st.setString(1, nv.MaNV);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SuaNV(NhanVien nv){
        try {
            Ketnoi();
            //String sql="insert into LOAISANPHAM values (MALOAISP, LOAISP)";
            CallableStatement st = conn.prepareCall("(edit_NhanVien(?,?,?,?,?,?,?))");
            st.setString(1, nv.MaNV);
            st.setString(2, nv.TenNV);
            st.setString(3, nv.ChucVu);
            st.setString(4, nv.SDT);
            //Date utilDate = nv.NVL;
            //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setString(5, nv.NVL);
            st.setString(6, nv.gt);
            st.setInt(7, nv.Luong);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<NhanVien> TimKiemNV(String giatri){
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            Ketnoi();
            CallableStatement st=conn.prepareCall("{call DanhsachNhanVien_TheoTenNV(?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                NhanVien nv = new NhanVien(MaNV, TenNV, Chucvu, SDT, GT, TrangThai, Luong, NVL);
                nv.setMaNV(rs.getString("MANV"));
                nv.setTenNV(rs.getString("TENNV"));
                nv.setChucVu(rs.getString("CHUCVU"));
                nv.setSDT(rs.getString("SDT"));
                //java.sql.Date NVL1 = rs.getDate("NGAYVAOLAM");
                //if(NVL1 != null  ){
                nv.setNVL(rs.getString("NGAYVAOLAM"));
                //}
                nv.setGt(rs.getString("GIOITINH"));
                nv.setLuong(rs.getInt("LUONG"));
                nv.setTrangThai(rs.getInt("TRANGTHAI"));
                ds.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    
    public String PhatSinhMa() {
        String MaNV = "";
        try {
            Ketnoi();
            CallableStatement st = conn.prepareCall("{call NVLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MANV");
                String so = ma.substring(4).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                //System.out.println("ma"+MaSo);
                if (mamoi>= 1 && mamoi <= 9)
                    MaNV = "NV" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi  <= 99)
                    MaNV = "NV" + "0" + mamoi;
                else
                    MaNV = "NV" + mamoi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaNV;
    }
}
