package inventory.repository;

import inventory.model.Product;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        Product p1 = new Product(inventory.getAutoProductId(), "product1", 1, 20, 1, 100, FXCollections.observableArrayList());
        Product p2 = new Product(inventory.getAutoProductId(), "product2",1,10,1,100,FXCollections.observableArrayList());
        Product p3 = new Product(inventory.getAutoProductId(), "product3",1,10,1,100,FXCollections.observableArrayList());
        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);
    }

    @Test
    @DisplayName("Find by name - valid")
    void findByNameValid() {
        String name = "product1";
        Product p1 = inventory.lookupProduct(name);
        assertEquals(p1.getName(),"product1");
        name = "prod";
        Product p2 = inventory.lookupProduct(name);
        assertEquals(p2.getName(), "product1");
    }

    @Test
    @DisplayName("Find by id - valid")
    void findByIdValid() {
        String id = "1";
        Product p1 = inventory.lookupProduct(id);
        assertEquals(p1.getName(),"product1");
        id = "2";
        Product p2 = inventory.lookupProduct(id);
        assertEquals(p2.getName(), "product2");
    }

    @Test
    @DisplayName("Find by name - invalid")
    void findByNameInvalid() {
        String name = "asdasfqsfd";
        Product p1 = inventory.lookupProduct(name);
        assertEquals(p1.getProductId(), 0);
        name = "p1";
        Product p2 = inventory.lookupProduct(name);
        assertEquals(p2.getProductId(), 0);
    }

    @Test
    @DisplayName("Find by id - invalid")
    void testFindByIdInvalid() {
        String id = "4";
        Product p1 = inventory.lookupProduct(id);
        assertNull(p1.getName());
        id = "123rfsfd";
        Product p2 = inventory.lookupProduct(id);
        assertNull(p2.getName());
    }

}