package lk.ijse.computerShop.dto;
/* 
    @author Sachi_S_Bandara
    @created 11/21/2023 - 11:08 AM 
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDto {
    String salaryID;
    int amount;
    Date date;
    String empID ;
}
