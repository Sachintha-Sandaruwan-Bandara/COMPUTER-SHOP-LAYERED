package lk.ijse.computerShop.bo;
/* 
    @author Sachi_S_Bandara
    @created 1/11/2024 - 9:14 PM 
*/

import lk.ijse.computerShop.bo.impl.CustomerBoImpl;
import lk.ijse.computerShop.bo.impl.ItemBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM
    }

    public SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();

            default:
                return null;
        }
    }
}
