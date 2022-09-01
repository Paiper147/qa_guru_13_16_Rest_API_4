package homelombok.models;

import lombok.Data;

@Data
public class CreateRequestBody {
    private String name;
    private String job;
}
