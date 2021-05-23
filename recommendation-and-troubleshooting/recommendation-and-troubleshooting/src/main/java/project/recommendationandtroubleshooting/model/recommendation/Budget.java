package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Budget {
    private Long minPrice;
    private Long maxPrice;
}
