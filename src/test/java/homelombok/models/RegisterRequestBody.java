package homelombok.models;

import lombok.Data;

@Data
public class RegisterRequestBody {
    private String email;
    private String password;
}
