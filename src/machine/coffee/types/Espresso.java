package coffee.types;

import coffee.components.*;

public class Espresso extends Coffee {
    public Espresso(Water waterNeeded, CoffeeBean beansNeeded, int cost) {
        super("Espresso", waterNeeded, beansNeeded, cost);
    }
}
