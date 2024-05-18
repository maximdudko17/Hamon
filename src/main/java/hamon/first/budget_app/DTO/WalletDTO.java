package hamon.first.budget_app.DTO;
import hamon.first.budget_app.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WalletDTO {
    private int money;


}
