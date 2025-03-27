package org.example.springbasictest.util;

public class Hello {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String print() {
        return "Hello " + name + "!";
    }
}
