package homelombok.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUsersResponseBody {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    @JsonProperty("data")
    private ArrayList<UserResponseBody> user;
//    private Support support;
}
