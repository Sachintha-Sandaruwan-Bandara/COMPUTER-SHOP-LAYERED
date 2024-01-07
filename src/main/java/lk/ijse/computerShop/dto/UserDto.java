package lk.ijse.computerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 12/1/2023 - 10:47 AM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String userID;
    private String userName;
    private String empID ;

    private String password;
}
