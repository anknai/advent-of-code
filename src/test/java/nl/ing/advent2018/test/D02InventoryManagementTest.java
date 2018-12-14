package nl.ing.advent2018.test;

import nl.ing.advent2018.D02InventoryManagement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D02InventoryManagementTest {

    private D02InventoryManagement inventoryManagement = new D02InventoryManagement();

    private final static String TEST_INPUT = "02_test.txt";

    private final static String TEST_INPUT_2 = "02_test_2.txt";

    private final static String INPUT = "02_inventory.txt";

    @Test
    public void checksum() {
        int checksum = inventoryManagement.checksum(TEST_INPUT);
        assertEquals("Checksum is ", 12, checksum);
        checksum = inventoryManagement.checksum(INPUT);
        assertEquals("Checksum is ", 5166, checksum);
    }

    @Test
    public void closest() {
        String closest = inventoryManagement.closest(TEST_INPUT_2);
        assertEquals("Closest is ", "fgij", closest);
        closest = inventoryManagement.closest(INPUT);
        assertEquals("Closest is ", "cypueihajytordkgzxfqplbwn", closest);
    }
}