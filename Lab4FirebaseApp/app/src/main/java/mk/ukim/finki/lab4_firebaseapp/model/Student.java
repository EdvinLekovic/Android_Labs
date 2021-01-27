package mk.ukim.finki.lab4_firebaseapp.model;

public class Student {


    private String uId;
    private String index;
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Student() {
    }

    public Student(String uId,String index, String name, String surname, String phone, String address) {
        this.uId = uId;
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
