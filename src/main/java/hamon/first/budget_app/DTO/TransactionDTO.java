package hamon.first.budget_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private int id_category;

    private int amount;
}
