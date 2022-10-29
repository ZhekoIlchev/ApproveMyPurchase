package entity;

import common.Type;

public class Purchase {
    private Integer id;
    private Double cost;
    private Type type;

    public Purchase(Integer id, Double cost, Type type) {
        this.setId(id);
        this.setCost(cost);
        this.setType(type);
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    protected void setCost(Double cost) {
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    private void setType(Type type) {
        this.type = type;
    }
}