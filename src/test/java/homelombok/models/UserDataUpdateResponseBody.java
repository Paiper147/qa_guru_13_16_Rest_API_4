package homelombok.models;

import lombok.Data;

@Data
public class UserDataUpdateResponseBody {
    private String name;
    private String job;
    private String updatedAt;
}
