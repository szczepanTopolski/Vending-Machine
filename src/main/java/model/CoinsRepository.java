package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

public class CoinsRepository {

    private List<Coin> coins;
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

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
        return (float) coins.stream().mapToDouble(Coin::getValue).sum();
    }

    public boolean isChangePossible() {
        //TODO if totalvalue - price> coinsRepo.totalvalue() return false;
        return coins.stream().filter(n->n.getValue() <= 0.5f).allMatch(n-> n.getAmount() > 0);
    }

    public List<Coin> calculateChange(float price, CoinsRepository customerCoins) {
        List<Coin> sortedCoins = coins.stream()
                .sorted((f1,f2)->Float.compare(f2.getValue(),f1.getValue()))
                .collect(Collectors.toList());
        List<Coin> totalChange = new ArrayList<>();
        float change = 0;
        float changeWanted = Float.parseFloat(decimalFormat.format(customerCoins.totalValue() - price));
        int maxCurrentCoinAmount;
        float maxCurrentCoinTotalValue;
        for(int i = 0; i < sortedCoins.size() ||  change < changeWanted; i++){
            Coin currentCoin = sortedCoins.get(i);
            maxCurrentCoinAmount = (int)(changeWanted / currentCoin.getValue());
            maxCurrentCoinTotalValue = (float) maxCurrentCoinAmount * currentCoin.getValue();
            change += maxCurrentCoinTotalValue;
            changeWanted -= maxCurrentCoinTotalValue;
            totalChange.add(currentCoin.copyValues(maxCurrentCoinAmount));
            currentCoin.decreaseAmount(maxCurrentCoinAmount);
        }
        return totalChange;
    }
}
