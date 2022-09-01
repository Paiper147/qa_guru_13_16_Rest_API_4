package homelombok.models;

import lombok.Data;

@Data
public class UserDataUpdateRequestBody {
    private String name;
    private String job;
}
