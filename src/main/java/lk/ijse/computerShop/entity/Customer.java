package lk.ijse.computerShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 11/3/2023 - 8:38 PM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String id;
    private String name;
    private String address;

    private String email;
    private String mobile;
    private byte [] imageBytes;

}
