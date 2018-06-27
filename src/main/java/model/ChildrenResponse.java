package model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChildrenResponse {
    private List<Child> children;
    private int count;
}
