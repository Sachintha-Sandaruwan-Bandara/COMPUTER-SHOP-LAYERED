package lk.ijse.computerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 11/18/2023 - 7:05 PM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDto {
    private String id;
    private String name;
    private String address;

    private String email;
    private String mobile;
}
