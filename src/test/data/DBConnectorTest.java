package data;

import logic.FrameType;
import logic.PriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import presentation.TUI;
import presentation.UI;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
    void testGlassprice() {
        DataAccessor accessor = new DataAccessorDatabase();
        double expected = 300;
        double actual = accessor.getGlassprice();

        assertEquals(expected, actual);
    }

    @Test
    void testFramePrice() {
        FrameType frameType = FrameType.Simple;
        // CM
        double height = 0.11;
        double width = 0.22;
        DataAccessor accessor = new DataAccessorDatabase();


        // Checks whether the frameprice is correct
        double frameprice = accessor.getFramePrice(frameType);
        Assertions.assertEquals(100, frameprice);

        PriceCalculator priceCalculator = new PriceCalculator();

        // Price Calculation
        // 0.11 * 0.22 * 100 = glassprice = 7,26
        // (2 * 11) + (2 * 22) * 100 = frameprice / 100 = 66
        // glassprice + frameprice = totalprice - The one we test - 73,26,-

        double calculatedPrice = priceCalculator.calculatePrice(height, width, frameType, accessor);
        Assertions.assertEquals(73.26, calculatedPrice);
    }

    @Test
    void testTUIInsertion() throws InterruptedException {
        DataAccessor accessor = new DataAccessorDatabase();
        FrameType frameType = FrameType.Lavish;

        // CM
        double height = 0.44;
        double width = 0.32;

        String inputHeight = "44" + "\n" + "32" + "\n" + "3";
        InputStream in = new ByteArrayInputStream(inputHeight.getBytes());
        System.setIn(in);


        UI ui = new TUI();
        assertEquals(height, ui.getFrameHeight());
        assertEquals(width, ui.getFrameWidth());
        assertEquals(frameType, ui.getFrameType());

        // Now price calculation
        // Price Calculation
        // 0.44 * 0.32 * 300 = glassprice = 42,24
        // (2 * 44) + (2 * 32) * 350 = frameprice / 100 = 532
        // glassprice + frameprice = totalprice - The one we test - 574,24
        PriceCalculator priceCalculator = new PriceCalculator();
        double price = priceCalculator.calculatePrice(height, width, frameType, accessor);
        double expected = 574.24;
        assertEquals(expected, price);
        System.out.println("Actual price: " + price);
    }
}