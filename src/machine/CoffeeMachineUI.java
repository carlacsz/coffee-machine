import coffee.types.Coffee;
import coffee.components.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMachineUI {
    private CoffeeMachine coffeeMachine;
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Coffee> menu = new HashMap<>();
    private String menuQuestion;

    public CoffeeMachineUI(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
        generateMenu();
    }

    public void start() {
        printActions();
        while (coffeeMachine.getState() != MachineState.STOPPED) {
            processAction(readLine());
        }
    }

    public void processAction(String action) {
        MachineState state = coffeeMachine.getState();
        switch (state) {
            case WAITING_ACTION:
                switch (action) {
                    case "buy":
                        coffeeMachine.setState(MachineState.BUYING);
                        System.out.println(menuQuestion);
                        break;
                    case "fill":
                        coffeeMachine.setState(MachineState.FILLING);
                        processAction(null);
                        break;
                    case "take":
                        take();
                        returnToWaitingForAction();
                        break;
                    case "remaining":
                        printRemaining();
                        returnToWaitingForAction();
                        break;
                    case "exit":
                        coffeeMachine.setState(MachineState.STOPPED);
                        return;
                    default:
                        System.out.println("Invalid action");
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        break;
                }
                break;
            case BUYING:
                buy(action);
                returnToWaitingForAction();
                break;
            case FILLING:
                fill();
                returnToWaitingForAction();
                break;
        }
    }

    private void returnToWaitingForAction() {
        coffeeMachine.setState(MachineState.WAITING_ACTION);
        printActions();
    }

    private void fill(){
        int suppliesFilled = 0;
        ArrayList<Component> supplies = coffeeMachine.getSupplies();
        while (suppliesFilled < supplies.size()) {
            Component supply = supplies.get(suppliesFilled);
            String question = String.format("Write how many %s of %s do you want to add:",
                    supply.getMeasure(), supply.getName());
            System.out.println(question);
            try {
                supply.addAmount(Integer.parseInt(readLine()));
                suppliesFilled++;
            } catch (NumberFormatException  e) {
                System.out.println("Invalid number, please enter a valid amount");
            }
        }
    }
    private void buy(String option) {
        if ("back".equalsIgnoreCase(option)) {
            return;
        }
        Coffee coffee = menu.get(option);
        if (coffee != null) {
            if (coffeeMachine.canMakeCoffee(coffee)) {
                System.out.println("I have enough resources, making you a(n) " + coffee.getName());
                coffeeMachine.makeCoffee(coffee);
            } else {
                System.out.println("Sorry, not enough resources!");
            }
        } else {
            System.out.println("Invalid type of coffee");
            System.out.println(menuQuestion);
        }
    }

    private void take() {
        System.out.println("I gave you $" + coffeeMachine.takeMoneyEarned());
    }

    private void generateMenu() {
        ArrayList<Coffee> coffeeTypes = coffeeMachine.getCoffeeTypes();
        menuQuestion = "What do you want to buy? ";
        for (int i = 0; i < coffeeTypes.size(); i++) {
            String itemNumber = String.valueOf(i + 1);
            menu.put(itemNumber, coffeeTypes.get(i));
            menuQuestion += itemNumber + " - " + coffeeTypes.get(i).getName() + ", ";
        }
        menuQuestion += "back - to main menu:";
    }
    private void printRemaining() {
        for (Component supply : coffeeMachine.getSupplies()) {
            System.out.println(supply);
        }
        System.out.println(String.format("$%d of money", coffeeMachine.getMoneyEarned()));
    }
    private String readLine() {
        return scanner.nextLine();
    }
    private void printActions() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
