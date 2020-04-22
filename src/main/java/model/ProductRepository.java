package model;

import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository(List<Product> products){
        this.products = products;
    }

    public void addToProducts(Product product){
        products.add(product);
    }
    public Product getFromProducts(int i){
        return products.get(i);
    }

    public List<Product> getProducts() {
        return products;
    }
}
