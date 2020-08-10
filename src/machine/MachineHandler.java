import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MachineHandler {

    private final CoffeeMachine coffeeMachine;
    private final Scanner scanner = new Scanner(System.in);
    private final HashMap<String, CoffeeType> menu = new HashMap<>();
    private String menuQuestion;

    public MachineHandler(CoffeeMachine coffeeMachine) {
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
                action = action.toLowerCase();
                switch (action) {
                    case "buy":
                        coffeeMachine.setState(MachineState.BUYING);
                        System.out.println(menuQuestion);
                        break;
                    case "fill":
                        coffeeMachine.setState(MachineState.FILLING);
                        fill();
                        returnToWaitingForAction();
                        break;
                    case "take":
                        System.out.println("I gave you $" + coffeeMachine.takeMoneyEarned());
                        returnToWaitingForAction();
                        break;
                    case "remaining":
                        System.out.println(coffeeMachine);
                        returnToWaitingForAction();
                        break;
                    case "exit":
                        coffeeMachine.setState(MachineState.STOPPED);
                        return;
                    default:
                        System.out.println("Invalid action");
                        printActions();
                        break;
                }
                break;
            case BUYING:
                buy(action);
                returnToWaitingForAction();
                break;
        }
    }

    private void returnToWaitingForAction() {
        coffeeMachine.setState(MachineState.WAITING_ACTION);
        printActions();
    }

    private void fill() {
        int suppliesFilled = 0;
        List<Ingredient> supplies = coffeeMachine.getSupplies();
        while (suppliesFilled < supplies.size()) {
            Ingredient supply = supplies.get(suppliesFilled);
            String question = String.format("Write how many %s of %s do you want to add:",
                    supply.getMeasure(), supply.getName());
            System.out.println(question);
            try {
                supply.addQuantity(Integer.parseInt(readLine()));
                suppliesFilled++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please enter a valid amount");
            }
        }
        while (true) {
            System.out.println("Write how many disposable cups do you want to add:");
            try {
                coffeeMachine.addDisposableCups(Integer.parseInt(readLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please enter a valid amount");
            }
        }
    }

    private void buy(String option) {
        if ("back".equalsIgnoreCase(option)) {
            return;
        }
        CoffeeType coffeeType = menu.get(option);
        if (coffeeType != null) {
            if (coffeeMachine.hasEnoughSupplies(coffeeType)) {
                System.out.println("I have enough resources, making you a(n) " + coffeeType.getName());
                coffeeMachine.makeCoffee(coffeeType);
            } else {
                System.out.println("Sorry, not enough resources!");
            }
        } else {
            System.out.println("Invalid type of coffee");
        }
    }

    private void generateMenu() {
        List<CoffeeType> coffeeTypeTypes = coffeeMachine.getCoffeeTypes();
        StringBuilder question = new StringBuilder("What do you want to buy? ");
        for (int i = 0; i < coffeeTypeTypes.size(); i++) {
            String itemNumber = String.valueOf(i + 1);
            menu.put(itemNumber, coffeeTypeTypes.get(i));
            question.append(itemNumber).append(" - ").append(coffeeTypeTypes.get(i).getName()).append(", ");
        }
        question.append("back - to main menu:");
        menuQuestion = question.toString();
    }

    private void printActions() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private String readLine() {
        return scanner.nextLine();
    }

}
