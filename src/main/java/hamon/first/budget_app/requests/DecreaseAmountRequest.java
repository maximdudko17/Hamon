package hamon.first.budget_app.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecreaseAmountRequest {
    private int id;

    private int amount;
}
