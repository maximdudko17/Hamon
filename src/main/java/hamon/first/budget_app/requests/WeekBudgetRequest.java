package hamon.first.budget_app.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeekBudgetRequest {

    private int food;

    private int transport;

    private int home;

    private int medicine;

    private int entertainment;

}
