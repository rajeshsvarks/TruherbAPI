package com.svarks.trueherb.request.model;

public class TransportModeRequest {
	
	private int modeid;
	private String fromCountry;
	private String toCountry;
	private String modeType;  
	private boolean isDelete;
	public int getModeid() {
		return modeid;
	}
	public void setModeid(int modeid) {
		this.modeid = modeid;
	}
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public String getModeType() {
		return modeType;
	}
	public void setModeType(String modeType) {
		this.modeType = modeType;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
