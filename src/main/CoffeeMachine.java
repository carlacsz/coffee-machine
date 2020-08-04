import java.util.Scanner;

public class CoffeeMachine {

    private int water;
    private int milk;
    private int beans;
    private int disposableCups;
    private int moneyEarned;
    private machineState state;

    public CoffeeMachine(int water, int milk, int beans,
                         int disposableCups, int moneyEarned) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.disposableCups = disposableCups;
        this.moneyEarned = moneyEarned;
        setWaitingAction();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine =
                new CoffeeMachine(400, 540, 120, 9, 550);
        while (machine.state != machineState.STOPPED) {
            machine.processAction(scanner.nextLine());
        }
    }

    public void processAction(String action) {
        switch (state) {
            case WAITING_ACTION:
                switch (action) {
                    case "buy":
                        state = machineState.BUYING;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        break;
                    case "fill":
                        state = machineState.FILLING_WATER;
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case "take":
                        take();
                        setWaitingAction();
                        break;
                    case "remaining":
                        printSupplies();
                        setWaitingAction();
                        break;
                    case "exit":
                        state = machineState.STOPPED;
                        return;
                    default:
                        System.out.println("Invalid action");
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        break;
                }
                break;
            case BUYING:
                buy(action);
                break;
            case FILLING_WATER:
                try {
                    water += Integer.parseInt(action);
                    state = machineState.FILLING_MILK;
                    System.out.println("Write how many ml of milk do you want to add:");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please write a valid amount of water mls to add:");
                }
                break;
            case FILLING_MILK:
                try {
                    milk += Integer.parseInt(action);
                    state = machineState.FILLING_BEANS;
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please write a valid amount of milk mls to add:");
                }
                break;
            case FILLING_BEANS:
                try {
                    beans += Integer.parseInt(action);
                    state = machineState.FILLING_CUPS;
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please write a valid amount of coffee beans grams to add:");
                }
                break;
            case FILLING_CUPS:
                try {
                    disposableCups += Integer.parseInt(action);
                    setWaitingAction();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please write a valid amount of disposable cups to add:");
                }
                break;
        }
    }

    private void setWaitingAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        state = machineState.WAITING_ACTION;
    }

    private void buy(String type) {
        switch (type) {
            case "1":
                makeCoffee(coffeeType.ESPRESSO);
                setWaitingAction();
                break;
            case "2":
                makeCoffee(coffeeType.LATTE);
                setWaitingAction();
                break;
            case "3":
                makeCoffee(coffeeType.CAPPUCCINO);
                setWaitingAction();
                break;
            case "back":
                setWaitingAction();
                break;
            default:
                System.out.println("Invalid type of coffee");
                System.out.println("Write 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
        }
    }

    private void take() {
        System.out.println("I gave you $" + moneyEarned);
        moneyEarned = 0;
    }

    private void printSupplies() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println(moneyEarned + " of money");
    }

    private void makeCoffee(coffeeType type) {
        if (water >= type.getWaterNeeded() && milk >= type.getMilkNeeded() &&
                beans >= type.getBeansNeeded() && disposableCups >= 1) {
            water -= type.getWaterNeeded();
            milk -= type.getMilkNeeded();
            beans -= type.getBeansNeeded();
            disposableCups -= 1;
            moneyEarned += type.getCost();
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (water < type.getWaterNeeded()) {
                System.out.println("Sorry, not enough water!");
            } else if (milk < type.getMilkNeeded()) {
                System.out.println("Sorry, not enough milk!");
            } else if (beans < type.getBeansNeeded()) {
                System.out.println("Sorry, not enough coffee beans!");
            } else {
                System.out.println("Sorry, not enough disposable cups!");
            }
        }
    }
}
