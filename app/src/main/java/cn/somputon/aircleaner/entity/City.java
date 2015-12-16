package cn.somputon.aircleaner.entity;

/**
 * Created by 002 on 2015/12/16.
 */
public class City {
    String rank,city,province,detailNum;

    public City(String rank, String city, String province, String detailNum) {
        this.rank = rank;
        this.city = city;
        this.province = province;
        this.detailNum = detailNum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(String detailNum) {
        this.detailNum = detailNum;
    }
}
