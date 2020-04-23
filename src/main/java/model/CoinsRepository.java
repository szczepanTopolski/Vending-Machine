package model;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CoinsRepository {
    private List<Coin> coins;

    public CoinsRepository(List<Coin> products){
        this.coins = products;
    }

    public void addToCoins(Coin product){
        coins.add(product);
    }
    public Coin getFromCoinsByIndex(int i){
        return coins.get(i);
    }

    public Optional<Coin> getFromCoinsByValue(float value){
        return coins.stream().filter(n->n.getValue() == value).findFirst();
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public float totalValue(){
        AtomicReference<Float> sum = new AtomicReference<>((float) 0);
        coins.forEach(n-> sum.updateAndGet(v -> (float) (v + n.getValue())));
        return sum.get();
    }

    public boolean isChangePossible() {
        return coins.stream().filter(n->n.getValue() <= 0.5f).allMatch(n-> n.getAmount() > 0);
    }
}
