package DAO;

import Models.LoaiSPClass;
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

public class dsLoaiSPClass {
    static Connection conn;
    String MaLoaiSP;
    String LoaiSP;
    int TrangThai;

    // Phương thức kết nối cơ sở dữ liệu
    public void KetNoi() {
        try {
            String user = "sa";
            String pass = "khai754123";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Không tìm thấy driver phù hợp!", "Lỗi Kết Nối", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức lấy danh sách loại sản phẩm
    public ArrayList<LoaiSPClass> LaydsLoaiSP() {
        ArrayList<LoaiSPClass> ds = new ArrayList<>();
        try {
            KetNoi();
            String sql = "SELECT * FROM LOAISANPHAM WHERE TRANGTHAI = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                LoaiSPClass lsp = new LoaiSPClass(rs.getString("MALOAISP"), rs.getString("LOAISP"), rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }

    // Phương thức thêm loại sản phẩm
    public void ThemLoaiSP(LoaiSPClass lsp) {
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call insert_LoaiSP(?,?)}");
            st.setString(1, lsp.MaLoaiSP);
            st.setString(2, lsp.LoaiSP);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức xóa loại sản phẩm
    public void XoaLoaiSP(LoaiSPClass lsp) {
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call Delete_LoaiSP(?)}");
            st.setString(1, lsp.MaLoaiSP);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức sửa loại sản phẩm
    public void SuaLoaiSP(LoaiSPClass lsp) {
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call edit_LoaiSP(?, ?)}");
            st.setString(1, lsp.MaLoaiSP);
            st.setString(2, lsp.LoaiSP);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức tìm kiếm loại sản phẩm
    public ArrayList<LoaiSPClass> TimKiemLoaiSP(String giatri) {
        ArrayList<LoaiSPClass> ds = new ArrayList<>();
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call DanhsachLoaiSanPham_TheoLoaiSP(?)}");
            st.setString(1, giatri);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoaiSPClass lsp = new LoaiSPClass(rs.getString("MALOAISP"), rs.getString("LOAISP"), rs.getInt("TRANGTHAI"));
                ds.add(lsp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }

    // Phương thức phát sinh mã
    public String PhatSinhMa() {
        String MaLoaiSP = "";
        try {
            KetNoi();
            CallableStatement st = conn.prepareCall("{call LoaiSPLonNhat}");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MALOAISP");
                String so = ma.substring(4).trim(); // Loại bỏ các khoảng trắng không mong muốn
                int MaSo = Integer.parseInt(so);
                int mamoi = (MaSo + 1);
                if (mamoi >= 1 && mamoi <= 9)
                    MaLoaiSP = "LOAI" + "00" + mamoi;
                else if (mamoi >= 10 && mamoi <= 99)
                    MaLoaiSP = "LOAI" + "0" + mamoi;
                else
                    MaLoaiSP = "LOAI" + mamoi;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(dsLoaiSPClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi phát sinh mã loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return MaLoaiSP;
    }
}
