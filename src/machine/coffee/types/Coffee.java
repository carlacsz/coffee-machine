package coffee.types;

import coffee.components.*;

import java.util.ArrayList;
import java.util.Optional;

public class Coffee {
    protected String name;
    protected Water waterNeeded;
    protected CoffeeBean beansNeeded;
    protected DisposableCup cupsNeeded;
    protected int cost;

    public Coffee(String name, Water waterNeeded, CoffeeBean beansNeeded, int cost) {
        this.name = name;
        this.waterNeeded = waterNeeded;
        this.beansNeeded = beansNeeded;
        this.cupsNeeded = new DisposableCup(1);
        this.cost = cost;
    }

    public boolean areSuppliesEnough(ArrayList<Component> supplies){
        Water waterAvailable = (Water) findSupplyFor(waterNeeded, supplies);
        CoffeeBean beansAvailable = (CoffeeBean) findSupplyFor(beansNeeded, supplies);
        DisposableCup cupsAvailable = (DisposableCup) findSupplyFor(cupsNeeded, supplies);
        return waterAvailable.getQuantity() >= waterNeeded.getQuantity() &&
                beansAvailable.getQuantity() >= beansNeeded.getQuantity() &&
                cupsAvailable.getQuantity() >= cupsNeeded.getQuantity();
    }

    public void makeCoffee(ArrayList<Component> supplies){
        Water waterAvailable = (Water) findSupplyFor(waterNeeded, supplies);
        CoffeeBean beansAvailable = (CoffeeBean) findSupplyFor(beansNeeded, supplies);
        DisposableCup cupsAvailable = (DisposableCup) findSupplyFor(cupsNeeded, supplies);
        waterAvailable.setQuantity(waterAvailable.getQuantity() - waterNeeded.getQuantity());
        beansAvailable.setQuantity(beansAvailable.getQuantity() - beansNeeded.getQuantity());
        cupsAvailable.setQuantity(cupsAvailable.getQuantity() - cupsNeeded.getQuantity());
    }

    protected Component findSupplyFor(Component component, ArrayList<Component> supplies) {
        Optional<Component> supplyFound = supplies.stream().filter(c -> c.isSameType(component)).findFirst();
        if(supplies.stream().filter(c -> c.isSameType(component)).findFirst() != null){
            return supplyFound.get();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
