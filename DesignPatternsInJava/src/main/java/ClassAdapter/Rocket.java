package main.java.ClassAdapter;

import java.math.BigDecimal;

public class Rocket {

    private String name;
    private BigDecimal price;
    private double apogee;

    public Rocket() {
    }

    public Rocket(String name, BigDecimal price, double apogee) {
        this.name = name;
        this.price = price;
        this.apogee = apogee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getApogee() {
        return apogee;
    }

    public void setApogee(double apogee) {
        this.apogee = apogee;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", apogee=" + apogee +
                '}';
    }
}
