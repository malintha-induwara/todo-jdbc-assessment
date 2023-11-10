package lk.ijse.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TasksDto {
    private int taskId;

    private String email;

    private String description;

    private String dueDate;

    private int isCompleted;

}

