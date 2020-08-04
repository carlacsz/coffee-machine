enum coffeeType {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    private final int waterNeeded, milkNeeded, beansNeeded, cost;

    coffeeType(int waterNeeded, int milkNeeded, int beansNeeded, int cost) {
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.beansNeeded = beansNeeded;
        this.cost = cost;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getBeansNeeded() {
        return beansNeeded;
    }

    public int getCost() {
        return cost;
    }
}
