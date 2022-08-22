package com.bootninza.bullbear.webservices.models;

import java.io.Serializable;

public class Equity implements Serializable {

    private Long id;
    private String name;
    private String symbol;
    private String market;

    @Override
    public String toString() {
        return "Equity{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", market='" + market + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
