package inventory.service;

import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryRepository inventoryRepository;
    private InventoryService inventoryService;
    private Product product1, product2, product3, product4, product5, product6, product7, product8, product9, product10;
    private ObservableList<Part> partList = FXCollections.observableArrayList();

    @BeforeEach
    void setUp() {
        inventoryRepository = new InventoryRepository();
        inventoryService = new InventoryService(inventoryRepository);
        product1 = new Product(inventoryRepository.getAutoProductId(),"den1",5,3,1,100,partList);
        product2 = new Product(inventoryRepository.getAutoProductId(), "",6.0,10,1,100,partList);
        product3 = new Product(inventoryRepository.getAutoProductId(), "den3",0,10,1,100,partList);
        product4 = new Product(inventoryRepository.getAutoProductId(),"den4",-1,10,1,100,partList);
        product5 = new Product(inventoryRepository.getAutoProductId(),"",0,10,1,100,partList);
        product6 = new Product(inventoryRepository.getAutoProductId(), "",-1,10,1,100,partList);
        product7 = new Product(inventoryRepository.getAutoProductId(),"den2",5,6,3,9,partList);
        product8 = new Product(inventoryRepository.getAutoProductId(),"d",4,6,1,10,partList);
        String s = "qwertyuiopasdfghqwertyuioqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmbnmasdfghjklqwertyuiop";
        product9 = new Product(inventoryRepository.getAutoProductId(),s,3,10,1,100,partList);
        product10 = new Product(inventoryRepository.getAutoProductId(),s+"a",1,10,1,100,partList);
    }

    @Disabled
    void testJUnit(){
        System.out.println("testJunit");
    }

    /*TC1_EC valid*/
    @Test
    @DisplayName("add product valid")
    void testAddProduct1(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product1.getName(),product1.getPrice(),product1.getInStock(),product1.getMin(),product1.getMax(),product1.getAssociatedParts());
        } catch (Exception e){
            assertNull(e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1+1, nr2);
    }

    /*TC2_EC valid*/
    @Test
    @DisplayName("add product valid")
    void testAddProduct7(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product7.getName(),product7.getPrice(),product7.getInStock(),product7.getMin(),product7.getMax(),product7.getAssociatedParts());
        } catch (Exception e){
            assertNull(e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1+1, nr2);
    }

    /*TC3_EC invalid*/
    @RepeatedTest(5)
    @DisplayName("product with a invalid name")
    void testAddProduct2(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product2.getName(),product2.getPrice(),product2.getInStock(),product2.getMin(),product2.getMax(),product2.getAssociatedParts());
        }catch (Exception e){
            assertEquals("Denumirea este un string null!\n", e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1,nr2);
    }
    /*TC6_EC invalid*/
    @Test
    @Tag("development")
    @DisplayName("product with invalid price")
    void testAddProduct3(){
        int nr2 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product3.getName(),product3.getPrice(),product3.getInStock(),product3.getMin(),product3.getMax(),product3.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(), "Pretul este negativ!\n");
        }
        int nr1 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC7_EC invalid*/
    @Test
    @DisplayName("product with invalid name and invalid price")
    void testAddProduct5(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product5.getName(),product5.getPrice(),product5.getInStock(),product5.getMin(),product5.getMax(),product5.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(), "Denumirea este un string null!\nPretul este negativ!\n");
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC1_BVA invalid*/
    @DisplayName("product with invalid name")
    @ParameterizedTest
    @ValueSource(strings = {""})
    void testAddProduct(String name){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(name,product1.getPrice(),product1.getInStock(),product1.getMin(),product1.getMax(),product1.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(),"Denumirea este un string null!\n");
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC4_BVA valid*/
    @DisplayName("product valid")
    @RepeatedTest(4)
    void testAddProduct8(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product8.getName(),product8.getPrice(),product8.getInStock(),product8.getMin(),product8.getMax(),product8.getAssociatedParts());
        }catch (Exception e){
            assertNull(e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1+1, nr2);
    }

    /*TC5_BVA valid*/
    @DisplayName("product valid with 255 chars in name")
    @Test
    void testAddProduct9(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product9.getName(),product9.getPrice(),product9.getInStock(),product9.getMin(),product9.getMax(),product9.getAssociatedParts());
        }catch (Exception e){
            assertNull(e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1+1, nr2);
    }

    /*TC7_BVA invalid*/
    @DisplayName("product with 256 chars in name")
    @Test
    void testAddProduct10(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product10.getName(),product10.getPrice(),product10.getInStock(),product10.getMin(),product10.getMax(),product10.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(),"Denumirea are prea multe caractere!\n");
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC8_BVA invalid*/
    @DisplayName("product with price = -1")
    @Test
    void testAddProduct4(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product4.getName(),product4.getPrice(),product4.getInStock(),product4.getMin(),product4.getMax(),product4.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(),"Pretul este negativ!\n");
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC9_BVA invalid*/
    @Test
    @DisplayName("product with price 0")
    void testAddProductWithPrice0(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product3.getName(),product3.getPrice(),product3.getInStock(),product3.getMin(),product3.getMax(),product3.getAssociatedParts());
        }catch (Exception e){
            assertEquals(e.getMessage(),"Pretul este negativ!\n");
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1, nr2);
    }

    /*TC10_BVA | TC11_BVA valid*/
    @Test
    @DisplayName("product with price > 0")
    void testAddProductWithPositivePrice(){
        int nr1 = inventoryService.getAllProducts().size();
        try{
            inventoryService.addProduct(product1.getName(),product1.getPrice(),product1.getInStock(),product1.getMin(),product1.getMax(),product1.getAssociatedParts());
        } catch (Exception e){
            assertNull(e.getMessage());
        }
        int nr2 = inventoryService.getAllProducts().size();
        assertEquals(nr1+1, nr2);
    }

}