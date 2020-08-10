public enum IngredientType {
    WATER("water", "ml"),
    MILK("milk","ml"),
    COFFEE_BEANS("coffee beans", "grams"),
    CHOCOLATE("chocolate powder", "grams");

    private final String name, measure;

    IngredientType(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }
}
