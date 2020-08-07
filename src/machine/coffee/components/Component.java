package coffee.components;

public class Component {
    protected String name;
    protected String measure;
    protected int quantity;

    public String getName() {
        return name;
    }

    public Component(int quantity, String name, String measure){
        this.quantity = quantity;
        this.name = name;
        this.measure = measure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity + " " + measure + " of " + name;
    }

    public void addAmount(int amount) {
        this.quantity += amount;
    }

    public boolean isSameType(Component other) {
        return other.getName().equals(name);
    }

    public String getMeasure() {
        return measure;
    }
}