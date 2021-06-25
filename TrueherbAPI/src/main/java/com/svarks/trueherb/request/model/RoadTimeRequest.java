package com.svarks.trueherb.request.model;

public class RoadTimeRequest {
	
	private int timingsId;
	private Double kilometer;
	private Double timeInHrs;
	private boolean isDelete;
	public boolean isDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public int getTimingsId() {
		return timingsId;
	}
	public void setTimingsId(int timingsId) {
		this.timingsId = timingsId;
	}
	public Double getKilometer() {
		return kilometer;
	}
	public void setKilometer(Double kilometer) {
		this.kilometer = kilometer;
	}
	public Double getTimeInHrs() {
		return timeInHrs;
	}
	public void setTimeInHrs(Double timeInHrs) {
		this.timeInHrs = timeInHrs;
	}
	
	

}
