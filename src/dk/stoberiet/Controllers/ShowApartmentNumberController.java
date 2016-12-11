package dk.stoberiet.Controllers;

/**
 * Created by Olivi on 08-12-2016.
 */
public class ShowApartmentNumberController {

    public static String apartmentNumber;

    public static void setApartmentNumber(String apartn){
        ShowApartmentNumberController.apartmentNumber = apartn;
    }

    public static String getApartmentNumber(){
        return apartmentNumber;
    }

}
