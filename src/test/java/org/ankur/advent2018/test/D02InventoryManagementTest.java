package org.ankur.advent2018.test;

import org.ankur.advent2018.D02InventoryManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D02InventoryManagementTest {

    private D02InventoryManagement inventoryManagement = new D02InventoryManagement();

    private final static String TEST_INPUT = "02_test.txt";

    private final static String TEST_INPUT_2 = "02_test_2.txt";

    private final static String INPUT = "02_inventory.txt";

    @Test
    void checksum() {
        int checksum = inventoryManagement.checksum(TEST_INPUT);
        assertEquals(12, checksum);
        checksum = inventoryManagement.checksum(INPUT);
        assertEquals(5166, checksum);
    }

    @Test
    void closest() {
        String closest = inventoryManagement.closest(TEST_INPUT_2);
        assertEquals( "fgij", closest);
        closest = inventoryManagement.closest(INPUT);
        assertEquals("cypueihajytordkgzxfqplbwn", closest);
    }
}