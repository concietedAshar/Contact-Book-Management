package com.mrash.ashbook;


public class Contacts {
    int id;
    int imageId;
    String name;
    String mobileNo;
    String email;
    String Address;
    String dateOfBirth;

    public Contacts() {
    }

    public Contacts(int id, int imageId, String name, String mobileNo, String email, String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        Address = address;
        this.dateOfBirth = dateOfBirth;
        this.imageId = imageId;
    }

    public Contacts(int imageId, String name, String mobileNo, String email, String address, String dateOfBirth) {
        this.name = name;
        this.imageId = imageId;
        this.mobileNo = mobileNo;
        this.email = email;
        Address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", Address='" + Address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
