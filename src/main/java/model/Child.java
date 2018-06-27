package model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Child {
    private String dateOfBirth;
    private String customerId;
    private String salutationName;
    private String gender;
}
