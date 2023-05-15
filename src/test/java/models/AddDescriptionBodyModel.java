package models;


import lombok.Data;

@Data
public class AddDescriptionBodyModel {
    Integer id;
    String description, descriptionHtml;
}
