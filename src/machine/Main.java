import coffee.components.*;
import coffee.types.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Component> supplies = new ArrayList<>();
        supplies.add(new Water(400));
        supplies.add(new Milk(540));
        supplies.add(new CoffeeBean(120));
        supplies.add(new DisposableCup(9));

        ArrayList<Coffee> coffeeTypes = new ArrayList();
        coffeeTypes.add(new Espresso(
                new Water(250),
                new CoffeeBean(16),
                4));
        coffeeTypes.add(new Latte(
                new Water(350),
                new Milk(75),
                new CoffeeBean(20),
                7));
        coffeeTypes.add(new Cappuccino(
                new Water(200),
                new Milk(100),
                new CoffeeBean(12),
                6));
        CoffeeMachine machine =
                new CoffeeMachine(coffeeTypes, supplies, 550);
        CoffeeMachineUI coffeeMachineUI = new CoffeeMachineUI(machine);
        coffeeMachineUI.start();
    }
}

