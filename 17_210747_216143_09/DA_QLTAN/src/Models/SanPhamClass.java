/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author KHAI
 */
public class SanPhamClass {
    public String MaSP, MaLoaiSP, TenSP, HinhAnh;
    public int GiaNhap, GiaSP, TrangThai;

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getGiaBan() {
        return GiaSP;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaSP = GiaBan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public SanPhamClass(String MaSP, String MaLoaiSP, String TenSP, int GiaNhap, int GiaSP, String HinhAnh, int TrangThai) {
        this.MaSP = MaSP;
        this.MaLoaiSP = MaLoaiSP;
        this.TenSP = TenSP;
        this.GiaNhap = GiaNhap;
        this.GiaSP = GiaSP;
        this.HinhAnh = HinhAnh;
        this.TrangThai = TrangThai;
    }
}
