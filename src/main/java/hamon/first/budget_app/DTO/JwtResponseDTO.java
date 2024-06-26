package hamon.first.budget_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {

    private final String tokenType = "Bearer";

    private String accessToken;

    private String message;


    public JwtResponseDTO(String accessToken){
        this.accessToken = accessToken;
    }
}
