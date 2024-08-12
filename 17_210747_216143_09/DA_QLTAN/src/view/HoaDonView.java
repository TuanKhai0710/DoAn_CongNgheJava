/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import DAO.DAO_CTHD;
import DAO.DAO_HoaDon;
import DAO.DAO_Khachhang;
import DAO.DAO_NhanVien;
import DAO.daoSanPham;
import Models.ChiTietHoaDon;
import Models.HoaDon;
import Models.NhanVien;
import Models.SanPhamClass;
import java.awt.Image;
import java.io.File;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author KHAI
 */
public class HoaDonView extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonView
     */
    Connection conn;
    DAO_HoaDon dshd;
    DAO_Khachhang dskh;
    DAO_NhanVien dsnv;         
    private List<HoaDon> hoadon;
    private List<SanPhamClass> sanpham;
    private List<ChiTietHoaDon> cthd;
    
    
    private DefaultTableModel ModelHD;
    private DefaultTableModel ModelSP;
    private DefaultTableModel ModelCTHD;
    private DefaultComboBoxModel cbomodel;
    int SelectedIndex;
    public HoaDonView() {
        initComponents();
        dsnv = new DAO_NhanVien();
        dskh = new DAO_Khachhang();
        dshd = new DAO_HoaDon();       
        ModelHD = (DefaultTableModel) tbl_HoaDon.getModel();
        ModelSP = (DefaultTableModel) tbl_SP.getModel();
        ModelCTHD = (DefaultTableModel) tbl_CTHD.getModel();
        showTableHD();
        showTableSP();
        showTableCTHD();
        AnHienTextBox(true);
        HienThiComBoBox();
    }
    void HienThiComBoBox(){
        String sqlQuery = "SELECT TENNV FROM NHANVIEN";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN", "sa", "khai754123");
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            ResultSet result = statement.executeQuery();
            while(result.next()) { // Nếu có kết quả trả về từ truy vấn
                NhanVien nv = new NhanVien();
                // Hiển thị thông tin lấy được lên giao diện
                nv.setTenNV(result.getString("TENNV"));
                cbomodel = new DefaultComboBoxModel();
                cbx_TenNV.removeAllItems();
                cbomodel.addElement("TENNV");
                cbx_TenNV.setModel(cbomodel);
            } 
            conn.close(); // Đóng kết nối sau khi sử dụng
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + ex.getMessage());
        }
    }
     private void Uploadhinh(String hinh) {
    // Đảm bảo đường dẫn tới thư mục chứa hình ảnh là chính xác
    String path = "src\\images\\" + hinh;
    
    // Kiểm tra file hình ảnh có tồn tại không
    File file = new File(path);
    if (!file.exists()) {
        System.out.println("File không tồn tại: " + path);
        return;
    }
    
    // Tải và đặt hình ảnh vào label
    ImageIcon image1 = new ImageIcon(path);
    Image im = image1.getImage();
    ImageIcon icon = new ImageIcon(im.getScaledInstance(lbl_hinh.getWidth(), 
            lbl_hinh.getHeight(), Image.SCALE_SMOOTH));
    lbl_hinh.setIcon(icon);
    }
    
    private boolean kiemTraNhapDayDu() {
    String Soluong = txtSoLuong.getText().trim();
    
    //String HinhAnh = lbl_Hinh.getText().trim();

    if (Soluong.isEmpty() ){
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
    }
    
    private boolean kiemTraSoLuong() {
    int Soluong = Integer.parseInt(txtSoLuong.getText());
    
    //String HinhAnh = lbl_Hinh.getText().trim();

    if (Soluong <= 0 ){
        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
    }
     private void AnHienTextBox(boolean t)
    {
        txtMaHD.setEnabled(!t);
        txtTT.setEnabled(!t);
        //txt_tenLoai.setEnabled(t);
    }
    private void showTableHD() {
        hoadon = new DAO_HoaDon().LaydsHD();
        ModelHD.setRowCount(0);
        for(HoaDon hd:hoadon){
            ModelHD.addRow(new Object[]{
                hd.getMaHD(),hd.getDateOrder(),hd.getThanhtien(),hd.getTT()
            });
        }
    }

    private void showTableSP() {
        sanpham = new daoSanPham().LaydsSanPham();
        ModelSP.setRowCount(0);
        for(SanPhamClass sp:sanpham){
            ModelSP.addRow(new Object[]{
                sp.getMaSP(),sp.getMaLoaiSP(),sp.getTenSP(),sp.getGiaNhap(),sp.getGiaBan(),sp.getHinhAnh()
            });
        }
    }

    private void showTableCTHD() {
        cthd = new DAO_CTHD().LaydsCTHD();
        ModelCTHD.setRowCount(0);
        for(ChiTietHoaDon chitiet:cthd){
            ModelCTHD.addRow(new Object[]{
                tbl_CTHD.getRowCount()+1,chitiet.getMaSP(),chitiet.getSoLuong(),chitiet.getGia()
            });
        }
    }
       public boolean isValidPhoneNumber() {
        String phoneNumber = txtSDT.getText();
        // Định dạng số điện thoại: 10 chữ số và bắt đầu từ số 0
        String regex = "^0[0-9]{9}$";
        
        // Biên dịch regex
        Pattern pattern = Pattern.compile(regex);
        
        // So khớp regex với chuỗi số điện thoại
        Matcher matcher = pattern.matcher(phoneNumber);
        
        // Trả về kết quả so khớp
        return matcher.matches();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_HoaDon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        cbxLoai = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        txtDateOrder = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbx_TenNV = new javax.swing.JComboBox<>();
        txtTenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnADD = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_CTHD = new javax.swing.JTable();
        btnAddSP = new javax.swing.JButton();
        lbl_hinh = new javax.swing.JLabel();

        btnDelete.setText("Xóa hóa đơn");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Ngày Mua", "Thành tiền", "Trạng Thái"
            }
        ));
        jScrollPane3.setViewportView(tbl_HoaDon);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel17.setText("Trạng Thái:");

        txtTrangThai.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Mã hóa đơn:");

        txtMaHD.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setText("Thành tiền:");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Mã sản phẩm:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("Tên sản phẩm:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Loại:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Số lượng:");

        cbxLoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOAI001\t", "LOAI002", "LOAI003", "LOAI004", "LOAI005", "LOAI006" }));

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Giá");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(112, 112, 112))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMasp, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtGia)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTensp)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxLoai, 0, 1, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        tbl_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã LSP", "Tên SP", "Giá nhập", "Giá bán", "Hình"
            }
        ));
        tbl_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SP);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÓA ĐƠN");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Số điện thoại");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Ngày đặt:");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtDiachi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        txtDateOrder.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Tên Nhân Viên:");

        cbx_TenNV.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbx_TenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_TenNVActionPerformed(evt);
            }
        });

        txtTenkh.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenkhActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Tên Khách hàng:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx_TenNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiachi))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDateOrder)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbx_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        txtTT.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel16.setText("Bảng hóa đơn");

        btnADD.setText("Thêm hóa đơn");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        tbl_CTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Số lượng", "Giá"
            }
        ));
        tbl_CTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_CTHD);

        btnAddSP.setText("Thêm sản phẩm");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        lbl_hinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnADD)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddSP)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMaHD))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(36, 36, 36)
                                .addComponent(lbl_hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        SelectedIndex = tbl_HoaDon.getSelectedRow();
        JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không !");

        HoaDon hd = hoadon.get(SelectedIndex);
        new DAO_HoaDon().XoaKH(hd);
        showTableHD();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String MaHD = txtMaHD.getText();
        String DateOrder = txtDateOrder.getText();
        String TrangThai = txtTrangThai.getText();
        int gia =0;
        for(int i=0; i<tbl_CTHD.getRowCount();i++){
            gia += Integer.parseInt(tbl_CTHD.getValueAt(i, 3).toString());

        }

        txtTT.setText(String.valueOf(gia));

        HoaDon hd = new HoaDon(MaHD, DateOrder, gia, TrangThai);
        new DAO_HoaDon().ThemCTHD(hd);
        showTableHD();
        JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");

    }//GEN-LAST:event_btnSaveActionPerformed

    private void tbl_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPMouseClicked
        // TODO add your handling code here:

        SelectedIndex=tbl_SP.getSelectedRow();
        SanPhamClass sp = sanpham.get(SelectedIndex);
        txtMasp.setText(sp.getMaSP());
        txtTensp.setText(sp.getTenSP());
        cbxLoai.setSelectedItem(sp.getMaLoaiSP());
        txtGia.setText(sp.getGiaBan()+"");

    }//GEN-LAST:event_tbl_SPMouseClicked

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
        if (!isValidPhoneNumber()) {
                JOptionPane.showMessageDialog(this, "Bạn nhập sai định dạng Số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
           }
        String tenKhachHang = txtSDT.getText().trim(); // Lấy tên khách hàng từ ô nhập
        if (!tenKhachHang.isEmpty()) { // Đảm bảo tên khách hàng không rỗng
            // Thực hiện truy vấn SQL để lấy thông tin số điện thoại và địa chỉ từ cơ sở dữ liệu
            String sqlQuery = "SELECT TENKH, DIACHI FROM KHACHHANG WHERE SDT = ?";
            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN", "sa", "khai754123");
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
                statement.setString(1, tenKhachHang);
                ResultSet result = statement.executeQuery();
                if (result.next()) { // Nếu có kết quả trả về từ truy vấn
                    String soDienThoai = result.getString("TENKH");
                    String diaChi = result.getString("DIACHI");
                    // Hiển thị thông tin lấy được lên giao diện
                    txtDiachi.setText(diaChi);
                    txtTenkh.setText(soDienThoai);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin cho khách hàng này!");
                }
                conn.close(); // Đóng kết nối sau khi sử dụng
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng!");
        }
    }//GEN-LAST:event_txtSDTActionPerformed

    private void cbx_TenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_TenNVActionPerformed
        // TODO add your handling code here:

        String sqlQuery = "SELECT TENNV FROM NHANVIEN";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DA_QL_CHTAN", "sa", "khai754123");
            PreparedStatement statement = conn.prepareStatement(sqlQuery);

            ResultSet result = statement.executeQuery();
            while (result.next()) { // Nếu có kết quả trả về từ truy vấn
                String tennv = result.getString("TENNV");
                cbx_TenNV.addItem(tennv);
            }
            conn.close(); // Đóng kết nối sau khi sử dụng
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + ex.getMessage());
        }

    }//GEN-LAST:event_cbx_TenNVActionPerformed

    private void txtTenkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenkhActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenkhActionPerformed

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        // TODO add your handling code here:
        txtMaHD.setText(dshd.PhatSinhMa());
        txtTenkh.setText("");
        txtDiachi.setText("");
        txtSDT.setText("");
        txtDateOrder.setText("2024-06-06");
        txtMasp.setText("");
        txtTensp.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        cbxLoai.setSelectedIndex(0);
    }//GEN-LAST:event_btnADDActionPerformed

    private void tbl_CTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CTHDMouseClicked
        // TODO add your handling code here:
        SelectedIndex = tbl_CTHD.getSelectedRow();
        ChiTietHoaDon ct = cthd.get(SelectedIndex);
        new DAO_CTHD().XoaCTHD(ct.getID());
        showTableCTHD();
    }//GEN-LAST:event_tbl_CTHDMouseClicked

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        // TODO add your handling code here:

        kiemTraNhapDayDu();
//        kiemTraSoLuong();
        String MaSP = txtMasp.getText();
        int soluong = Integer.parseInt(txtSoLuong.getText());
        int gia = Integer.parseInt(txtGia.getText());
        int tongtien= gia * soluong;

        ChiTietHoaDon ct = new ChiTietHoaDon(MaSP, soluong, tongtien);
        new DAO_CTHD().ThemCTHD(ct);
        showTableCTHD();
    }//GEN-LAST:event_btnAddSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbxLoai;
    private javax.swing.JComboBox<String> cbx_TenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_hinh;
    private javax.swing.JTable tbl_CTHD;
    private javax.swing.JTable tbl_HoaDon;
    private javax.swing.JTable tbl_SP;
    private javax.swing.JTextField txtDateOrder;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTenkh;
    private javax.swing.JTextField txtTensp;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
