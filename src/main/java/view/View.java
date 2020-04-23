package view;

import model.Coin;
import model.Product;

import java.util.List;

public interface View {
    void displayMenu(String[] options);
    void displayProducts(List<Product> products);
    int getUserChoice();
    void displayCoins(List<Coin> coins);
}
