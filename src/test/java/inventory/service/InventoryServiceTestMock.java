package inventory.service;

import inventory.model.Product;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

class InventoryServiceTestMock {

    @Mock
    private InventoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddProductValid() throws Exception {
        Product p = new Product(1,"product",1,10,1,100, FXCollections.observableArrayList());
        Mockito.when(service.getAllProducts()).thenReturn(FXCollections.observableArrayList(p));
        Mockito.doNothing().when(service).addProduct("product",1,10,1,100, FXCollections.observableArrayList());
        service.addProduct("product",1,10,1,100, FXCollections.observableArrayList());
        assertEquals(1, service.getAllProducts().size());
    }

    @Test
    void testAddProductInvalid() throws Exception {
        Product p = new Product(1, "",1,10,1,100,FXCollections.observableArrayList());
        Mockito.doThrow(Exception.class).when(service).addProduct("",1,10,1,100,FXCollections.observableArrayList());
        Mockito.when(service.getAllProducts()).thenReturn(FXCollections.observableArrayList());
        assertEquals(0, service.getAllProducts().size());
    }

    @Test
    void testFindProductValid() {
        Product p1 = new Product(1, "product1", 1, 10,1, 100, FXCollections.observableArrayList());
        Product p2 = new Product(2, "product2", 1, 10,1, 100, FXCollections.observableArrayList());
        Mockito.when(service.lookupProduct("product1")).thenReturn(p1);
        assertEquals(p1, service.lookupProduct("product1"));
        Mockito.when(service.lookupProduct("product2")).thenReturn(p2);
        assertEquals(p2, service.lookupProduct("product2"));
    }

    @Test
    void testFindProductInvalid() {
        Mockito.when(service.lookupProduct("ceva")).thenReturn(new Product(0,null,0,0,0,0,null));
        assertEquals(0, service.lookupProduct("ceva").getProductId());
    }

}