package tothemars;

import java.util.Random;

public class PriceMovement{
    private Random r = new Random();
    private double movementRate;
    private double movementPercent;
    private String temp;
    public void priceMove(Coin obj) {
        movementPercent=obj.Price;
        movementRate = (r.nextDouble(-0.1,0.1));
        obj.Price = (int)(obj.Price + (obj.Price * movementRate));
    }
    public double priceMovement(double Price1, double Price2){
        double Percent=(Price2-Price1)/Price1*100;
        String temp1 = String.format("%+5.2f", Percent);
        return Percent;
    }
}