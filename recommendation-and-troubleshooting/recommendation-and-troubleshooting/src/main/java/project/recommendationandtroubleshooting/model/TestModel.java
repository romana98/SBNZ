package project.recommendationandtroubleshooting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TestModel {

    private String incomeMessage;
    private String responseMessage;

    public String getIncomeMessage() {
        return incomeMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
