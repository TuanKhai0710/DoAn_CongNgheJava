/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.SanPhamClass;
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
 * @author KHAI
 */
public class daoSanPham {
    static Connection conn;

    public static void restoreProduct(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String MaSP, MaLoaiSP, TenSP, HinhAnh;
    public int GiaNhap, GiaSP, TrangThai;

    public void KetNoi(){
        try {
            String user="sa";
            String pass="khai754123";
            String url="jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String getTenLoaiSPFromMaLoaiSP(String maLoaiSP) {
        String tenLoaiSP = ""; // Khởi tạo tên loại sản phẩm

        // Thực hiện truy vấn để lấy tên loại sản phẩm từ mã loại sản phẩm
        try {
            KetNoi(); // Kết nối đến cơ sở dữ liệu
            String sql = "SELECT LOAISP FROM LOAISANPHAM WHERE MALOAISP = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, MaLoaiSP);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenLoaiSP = rs.getString("LOAISP"); // Lấy tên loại sản phẩm từ kết quả truy vấn
            }
            conn.close(); // Đóng kết nối
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tenLoaiSP; // Trả về tên loại sản phẩm
    }
    public ArrayList<SanPhamClass> LaydsSanPham() {
    ArrayList<SanPhamClass> ds = new ArrayList<>();
    try {
        KetNoi();
        String sql = "SELECT * FROM SANPHAM, LOAISANPHAM WHERE SANPHAM.TRANGTHAI = 0 AND SANPHAM.MALOAISP = LOAISANPHAM.MALOAISP";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String maSP = rs.getString("MASP");
            String maLoaiSP = rs.getString("MALOAISP");
            String tenSP = rs.getString("TENSP");
            int giaNhap = rs.getInt("GIANHAP");
            int giaSP = rs.getInt("GIASP");
            String hinhAnh = rs.getString("HINHANH");
            int trangThai = rs.getInt("TRANGTHAI");
            SanPhamClass lsp = new SanPhamClass(maSP, maLoaiSP, tenSP, giaNhap, giaSP, hinhAnh, trangThai);
            ds.add(lsp);
        }
        conn.close();
    } catch (SQLException ex) {
        Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ds;
    }

    public ArrayList<SanPhamClass> LaydsSanPhamXoa(){
        ArrayList<SanPhamClass> ds = new ArrayList<SanPhamClass>();
        try {
            KetNoi();
            String sql="select * from SANPHAM where TRANGTHAI = 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                SanPhamClass lsp = new SanPhamClass(MaSP, MaLoaiSP, TenSP, GiaNhap, GiaSP, HinhAnh, TrangThai);
                lsp.setMaSP(rs.getString("MASP"));
                lsp.setMaLoaiSP(rs.getString("MALOAISP"));
                lsp.setTenSP(rs.getString("TENSP"));
                lsp.setGiaNhap(rs.getInt("GIANHAP"));
                lsp.setGiaBan(rs.getInt("GIASP"));
                lsp.setHinhAnh(rs.getString("HINHANH"));
                lsp.setTrangThai(rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    public ArrayList<SanPhamClass> TimKiemSanPham(String giatri){
        ArrayList<SanPhamClass> ds = new ArrayList<SanPhamClass>();
        try {
            KetNoi();
            CallableStatement st=conn.prepareCall("{call DanhsachSanPham_TheoTenSP(?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                SanPhamClass lsp = new SanPhamClass(MaSP, MaLoaiSP, TenSP, GiaNhap, GiaSP, HinhAnh, TrangThai);
                lsp.setMaSP(rs.getString("MASP"));
                lsp.setMaLoaiSP(rs.getString("MALOAISP"));
                lsp.setTenSP(rs.getString("TENSP"));
                lsp.setGiaNhap(rs.getInt("GIANHAP"));
                lsp.setGiaBan(rs.getInt("GIASP"));
                lsp.setHinhAnh(rs.getString("HINHANH"));
                lsp.setTrangThai(rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return ds;
    }
    public String PhatSinhMa() {
        String MaSP = "";
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call SanPhamLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MASP");
                String so = ma.substring(2).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                //System.out.println("ma"+MaSo);
                if (mamoi >= 1 && mamoi <= 9)
                    MaSP = "SP" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi  <= 99)
                    MaSP = "SP" + "0" + mamoi;
                else
                    MaSP = "SP" + mamoi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MaSP;
    }
     public void ThemSanPham(SanPhamClass lsp)
    {
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call insert_SanPham(?,?,?,?,?,?)}");
            st.setString(1, lsp.MaSP);
            st.setString(2, lsp.MaLoaiSP);
            st.setString(3, lsp.TenSP);
            st.setInt(4, lsp.GiaNhap);
            st.setInt(5, lsp.GiaSP);
            st.setString(6, lsp.HinhAnh);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void XoaSanPham(SanPhamClass lsp)
    {
        try {
            KetNoi();
            CallableStatement st=conn.prepareCall("{call Delete_SanPham(?)}");
            st.setString(1, lsp.MaSP);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SuaSanPham(SanPhamClass lsp) {
    try {
        KetNoi();
        // Sử dụng cú pháp chuẩn để gọi thủ tục lưu trữ
        CallableStatement st = conn.prepareCall("{call edit_SanPham(?, ?, ?, ?, ?, ?)}");
        st.setString(1, lsp.MaSP);
        st.setString(2, lsp.MaLoaiSP);
        st.setString(3, lsp.TenSP);
        st.setInt(4, lsp.GiaNhap);
        st.setInt(5, lsp.GiaSP);
        st.setString(6, lsp.HinhAnh);
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public ArrayList<String> LoadMaLoaiSPcbo() {
//        ArrayList<String> list = new ArrayList<String>();
//        KetNoi();
//        String sql="select MALOAISP from SANPHAM where TRANGTHAI = 0";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next())
//            {
//                list.add(rs.getString("MALOAISP"));
//            }
//        } catch (Exception e) {
//            
//        }
//        return list;
//    }
    public void restoreProduct(){
        // Thực hiện cập nhật trạng thái của sản phẩm từ 1 (đã xóa) thành 0 (hoạt động)
        KetNoi();
        String sql = "UPDATE SANPHAM SET TRANGTHAI = 0 WHERE MASP = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "MASP");
            pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
