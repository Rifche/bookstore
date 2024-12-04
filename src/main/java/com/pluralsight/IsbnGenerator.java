package com.pluralsight;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IsbnGenerator {
    public String generateNumber() {
        return "13-84356-" + Math.abs(new java.util.Random().nextInt());
    }
}
