package logic;

import data.DataAccessor;

/**
 *
 * @author RODA
 */
public class PriceCalculator {

    public double calculatePrice(double height, double width, FrameType type, DataAccessor data){
        double glassPrice = height * width * data.getGlassprice();
        double framePrice = ((2 * height) + (2 * width)) * data.getFramePrice(type);
        double totalPrice = glassPrice + framePrice;
        return totalPrice;
    }
}
