package com.vishvesh.capstone.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    //@JsonIgnore
    //@JsonManagedReference 
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

	@Column(name = "address1")
	private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "zip")
    private String zip;
    
	public Address() {

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Address(User user_id, String address1, String address2, String city, String state, String zip) {
		this.user_id = user_id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	

	@Override
	public String toString() {
		return "Address [id=" + id + ", user_id=" + user_id + ", address1="+ address1 +", address2="+address2+", "
				+ "city=" + city + ", state="+state+", zip="+zip+"]";
	}

}