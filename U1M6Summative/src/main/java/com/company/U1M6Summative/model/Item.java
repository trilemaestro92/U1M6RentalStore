package com.company.U1M6Summative.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private int item_id;
    private String name;
    private String description;
    private BigDecimal daily_rate;

    public Item(){
        super();
    }
    public Item(String name, String description, BigDecimal daily_rate) {
        this.name = name;
        this.description = description;
        this.daily_rate = daily_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id &&
                name.equals(item.name) &&
                description.equals(item.description) &&
                daily_rate.equals(item.daily_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, name, description, daily_rate);
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
    }
}
