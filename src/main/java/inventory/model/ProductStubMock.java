package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductStubMock extends  Product {
    @Override
    public String getName() {
        return "N";
    }
    @Override
    public ObservableList<Part> getAssociatedParts() {
        return FXCollections.observableArrayList();
    }
}
