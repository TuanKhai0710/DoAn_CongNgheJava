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
public class LoaiSPClass {
    public String MaLoaiSP, LoaiSP;
    public int TrangThai;
    public LoaiSPClass(String MaLoaiSP, String LoaiSP, int TrangThai) {
        this.MaLoaiSP = MaLoaiSP;
        this.LoaiSP = LoaiSP;
        this.TrangThai = TrangThai;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
}


