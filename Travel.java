public class Travel {

    private final String name;
    private final String destination;
    private final int price;

    public Travel(String name, String destination, int price) {
        this.name = name;
        this.destination = destination;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }
}