import model.Coin;
import model.CoinsRepository;
import model.Product;
import model.ProductRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {

    public ProductRepository loadProducts(File file) throws IllegalArgumentException{
        ProductRepository productRepository;
        List<Product> products = new ArrayList<>();
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String[] values = scan.nextLine().split(",");
                String name = values[0];
                int amount = Integer.parseInt(values[1]);
                float price = Float.parseFloat(values[2]);
                products.add(new Product(name, amount, price));
            }
            productRepository = new ProductRepository(products);
            return productRepository;
        }catch(FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public CoinsRepository loadCoins(File file)throws IllegalArgumentException{
        CoinsRepository coinsRepository;
        List<Coin> coins = new ArrayList<>();
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String[] values = scan.nextLine().split(",");
                float diameter = Float.parseFloat(values[0]);
                float weight = Float.parseFloat(values[1]);
                float price = Float.parseFloat(values[2]);
                int amount = Integer.parseInt(values[3]);
                coins.add(new Coin(diameter, weight, price,amount));
            }
            coinsRepository = new CoinsRepository(coins);
            return coinsRepository;
        }catch(FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
}
