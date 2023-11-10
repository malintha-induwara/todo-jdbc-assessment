package lk.ijse.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDto {
    private String email;
    private  String name;
    private String password;
}

