import java.util.List;

public class CoffeeType {
    protected String name;
    protected List<Ingredient> ingredients;
    protected double price;

    public CoffeeType(String name, List<Ingredient> ingredients, double price) {
        this.ingredients = ingredients;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    public double getPrice() {
        return price;
    }
}
