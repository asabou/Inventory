package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


class InventoryRepositoryTestMock {

    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        inventoryRepository = mock(InventoryRepository.class);
    }
    @Test
    void testAddProduct1() {
        Product p = new Product(inventoryRepository.getAutoProductId(),"product",1,10,1,100, FXCollections.observableArrayList());
        Mockito.when(inventoryRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList(p));
        Mockito.doNothing().when(inventoryRepository).addProduct(p);
        assertEquals(1, inventoryRepository.getAllProducts().size());
    }

    @Test
    void testAddProduct2() {
        Product p = new Product(inventoryRepository.getAutoProductId(),"product",1,10,1,100, FXCollections.observableArrayList());
        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length == 1 && arguments[0] != null) {
                Product product = (Product) arguments[0];
            }
            return null;
        }).when(inventoryRepository).addProduct(p);//same behaviour as test_add_valid_Quiz2
        inventoryRepository.addProduct(p);
        Mockito.verify(inventoryRepository, times(1)).addProduct(p);
    }

    @Test
    void testFindProductValid() {
        Product p1 = new Product(inventoryRepository.getAutoProductId(), "product1", 1, 10,1, 100, FXCollections.observableArrayList());
        Product p2 = new Product(inventoryRepository.getAutoProductId(), "product2", 1, 10,1, 100, FXCollections.observableArrayList());
        Mockito.when(inventoryRepository.lookupProduct("product1")).thenReturn(p1);
        assertEquals(p1, inventoryRepository.lookupProduct("product1"));
        Mockito.when(inventoryRepository.lookupProduct("product2")).thenReturn(p2);
        assertEquals(p2, inventoryRepository.lookupProduct("product2"));
    }

    @Test
    void testFindProductInvalid() {
        Mockito.when(inventoryRepository.lookupProduct("ceva")).thenReturn(new Product(0,null,0,0,0,0,null));
        assertEquals(0, inventoryRepository.lookupProduct("ceva").getProductId());
    }


}