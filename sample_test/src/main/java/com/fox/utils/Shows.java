package com.fox.utils;

public class Shows {

	private String fox;
	private String fx;
	private String nationalGeographic;
	private String foxSports;
	private String allShows;

	public Shows(String fox, String fx, String nationalGeographic, String foxSports, String allShows) {
		this.fox = fox;
		this.fx = fx;
		this.nationalGeographic = nationalGeographic;
		this.foxSports = foxSports;
		this.allShows = allShows;
	}

	public String getFox() {
		return fox;
	}

	public void setFox(String fox) {
		this.fox = fox;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getNationalGeographic() {
		return nationalGeographic;
	}

	public void setNationalGeographic(String nationalGeographic) {
		this.nationalGeographic = nationalGeographic;
	}

	public String getFoxSports() {
		return foxSports;
	}

	public void setFoxSports(String foxSports) {
		this.foxSports = foxSports;
	}

	public String getAllShows() {
		return allShows;
	}

	public void setAllShows(String allShows) {
		this.allShows = allShows;
	}

}
