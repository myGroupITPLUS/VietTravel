package com.viettravelapplication.Model;

public class AboutUs {
    private int id;
    private String descriptions;
    private String address;
    private String contact;
    private String rule;
    public AboutUs(){

    }
    public AboutUs(int id, String descriptions, String address, String contact, String rule) {
        this.id = id;
        this.descriptions = this.descriptions;
        this.address = this.address;
        this.contact = this.contact;
        this.rule = this.rule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "id=" + id +
                ", descriptions='" + descriptions + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
