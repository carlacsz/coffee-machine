package coffee.types;

import coffee.components.*;

import java.util.ArrayList;

public class CoffeeMilked extends Coffee {
    private Milk milkNeeded;
    public CoffeeMilked(String name, Water waterNeeded, Milk milkNeeded, CoffeeBean beansNeeded, int cost) {
        super(name, waterNeeded, beansNeeded, cost);
        this.milkNeeded = milkNeeded;
    }
    public boolean areSuppliesEnough(ArrayList<Component> supplies){
        Milk milkAvailable = (Milk) findSupplyFor(milkNeeded, supplies);
        return super.areSuppliesEnough(supplies) &&
                milkAvailable.getQuantity() >= milkNeeded.getQuantity();
    }

    public void makeCoffee(ArrayList<Component> supplies){
        super.makeCoffee(supplies);
        Milk milkAvailable = (Milk) findSupplyFor(milkNeeded, supplies);
        milkAvailable.setQuantity(milkAvailable.getQuantity() - milkNeeded.getQuantity());
    }
}
