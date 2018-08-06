package com.service.tianyu.po;

import javax.persistence.*;

/**
 * Created by intron on 2017/1/15 0015.
 */
@Entity
@Table(name = "t_product_config2")
public class ProductConfigPo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "channel_id")
    private String channelId;

    private int operator;

    private int mm;

    private String control;

    @Column(name = "province_closed")
    private String provinceClosed;

    @Column(name = "province_closed2")
    private String provinceClosed2;

    @Column(name = "province_closed3")
    private String provinceClosed3;

    @Column(name = "province_allowed1")
    private String provinceAllowed1;

    @Column(name = "province_allowed2")
    private String provinceAllowed2;

    @Column(name = "time_ban")
    private String timeBan;

    private String interval;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public String getProvinceClosed() {
        return provinceClosed;
    }

    public void setProvinceClosed(String provinceClosed) {
        this.provinceClosed = provinceClosed;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getProvinceClosed2() {
        return provinceClosed2;
    }

    public void setProvinceClosed2(String provinceClosed2) {
        this.provinceClosed2 = provinceClosed2;
    }

    public String getProvinceClosed3() {
        return provinceClosed3;
    }

    public void setProvinceClosed3(String provinceClosed3) {
        this.provinceClosed3 = provinceClosed3;
    }

    public String getProvinceAllowed1() {
        return provinceAllowed1;
    }

    public void setProvinceAllowed1(String provinceAllowed1) {
        this.provinceAllowed1 = provinceAllowed1;
    }

    public String getProvinceAllowed2() {
        return provinceAllowed2;
    }

    public void setProvinceAllowed2(String provinceAllowed2) {
        this.provinceAllowed2 = provinceAllowed2;
    }

    public String getTimeBan() {
        return timeBan;
    }

    public void setTimeBan(String timeBan) {
        this.timeBan = timeBan;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
