package data;

import logic.FrameType;
import logic.PriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {

    private static DBConnector dbConnector;
    @BeforeAll
    static void init() {
        try {
            dbConnector = new DBConnector();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Normal Unit Test
    @Test
    void getConnection() throws SQLException {
        Connection connection = dbConnector.getConnection();

        boolean actual = connection.isValid(2);

        Assertions.assertEquals(true, actual);
    }


    @Test
    void testFramePrice() {
        FrameType frameType = FrameType.Simple;
        double height = 11;
        double width = 22;
        DataAccessor accessor = new DataAccessorDatabase();


        // Checks whether the frameprice is correct
        double frameprice = accessor.getFramePrice(frameType);
        Assertions.assertEquals(100, frameprice);

        PriceCalculator priceCalculator = new PriceCalculator();


        // Price Calculation
        // 11 * 22 * 100 = glassprice - 4400
        // (2 * 11) + (2 * 22) * 100 = frameprice - 6600
        // glassprice + frameprice = totalprice - The one we test - 10.000,-

        double calculatedPrice = priceCalculator.calculatePrice(height, width, frameType, accessor);

        Assertions.assertEquals(10000, calculatedPrice);
    }
}