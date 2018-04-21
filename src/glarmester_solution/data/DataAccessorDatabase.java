package data;

import logic.FrameType;

import static logic.Controller.DEBUG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author RODA
 */
public class DataAccessorDatabase implements DataAccessor {

    @Override
    public double getGlassprice() {
        try{
            Connection connection = new DBConnector().getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT `price` FROM `prices` WHERE `name` = 'glass';";
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                return res.getDouble("price");
            }
        }catch(Exception e){
            if(DEBUG) e.printStackTrace();
        }
        return Double.NaN;
    }

    @Override
    public double getFramePrice(FrameType type) {
        try{
            Connection connection = new DBConnector().getConnection();
            String query = "SELECT `price` FROM `prices` WHERE `name` = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, type.toString().toLowerCase());
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                return res.getDouble("price");
            }
        }catch(Exception e){
            if(DEBUG) e.printStackTrace();
        }
        return Double.NaN;
    }
    
}
