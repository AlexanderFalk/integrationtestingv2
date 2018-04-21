package presentation;

import logic.FrameType;

import java.util.Scanner;

import static logic.FrameType.Lavish;
import static logic.FrameType.Ornate;
import static logic.FrameType.Simple;

/**
 *
 * @author RODA
 */
public class TUI implements UI {
    private final String ENTER_HEIGHT  = "Enter height of window (in cm.): ";
    private final String ENTER_WIDTH   = "Enter width of window (in cm.): ";
    private final String ENTER_TYPE    = "Select frametype: ";
    private final String DISPLAY_PRICE = "\nPrice:\tkr. %.2f,-";
    private final String[] FRAMETYPES  = { 
        Simple.toString(), 
        Ornate.toString(), 
        Lavish.toString()
    };
    private Scanner scanner = new Scanner(System.in);

    @Override
    public double getFrameHeight(){
        int height = -1;
        do{
            System.out.print(ENTER_HEIGHT);
            String _height = scanner.nextLine();
            height = convertToInt(_height);
        }while(height <= 0);
        return height / 100.0;
    }
    
    @Override
    public double getFrameWidth(){
        int width = -1;
        do{
            System.out.print(ENTER_WIDTH);
            String _width = scanner.nextLine();
            width = convertToInt(_width);
        } while(width < 0);
        return width / 100.0;
    }
    
    @Override
    public FrameType getFrameType(){
        int selection = -1;
        do{
            System.out.println(ENTER_TYPE);
            // Print options
            for(int i = 1; i <= FRAMETYPES.length; i++){
                System.out.println(i + ") " + FRAMETYPES[i-1]);
            }
            String _selection = scanner.nextLine();
            selection = convertToInt(_selection);
            if(selection > 3) selection = -1;
        } while(selection < 0);
        
        // Convert to Enum
        FrameType type = null;
        switch(selection){
            case 1: type = Simple; break;
            case 2: type = Ornate; break;
            case 3: type = Lavish; break;
        }
        return type;
    }
    
    @Override
    public void displayPrice(double price){
        System.out.println(String.format(DISPLAY_PRICE, price));
    }

    // Helper method
    private int convertToInt(String value) {
        try{
            int height = Integer.parseInt(value);
            return height;
        } catch(NumberFormatException e){
            return -1;
        }
    }
    
}
