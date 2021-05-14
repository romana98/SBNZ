package project.recommendationandtroubleshooting.model.input_model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InputBug {
    String problem;
    Integer indexOfRequest = 0;
}
