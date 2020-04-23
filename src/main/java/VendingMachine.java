import model.Coin;
import model.CoinsRepository;
import model.Product;
import model.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    ProductRepository productRepository;
    CoinsRepository customerCoins;
    CoinsRepository coinsRepository;

    VendingMachine(ProductRepository productRepository, CoinsRepository coinsRepository){
        this.productRepository = productRepository;
        this.coinsRepository = coinsRepository;
        this.customerCoins = new CoinsRepository(new ArrayList<>());

    }


    public boolean checkCustomerAffordAbility(Product product){
        return product.getPrice() <= customerCoins.totalValue();
    }


    public void insertCoin(Coin coin) {
        customerCoins.addToCoins(coin);
    }

    public boolean checkVendingMachineChangePossibility(){
        return coinsRepository.isChangePossible();
    }

    public List<Coin> calculateChange(float price){
        return coinsRepository.calculateChange(price,customerCoins);
    }
}
