package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;


@Entity
@Transactional
@Table(name = "products")
@NamedQueries({
		@NamedQuery(name = "Products.finByProductName", query = "SELECT e FROM Products e WHERE e.productName =:productName "),
		@NamedQuery(name = "Products.isProductExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM Products e WHERE e.productName =:productName or e.productCode=:productCode ")})

public class Products implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="pid",updatable=false,nullable=false)
	private int pid;
	private String productName;
	private String productCode;
	@Column(columnDefinition="text")
	private String description;
	
	@OneToMany(targetEntity=ProductsFileInfo.class,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="pf_fk",referencedColumnName="pid")
	private List<ProductsFileInfo> productFileInfoList;
	private Date creatdDate;
	private Date modifiedDate;
	

	public Date getCreatdDate() {
		return creatdDate;
	}

	public void setCreatdDate(Date creatdDate) {
		this.creatdDate = creatdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductsFileInfo> getProductFileInfoList() {
		return productFileInfoList;
	}

	public void setProductFileInfoList(List<ProductsFileInfo> productFileInfoList) {
		this.productFileInfoList = productFileInfoList;
	}
}
