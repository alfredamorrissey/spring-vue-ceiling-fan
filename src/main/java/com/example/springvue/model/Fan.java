package com.example.springvue.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class Fan {

    private Long id; // Auto-incremented ID

    @Min(0)
    @Max(3)
    private int speed; // Speed: 0 to 3

    @NotNull
    private String direction; // Direction: "clockwise" or "counter-clockwise"

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
