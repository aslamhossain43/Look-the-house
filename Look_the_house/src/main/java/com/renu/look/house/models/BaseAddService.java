package com.renu.look.house.models;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract  class BaseAddService<ID> {

private Date createdDate;
private Date lastModifiedDate;
public abstract ID getId();
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getLastModifiedDate() {
	return lastModifiedDate;
}
public void setLastModifiedDate(Date lastModifiedDate) {
	this.lastModifiedDate = lastModifiedDate;
}

	
	@PrePersist
	public void prepersist() {
		
		this.createdDate=new Date();
		this.lastModifiedDate=new Date();
		
	}
	
	@PreUpdate
	public void preUpdate() {
		this.lastModifiedDate=new Date();
		
	}
	
	
}
