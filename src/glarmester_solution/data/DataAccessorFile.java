package data;

import logic.FrameType;

import java.io.FileReader;
import java.io.IOException;
import static logic.Controller.DEBUG;
import java.io.BufferedReader;

public class DataAccessorFile implements DataAccessor {
    private String FILENAME = "data.txt";
    private double glassPrice, simple, ornate, lavish;
    
    public DataAccessorFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            while((line = reader.readLine()) != null){
                line = line.toLowerCase();
                String _value = line.split(":")[1].trim();
                if(line.startsWith("glass")) glassPrice = Double.parseDouble(_value);
                if(line.startsWith("simple")) simple = Double.parseDouble(_value);
                if(line.startsWith("ornate")) ornate = Double.parseDouble(_value);
                if(line.startsWith("lavish")) lavish = Double.parseDouble(_value);
            }
        } catch (IOException ex) {
            if(DEBUG)ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                if(DEBUG)ex.printStackTrace();
            }
        }
    }

    @Override
    public double getGlassprice() {
        return glassPrice;
    }

    @Override
    public double getFramePrice(FrameType type) {
        switch(type){
            case Simple: return simple;
            case Ornate: return ornate;
            case Lavish: return lavish;
            default: return Double.NaN;
        }
    }
    
}
