package com.svarks.trueherb.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name = "productFilesInfo")
//@NamedQueries({
//		@NamedQuery(name = "ProductsFileInfo.getProductsByName", query = "SELECT e FROM Products e WHERE e.productName =:productName "),
//		@NamedQuery(name = "ProductsFileInfo.isProductExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM Products e WHERE e.productName =:productName or e.productCode=:productCode ")})

public class ProductsFileInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="fid",updatable=false,nullable=false)
	private int fid;
	private String imageName;
	private String imageDescription;
	private String docName;
	private String docDescription;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocDescription() {
		return docDescription;
	}
	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docDescription == null) ? 0 : docDescription.hashCode());
		result = prime * result + ((docName == null) ? 0 : docName.hashCode());
		result = prime * result + fid;
		result = prime * result + ((imageDescription == null) ? 0 : imageDescription.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsFileInfo other = (ProductsFileInfo) obj;
		if (docDescription == null) {
			if (other.docDescription != null)
				return false;
		} else if (!docDescription.equals(other.docDescription))
			return false;
		if (docName == null) {
			if (other.docName != null)
				return false;
		} else if (!docName.equals(other.docName))
			return false;
		if (fid != other.fid)
			return false;
		if (imageDescription == null) {
			if (other.imageDescription != null)
				return false;
		} else if (!imageDescription.equals(other.imageDescription))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		return true;
	}
	
}
