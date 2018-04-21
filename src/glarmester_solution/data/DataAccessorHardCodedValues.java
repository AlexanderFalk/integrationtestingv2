package data;

import logic.FrameType;

/**
 *
 * @author RODA
 */
public class DataAccessorHardCodedValues implements DataAccessor {

    @Override
    public double getGlassprice() {
        return 300;
    }

    @Override
    public double getFramePrice(FrameType type) {
        switch(type){
            case Simple: return 100;
            case Ornate: return 200;
            case Lavish: return 350;
            default: return Double.NaN; // Not possible
        }
    }
    
}
