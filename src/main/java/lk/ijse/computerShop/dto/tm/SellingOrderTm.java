package lk.ijse.computerShop.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 11/21/2023 - 8:19 PM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellingOrderTm {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double tot;
}
