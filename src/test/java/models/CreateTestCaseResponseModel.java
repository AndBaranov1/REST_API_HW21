package models;

import lombok.Data;

@Data
public class CreateTestCaseResponseModel {
    Integer id;
    String name, statusName, statusColor;
    Boolean automated, external;
    Long createdDate;
}
