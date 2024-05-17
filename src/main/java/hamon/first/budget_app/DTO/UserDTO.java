package hamon.first.budget_app.DTO;

import hamon.first.budget_app.models.Wallet;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;

    private String password;

    private String email;

}
