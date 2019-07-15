import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FarmStocksTest {

    private FarmStocksDB db;

    @BeforeEach
    void setUp() {
        db = new FarmStocksDB("DB/FarmStocks.xlsx");
    }

    @AfterEach
    void tearDown() {
        db = null;
    }
    
    //This test insures that the database contains the correct number of supplies, 
    //as well as that the function to display the supplies to the user works.
    @Test
    void testSupplies() {
        int numSupplies = 15;
        assertEquals(numSupplies, db.getNumSupplies());
        assertEquals(numSupplies, db.getSupplies().size());
    }
    
    //This test insures that the quantity stored for a specific item is correct.
    @Test
    void testSupplyQuantity() {
        int quantity = 50;
        assertEquals(quantity, db.getSupplyByName("Grain").getQuantity());
    }

    @Test
    void testSupplyPrice() {
        double price = 100.00;
        assertEquals(price, db.getSupplyByName("Tractors").getPrice());
    }

    @Test
    void testNearestLocation() {
        String city = "Buford";
        assertEquals(city, db.getSupplyByName("Livestock").getNearestLocation());
    }

    @Test
    void testBuy() {
        Supply combine = db.getSupplyByName("Combine");
        assertEquals(0, combine.getQuantity());
        combine.buy(1);
        assertEquals(1, combine.getQuantity());
    }

    @Test
    void testBuyNegative() {
        Supply combine = db.getSupplyByName("Combine");
        assertEquals(0, combine.getQuantity());
        assertThrows(IllegalArgumentException.class, () -> combine.buy(-1));
    }

    @Test
    void testBuyZero() {
        Supply combine = db.getSupplyByName("Combine");
        assertEquals(0, combine.getQuantity());
        combine.buy(0);
        assertEquals(0, combine.getQuantity());
    }

    @Test
    void testBuyMultiple() {
        Supply combine = db.getSupplyByName("Combine");
        assertEquals(0, combine.getQuantity());
        combine.buy(2);
        assertEquals(2, combine.getQuantity());
    }
}
