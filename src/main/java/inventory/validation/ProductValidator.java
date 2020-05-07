package inventory.validation;

import inventory.model.Product;

public class ProductValidator {
    public void validate(Product p) throws Exception {
        String err="";
        if(p.getName().length()==0){
            err+="Denumirea este un string null!\n";
        }
        if(p.getName().length()>255){
            err+="Denumirea are prea multe caractere!\n";
        }
        else if(p.getPrice()<=0){
            err+="Pretul este negativ!\n";
        }
        if(err.length()>0){
            throw  new Exception(err);
        }

    }
}
