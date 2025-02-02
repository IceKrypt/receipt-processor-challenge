package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;


public  class Item {
    @NotNull
    @JsonProperty
    private String shortDescription;

    @NotNull
    @JsonProperty
    private Double price;

    public Item() {
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
