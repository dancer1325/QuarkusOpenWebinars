package org.openwebinars.course.gettingStarted;

// Included thanks to hibernate-validator
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Beer {
    @NotNull
    @NotBlank
    private String name;
    @Min(100)
    private int capacity;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @NotExpired
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate expirationDate;

    public Beer(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Beer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
