package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import project.recommendationandtroubleshooting.enums.LimitType;

public class Limit {

    public Long limit;
    public LimitType type;

    public Limit() {

    }

    public Limit(Long limit, LimitType type) {
        this.limit = limit;
        this.type = type;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public LimitType getType() {
        return type;
    }

    public void setType(LimitType type) {
        this.type = type;
    }
}