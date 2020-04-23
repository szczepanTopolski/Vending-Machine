import model.Coin;
import model.Product;
import view.View;

import java.io.File;
import java.util.List;

public class Controller {

    View view;
    DataAccess dataAccess;
    VendingMachine vendingMachine;

    Controller(View view, DataAccess dataAccess){
        this.dataAccess = dataAccess;
        this.view = view;
        vendingMachine = new VendingMachine(dataAccess.loadProducts(new File("src/main/resources/products.txt")),
                dataAccess.loadCoins(new File("src/main/resources/coins.txt")));
    }


    public void startTransaction() {
        int input;
        boolean transactionCompleted = false;
        String[] options = {"Insert coin", "Buy product", "Quit"};
        if(!vendingMachine.checkVendingMachineChangePossibility()){
            System.out.println("Change impossible. EXACT CHANGE ONLY");
        }
        while(!transactionCompleted){
            System.out.println("TOTAL VALUE INSERTED: $"+vendingMachine.customerCoins.totalValue());
            view.displayMenu(options);
            input = view.getUserChoice();
            switch (input) {
                case 1:
                    tryInsertCoin();
                    break;
                case 2:
                    transactionCompleted = tryBuyProduct();
                    break;
                case 3:
                    view.displayCoins(vendingMachine.customerCoins.getCoins());
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private void tryInsertCoin() {
        String[] options = {"$0.25", "$0.10", "$0.05", "Back"};
        view.displayMenu(options);
        int input = view.getUserChoice();
        if(input == 4) return;
        switch (input){
            case 1:
                vendingMachine.insertCoin(new Coin(24.26f,6.25f,0.25f,1));
                break;
            case 2:
                vendingMachine.insertCoin(new Coin(17.91f,2.268f,0.10f,1));
                break;
            case 3:
                vendingMachine.insertCoin(new Coin(21.21f,5.00f,0.05f,1));
                break;
            default:
                System.out.println("Invalid choice! Try again.");
        }

    }

    private boolean tryBuyProduct() {
        final List<Product> products = vendingMachine.productRepository.getProducts();
        view.displayProducts(products);
        int input = view.getUserChoice();
        if(input >= 4 || input <= 0){
            System.out.println("Invalid choice! Try again.");
            return false;
        }
        final Product product = products.get(input - 1);
        if(vendingMachine.checkCustomerAffordAbility(product)){
            view.displayCoins(vendingMachine.calculateChange(product.getPrice()));
            return true;
        }else{
            System.out.println("You cannot afford it! INSERT COIN");
            return false;
        }
    }
}
