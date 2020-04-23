package view;

import model.Coin;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class TerminalView implements View {

    public void displayMenu(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("(%d) %s", i+1, options[i]));
        }
    }

    public void displayProducts(List<Product> products) {
        Product product;
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            System.out.println(String.format("(%d) name: %s   price: %f", i+1, product.getName(), product.getPrice()));
        }
    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void displayCoins(List<Coin> coins) {
        System.out.println("Coins inserted: ");
        for (Coin coin : coins) {
            System.out.println(coin.toString());
        }
    }
}
