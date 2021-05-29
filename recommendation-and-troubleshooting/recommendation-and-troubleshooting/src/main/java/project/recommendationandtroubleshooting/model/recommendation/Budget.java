package project.recommendationandtroubleshooting.model.recommendation;

public class Budget {
    private Long minPrice;
    private Long maxPrice;

    public Budget() {

    }

    public Budget(Long minPrice, Long maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }
}
