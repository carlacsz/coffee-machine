import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IngredientType water = IngredientType.WATER;
        IngredientType milk = IngredientType.MILK;
        IngredientType coffeeBeans = IngredientType.COFFEE_BEANS;
        IngredientType chocolate = IngredientType.CHOCOLATE;

        List<Ingredient> espIngredients = new ArrayList<>();
        espIngredients.add(new Ingredient(water, 250));
        espIngredients.add(new Ingredient(coffeeBeans, 16));
        CoffeeType espresso = new CoffeeType("Espresso", espIngredients, 4);

        List<Ingredient> latteIngredients = new ArrayList<>();
        latteIngredients.add(new Ingredient(water, 350));
        latteIngredients.add(new Ingredient(milk, 75));
        latteIngredients.add(new Ingredient(coffeeBeans, 20));
        CoffeeType latte = new CoffeeType("Latte", latteIngredients, 7);

        List<Ingredient> capIngredients = new ArrayList<>();
        capIngredients.add(new Ingredient(water, 200));
        capIngredients.add(new Ingredient(milk, 100));
        capIngredients.add(new Ingredient(coffeeBeans, 12));
        CoffeeType cappuccino = new CoffeeType("Cappuccino", capIngredients, 6);

        List<Ingredient> mochaIngredients = new ArrayList<>();
        mochaIngredients.add(new Ingredient(water, 250));
        mochaIngredients.add(new Ingredient(milk, 100));
        mochaIngredients.add(new Ingredient(coffeeBeans, 16));
        mochaIngredients.add(new Ingredient(chocolate, 10));
        CoffeeType mocha = new CoffeeType("Mocha", mochaIngredients, 8);

        List<Ingredient> americanoIngs = new ArrayList<>();
        americanoIngs.add(new Ingredient(water, 300));
        americanoIngs.add(new Ingredient(coffeeBeans, 16));
        CoffeeType americano = new CoffeeType("Americano", americanoIngs, 5);

        List<Ingredient> supplies = new ArrayList<>();
        supplies.add(new Ingredient(water, 400));
        supplies.add(new Ingredient(milk, 540));
        supplies.add(new Ingredient(coffeeBeans, 120));
        supplies.add(new Ingredient(chocolate, 100));

        List<CoffeeType> coffeeTypes = new ArrayList();
        coffeeTypes.add(espresso);
        coffeeTypes.add(latte);
        coffeeTypes.add(cappuccino);
        coffeeTypes.add(mocha);
        coffeeTypes.add(americano);

        CoffeeMachine machine = new CoffeeMachine(supplies, coffeeTypes, 9, 550);
        MachineHandler machineHandler = new MachineHandler(machine);
        machineHandler.start();
    }
}

