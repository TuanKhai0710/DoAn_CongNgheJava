/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;



/**
 *
 * @author PC
 */
public class NhanVien {
    public String MaNV, TenNV, ChucVu, SDT,gt,NVL;
    public int TrangThai,Luong;

    public NhanVien(String MaNV, String TenNV, String ChucVu, String SDT, String gt,int TrangThai, int Luong, String NVL) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.gt = gt;
        this.TrangThai = TrangThai;
        this.Luong = Luong;
        this.NVL = NVL;
 
    }

    public NhanVien() {
        
    }

  

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getNVL() {
        return NVL;
    }

    public void setNVL(String NVL) {
        this.NVL = NVL;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }

    
    
}
