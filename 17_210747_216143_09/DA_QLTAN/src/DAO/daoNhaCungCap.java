/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.NhaCungCapClass;
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
 * @author KHAI
 */
public class daoNhaCungCap {
    static Connection conn;
    String MaNCC, TenNCC, SDT, DiaChi;
    int TrangThai;
    public void KetNoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<NhaCungCapClass> LaydsNhaCungCap(){
        ArrayList<NhaCungCapClass> ds = new ArrayList<NhaCungCapClass>();
        try {
            KetNoi();
            String sql="select * from NHACUNGCAP where TRANGTHAI = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                NhaCungCapClass lncc = new NhaCungCapClass(rs.getString("MANCC"),rs.getString("TENNCC"),rs.getString("SDT"),rs.getString("DIACHI"), rs.getInt("TRANGTHAI"));
                ds.add(lncc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    public void ThemNhaCungCap(NhaCungCapClass lncc)
    {
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call insert_NhaCungCap(?,?,?,?)}");
            st.setString(1, lncc.MaNCC);
            st.setString(2, lncc.TenNCC);
            st.setString(3, lncc.SDT);
            st.setString(4, lncc.DiaChi);
           //st.setInt(3, lsp.TrangThai);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void XoaNhaCungCap(NhaCungCapClass lncc)
    {
        try {
            KetNoi();
            CallableStatement st=conn.prepareCall("{call Delete_NhaCungCap(?)}");
            st.setString(1, lncc.MaNCC);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void SuaNhaCungCap(NhaCungCapClass lncc) {
    try {
        KetNoi();
        // Sử dụng cú pháp chuẩn để gọi thủ tục lưu trữ
        CallableStatement st = conn.prepareCall("{call edit_NhaCungCap(?, ?, ?, ?)}");
        st.setString(1, lncc.MaNCC);
        st.setString(2, lncc.TenNCC);
        st.setString(3, lncc.SDT);
        st.setString(4, lncc.DiaChi);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public ArrayList<NhaCungCapClass> TimKiemNhaCungCap(String giatri){
        ArrayList<NhaCungCapClass> ds = new ArrayList<NhaCungCapClass>();
        try {
            KetNoi();
            CallableStatement st=conn.prepareCall("{call DanhsachNhaCungCap_TheoTenNCC(?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                NhaCungCapClass lncc = new NhaCungCapClass(rs.getString("MANCC"),rs.getString("TENNCC"),rs.getString("SDT"),rs.getString("DIACHI"), rs.getInt("TRANGTHAI"));
                ds.add(lncc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    public String PhatSinhMa() {
        String MaNCC = "";
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call NhaCungCapLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MANCC");
                String so = ma.substring(3).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                //System.out.println("ma"+MaSo);
                if (mamoi >= 1 && mamoi <= 9)
                    MaNCC = "NCC" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi  <= 99)
                    MaNCC = "NCC" + "0" + mamoi;
                else
                    MaNCC = "NCC" + mamoi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaNCC;
    }
}

