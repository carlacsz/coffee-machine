package coffee.types;


import coffee.components.*;

public class Latte extends CoffeeMilked {
    public Latte(Water waterNeeded, Milk milkNeeded, CoffeeBean beansNeeded, int cost) {
        super("Latte",waterNeeded, milkNeeded, beansNeeded, cost);
    }
}
