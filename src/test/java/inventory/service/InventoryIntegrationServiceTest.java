package inventory.service;

import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryIntegrationServiceTest {
    private InventoryRepository repo;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        service = new InventoryService(repo);
    }

    @Test
    void testAddProductValid () {
        Product p = new Product(repo.getAutoProductId(),"N",10,10,1,100, FXCollections.observableArrayList());
        repo.addProduct(p);
        assertEquals(1,service.getAllProducts().size());
        assertEquals("N",service.getAllProducts().get(0).getName());
    }

    @Test
    void testFindProductValid() {
        Product p = new Product(repo.getAutoProductId(),"N",10,10,1,100,FXCollections.observableArrayList());
        repo.addProduct(p);
        assertEquals(1,service.getAllProducts().size());
        assertNull(service.lookupProduct("sfsd").getName());
        assertEquals("N",service.lookupProduct("N").getName());
    }

    @Test
    void testFindProductInvalid() {
        Product p = new Product(repo.getAutoProductId(),"N",10,10,1,100,FXCollections.observableArrayList());
        repo.addProduct(p);
        assertEquals(1,service.getAllProducts().size());
        assertNull(service.lookupProduct("sfsdfsaf").getName());
    }

}