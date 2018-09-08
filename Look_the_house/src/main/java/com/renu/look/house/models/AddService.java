package com.renu.look.house.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class AddService extends BaseAddService<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vCode;
	private String iCode;
	@NotBlank(message="Enter a country name")
	private String country;
	@NotBlank(message="Enter a district name")
	private String district;
	@NotBlank(message="Enter a sub-district name")
	private String subDistrict;
	@NotBlank(message="Enter your detail address")
	private String detailsAddress;
	@NotBlank(message="Select a rent type")
	private String rentType;
	@NotBlank(message="Enter service description")
	private String serviceDescription;
	@NotBlank(message="Enter your contact number")
	private String contact;
	@NotBlank(message="Enter a word which you added during registration")
	private String word;
	@Transient
	private MultipartFile vFile;
	@Transient
	private MultipartFile iFile;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public AddService() {
		this.vCode = "V" + UUID.randomUUID().toString().substring(26).toUpperCase();
		this.iCode = "I" + UUID.randomUUID().toString().substring(26).toUpperCase();
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public String getiCode() {
		return iCode;
	}

	public void setiCode(String iCode) {
		this.iCode = iCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDetailsAddress() {
		return detailsAddress;
	}

	public void setDetailsAddress(String detailsAddress) {
		this.detailsAddress = detailsAddress;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public MultipartFile getvFile() {
		return vFile;
	}

	public void setvFile(MultipartFile vFile) {
		this.vFile = vFile;
	}

	public MultipartFile getiFile() {
		return iFile;
	}

	public void setiFile(MultipartFile iFile) {
		this.iFile = iFile;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	@Override
	public String toString() {
		return "AddService [id=" + id + ", vCode=" + vCode + ", iCode=" + iCode + ", country=" + country + ", district="
				+ district + ", subDistrict=" + subDistrict + ", detailsAddress=" + detailsAddress + ", rentType="
				+ rentType + ", serviceDescription=" + serviceDescription + ", contact=" + contact + ", word=" + word
				+ "]";
	}



}
