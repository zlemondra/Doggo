package com.example.mainscreen;

public class DogContent {
    private String id;
    private String name;
    private String breed;
    private String gender;
    private String age;

    public DogContent(String id, String name, String breed, String gender, String age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getAge() {
        return this.age;
    }
}
