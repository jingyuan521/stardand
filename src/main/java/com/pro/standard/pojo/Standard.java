package com.pro.standard.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Standard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "std_num")
    private String std_num;

    public String getZhname() {
        return zhname;
    }

    public void setZhname(String zhname) {
        this.zhname = zhname;
    }

    public String getSversion() {
        return sversion;
    }

    public void setSversion(String sversion) {
        this.sversion = sversion;
    }

    private String zhname;
    private String sversion;

    public String getSkeys() {
        return skeys;
    }

    public void setSkeys(String skeys) {
        this.skeys = skeys;
    }

    private String skeys;
    private Date release_date;
    private Date impl_date;
    private String package_path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStd_num() {
        return std_num;
    }

    public void setStd_num(String std_num) {
        this.std_num = std_num;
    }





    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getImpl_date() {
        return impl_date;
    }

    public void setImpl_date(Date impl_date) {
        this.impl_date = impl_date;
    }

    public String getPackage_path() {
        return package_path;
    }

    public void setPackage_path(String package_path) {
        this.package_path = package_path;
    }
}
