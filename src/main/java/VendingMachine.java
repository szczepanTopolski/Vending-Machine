import model.Coin;
import model.CoinsRepository;
import model.Product;
import model.ProductRepository;

import java.util.ArrayList;

public class VendingMachine {

    ProductRepository productRepository;
    CoinsRepository customerCoins;
    CoinsRepository coinsRepository;

    VendingMachine(ProductRepository productRepository, CoinsRepository coinsRepository){
        this.productRepository = productRepository;
        this.coinsRepository = coinsRepository;
        this.customerCoins = new CoinsRepository(new ArrayList<Coin>());

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
}
