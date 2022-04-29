package com.example.M3.model;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.*;
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@PrimaryKey
	private String vin;
	private String verified;
	private int speed;
	private String alert;
	private String timestamp;
    
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public User(String vin, String verified, int speed, String alert,String timeStamp) {
	super();
	this.vin = vin;
	this.verified = verified;
	this.speed = speed;
	this.alert = alert;
	this.timestamp = timeStamp;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	

}
