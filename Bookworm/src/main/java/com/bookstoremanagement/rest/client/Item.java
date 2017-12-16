package com.bookstoremanagement.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {
	private VolumeInfo volumeInfo;
	
	private AccessInfo accessInfo;
	
	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}
	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
	public AccessInfo getAccessInfo() {
		return accessInfo;
	}
	public void setAccessInfo(AccessInfo accessInfo) {
		this.accessInfo = accessInfo;
	}
	
}
