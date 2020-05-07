package inventory.service;

import inventory.model.Product;
import inventory.model.ProductStubMock;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryIntegrationServiceMockTest {
    private InventoryRepository repo;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        service = new InventoryService(repo);
    }

    @Test
    void testAddProductValid() {
        Product p = new ProductStubMock();
        try {
            service.addProduct(p.getName(),1,10,1,100, p.getAssociatedParts());
        } catch (Exception e) {
            //nothing
        }
        assertEquals(1, service.getAllProducts().size());
        assertEquals(service.getAllProducts().get(0).getName(), "N");
    }

    @Test
    void testAddProductInvalid() {
        Product p = new ProductStubMock();
        try {
            service.addProduct(p.getName(),-1,10,1,100,p.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(),"Pretul este negativ!\n");
        }
    }

    @Test
    void testFindProductValid() {
        Product p = new ProductStubMock();
        try {
            service.addProduct(p.getName(),10,10,1,100,p.getAssociatedParts());
        }catch (Exception e) {
            //nothing
        }
        assertEquals(1, service.getAllProducts().size());
        Product p2 = service.lookupProduct("N");
        assertEquals(p2.getPrice(), 10);
    }

    @Test
    void testFindProductInvalid() {
        Product p = new ProductStubMock();
        try {
            service.addProduct(p.getName(),10,10,1,100,p.getAssociatedParts());
        } catch (Exception e) {
            //nothing
        }
        assertEquals(1, service.getAllProducts().size());
        assertNull(service.lookupProduct("fsfsd").getName());
    }

}