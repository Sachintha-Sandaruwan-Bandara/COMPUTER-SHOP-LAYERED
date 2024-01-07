package lk.ijse.computerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author Sachi_S_Bandara
    @created 11/19/2023 - 3:44 PM 
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
   private String itemID;
   private String name;
   private int buyingPrice;
    private int sellingPrice;
    private int qty;
    byte[] imageBytes;
}
