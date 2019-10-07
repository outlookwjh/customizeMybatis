package com.testinstance.domain.vo;

public class Domian {

    private String C_ID;
    private String C_CITY;
    private String C_CITYCODE;
    private String C_DISTRICT;
    private String C_POSTCODE;
    private String C_PROVINCE;
    private String C_SHORTCODE;

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    public String getC_CITY() {
        return C_CITY;
    }

    public void setC_CITY(String c_CITY) {
        C_CITY = c_CITY;
    }

    public String getC_CITYCODE() {
        return C_CITYCODE;
    }

    public void setC_CITYCODE(String c_CITYCODE) {
        C_CITYCODE = c_CITYCODE;
    }

    public String getC_DISTRICT() {
        return C_DISTRICT;
    }

    public void setC_DISTRICT(String c_DISTRICT) {
        C_DISTRICT = c_DISTRICT;
    }

    public String getC_POSTCODE() {
        return C_POSTCODE;
    }

    public void setC_POSTCODE(String c_POSTCODE) {
        C_POSTCODE = c_POSTCODE;
    }

    public String getC_PROVINCE() {
        return C_PROVINCE;
    }

    public void setC_PROVINCE(String c_PROVINCE) {
        C_PROVINCE = c_PROVINCE;
    }

    public String getC_SHORTCODE() {
        return C_SHORTCODE;
    }

    public void setC_SHORTCODE(String c_SHORTCODE) {
        C_SHORTCODE = c_SHORTCODE;
    }

    @Override
    public String toString() {
        return "domian{" +
                "C_ID='" + C_ID + '\'' +
                ", C_CITY='" + C_CITY + '\'' +
                ", C_CITYCODE='" + C_CITYCODE + '\'' +
                ", C_DISTRICT='" + C_DISTRICT + '\'' +
                ", C_POSTCODE='" + C_POSTCODE + '\'' +
                ", C_PROVINCE='" + C_PROVINCE + '\'' +
                ", C_SHORTCODE='" + C_SHORTCODE + '\'' +
                '}';
    }
}
