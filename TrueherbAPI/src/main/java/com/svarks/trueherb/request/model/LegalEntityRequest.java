package com.svarks.trueherb.request.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class LegalEntityRequest {
	
	private int legalEntityId;
	private String entityName;
	private String coutry;
	private String pincode;
	private boolean isDelete;
	
	public boolean isDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(int legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getCoutry() {
		return coutry;
	}

	public void setCoutry(String coutry) {
		this.coutry = coutry;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
