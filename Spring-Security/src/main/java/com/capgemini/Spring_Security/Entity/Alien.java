package com.capgemini.Spring_Security.Entity;


public class Alien {
    private Integer id;
    private String name;
    private String city;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Alien(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    public Alien(){
        super();
    }

}
