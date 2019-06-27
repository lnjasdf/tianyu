package com.service.tianyu.po;

import com.service.tianyu.controller.TestController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by intron on 2017/1/15 0015.
 */
public class IpInfoPo {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private String ip;
    private String continent;
    private String country;
    private String province;
    private String city;
    private String district;
    private String operatorName;
    private String districtCode;
    private String countryName;
    private String countryNameShort;
    private Double longitude;
    private Double latitude;

    private static final String SYMBOL_WITH = "\\|";
    private static final int LENGTN = 11;

    public static final IpInfoPo parseString(String ip, String str) {
        if (StringUtils.isBlank(ip) || StringUtils.isBlank(str)) {
            return null;
        }
        String[] infos = str.split(SYMBOL_WITH);
        if (infos.length != LENGTN) {
            return null;
        }
        IpInfoPo ipInfoPo = new IpInfoPo();
        ipInfoPo.setIp(ip);
        ipInfoPo.setContinent(infos[0]);
        ipInfoPo.setCountry(infos[1]);
        ipInfoPo.setProvince(infos[2]);
        ipInfoPo.setCity(infos[3]);
        ipInfoPo.setDistrict(infos[4]);
        ipInfoPo.setOperatorName(infos[5]);
        ipInfoPo.setDistrictCode(infos[6]);
        ipInfoPo.setCountryName(infos[7]);
        ipInfoPo.setCountryNameShort(infos[8]);
        ipInfoPo.setLongitude(Double.valueOf(infos[9])); // TODO 经纬度可能填反了
        ipInfoPo.setLatitude(Double.valueOf(infos[10]));
        return ipInfoPo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryNameShort() {
        return countryNameShort;
    }

    public void setCountryNameShort(String countryNameShort) {
        this.countryNameShort = countryNameShort;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}