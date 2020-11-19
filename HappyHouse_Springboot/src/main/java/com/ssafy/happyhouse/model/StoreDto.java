package com.ssafy.happyhouse.model;

public class StoreDto {
	private String storename;
	private String kind;
	private String lat;
	private String lng;
	private String dong;
	private String jibun;

	public StoreDto(String storename, String kind, String lat, String lng, String dong, String jibun) {
		super();
		this.storename = storename;
		this.kind = kind;
		this.lat = lat;
		this.lng = lng;
		this.dong = dong;
		this.jibun = jibun;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public StoreDto() {
		super();
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "StoreDto [storename=" + storename + ", kind=" + kind + ", lat=" + lat + ", lng=" + lng + ", dong="
				+ dong + ", jibun=" + jibun + "]";
	}

}
