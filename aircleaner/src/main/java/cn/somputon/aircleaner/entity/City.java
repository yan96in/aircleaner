package cn.somputon.aircleaner.entity;

public class City {
	String area;
	double lng;
    double lat;
	String rank,city,province, detailNum;

	public String getCity() {
		return city;
	}

	public String getDetailNum() {
		return detailNum;
	}

	public String getProvince() {
		return province;
	}

	public String getRank() {
		return rank;
	}

	public City(String rank, String city, String province, String detailNum) {
		this.rank = rank;
		this.city = city;
		this.province = province;
		this.detailNum = detailNum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
}

