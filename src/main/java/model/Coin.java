package model;

public class Coin {

    float diameter;
    float weight;
    float value;
    int amount;

    public Coin(float diameter, float weight, float value, int amount){
        this.diameter = diameter;
        this.weight = weight;
        this.value = value;
        this.amount = amount;
    }

    public float getDiameter() {
        return diameter;
    }

    public float getWeight() {
        return weight;
    }

    public float getValue() {
        return value;
    }

    public int getAmount() {
        return amount;
    }

    public Coin copyValues(int amount){
        return new Coin(this.diameter,this.weight,this.value,amount);
    }

    public void decreaseAmount(int amount){
        amount-= amount;
    }
}
