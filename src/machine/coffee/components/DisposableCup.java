package coffee.components;


public class DisposableCup extends Component {
    public DisposableCup(int amount){
        super(amount, "disposable cups", "cups");
    }

    @Override
    public String toString() {
        return quantity + " of " + name;
    }
}
