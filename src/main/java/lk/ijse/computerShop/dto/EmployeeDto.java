package lk.ijse.computerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 11/5/2023 - 2:01 PM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private String id;
    private String name;
    private String address;

    private String email;
    private String mobile;

    private String position;

    private byte [] imageBytes;


}
