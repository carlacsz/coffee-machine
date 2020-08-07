import coffee.types.Coffee;
import coffee.components.Component;

import java.util.ArrayList;

public class CoffeeMachine {

    private ArrayList<Coffee> coffeeTypes;
    private ArrayList<Component> supplies;
    private int moneyEarned;
    private MachineState state = MachineState.WAITING_ACTION;

    public CoffeeMachine(ArrayList<Coffee> coffeeTypes, ArrayList<Component> supplies, int moneyEarned) {
        this.supplies = supplies;
        this.coffeeTypes = coffeeTypes;
        this.moneyEarned = moneyEarned;
    }

    public ArrayList<Coffee> getCoffeeTypes() {
        return coffeeTypes;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public MachineState getState(){
        return state;
    }

    public int takeMoneyEarned() {
        int moneyEarned = this.moneyEarned;
        this.moneyEarned = 0;
        return moneyEarned;
    }
    public boolean canMakeCoffee(Coffee coffee){
        return coffee.areSuppliesEnough(supplies);
    }
    public void makeCoffee(Coffee coffee) {
        coffee.makeCoffee(supplies);
        moneyEarned += coffee.getCost();
    }

    public ArrayList<Component> getSupplies() {
        return supplies;
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }
}
