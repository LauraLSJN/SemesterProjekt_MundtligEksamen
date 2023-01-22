public class Price {

    private int price;
    private int minPrice;
    private int maxPrice;

    public Price(){
        this.maxPrice = 10;
        this.minPrice = -10;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "valuePrice=" + price +
                '}';
    }
}
