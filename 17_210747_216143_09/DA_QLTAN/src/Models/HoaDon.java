/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;

/**
 *
 * @author PC
 */
public class HoaDon {
    
    public String DateOrder;
    public String MaHD,TT;
    public int thanhtien;
    public Object get;

    public HoaDon(String MaHD, String DateOrder, int thanhtien, Object get) {
        this.DateOrder = DateOrder;
        this.MaHD = MaHD;
        this.thanhtien = thanhtien;
        this.get = get;
    }

    public HoaDon(String MaHD, String DateOrder, int thanhtien, String TT) {
        this.DateOrder = DateOrder;
        this.MaHD = MaHD;
        this.TT = TT;
        this.thanhtien = thanhtien;
    }

    
    public HoaDon() {
        
    }

    public HoaDon(String MaHD, String DateOrder, int gia) {
        
    }

    public String getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(String DateOrder) {
        this.DateOrder = DateOrder;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Object getGet() {
        return get;
    }

    public void setGet(Object get) {
        this.get = get;
    }

    public String getTT() {
        return TT;
    }

    public void setTT(String TT) {
        this.TT = TT;
    }

   
    
}
