import java.util.List;
import java.util.Optional;

public class CoffeeMachine {

    public static final int ONE = 1;

    private final List<Ingredient> supplies;
    private final List<CoffeeType> coffeeTypes;
    private int disposableCups;
    private double moneyEarned;
    private MachineState state;

    public CoffeeMachine(List<Ingredient> supplies, List<CoffeeType> coffeeTypes,
                         int disposableCups, double moneyEarned) {
        this.supplies = supplies;
        this.coffeeTypes = coffeeTypes;
        this.disposableCups = disposableCups;
        this.moneyEarned = moneyEarned;
        state = MachineState.WAITING_ACTION;
    }

    public void makeCoffee(CoffeeType coffeeType) {
        if (hasEnoughSupplies(coffeeType)) {
            for (Ingredient ing : coffeeType.getIngredients()) {
                Ingredient supply = findSupplyFor(ing);
                supply.reduceQuantity(ing.getQuantity());
            }
            disposableCups -= ONE;
            moneyEarned += coffeeType.getPrice();
        }
    }

    public boolean hasEnoughSupplies(CoffeeType coffeeType) {
        for (Ingredient ing : coffeeType.getIngredients()) {
            Ingredient supply = findSupplyFor(ing);
            if (supply == null) {
                return false;
            }
            if (supply.getQuantity() < ing.getQuantity()) {
                return false;
            }
        }
        return disposableCups >= ONE;
    }

    private Ingredient findSupplyFor(Ingredient ingredient) {
        Optional<Ingredient> supplyFound = supplies.stream().filter(
                supply -> supply.getName().equals(ingredient.getName())).findFirst();
        return supplyFound.orElse(null);
    }

    public double takeMoneyEarned() {
        double moneyEarned = this.moneyEarned;
        this.moneyEarned = 0;
        return moneyEarned;
    }

    public List<Ingredient> getSupplies() {
        return supplies;
    }

    public List<CoffeeType> getCoffeeTypes() {
        return coffeeTypes;
    }

    public void addDisposableCups(int cups) {
        disposableCups += cups;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Ingredient supply : supplies) {
            res.append(supply).append("\n");
        }
        res.append(disposableCups).append(" of disposable cups\n");
        res.append(moneyEarned).append(" of money\n");
        return res.toString();
    }
}
