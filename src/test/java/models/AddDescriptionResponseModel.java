package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDescriptionResponseModel {
    Integer id, projectId;
    String name, description, descriptionHtml;
    Boolean deleted, automated, external;
    Long createdDate, lastModifiedDate;
}
