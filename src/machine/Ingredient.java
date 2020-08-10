public class Ingredient {
    private IngredientType type;
    private int quantity;

    public Ingredient(IngredientType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getName() {
        return type.getName();
    }

    public String getMeasure() {
        return type.getMeasure();
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return quantity + " " + type.getMeasure() + " of " + type.getName();
    }
}