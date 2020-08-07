package coffee.types;

import coffee.components.*;

public class Cappuccino extends CoffeeMilked {
    public Cappuccino(Water waterNeeded, Milk milkNeeded, CoffeeBean beansNeeded, int cost) {
        super("Cappuccino", waterNeeded, milkNeeded, beansNeeded, cost);
    }
}
