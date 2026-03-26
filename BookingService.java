import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookingService {

    private final Map<String,Integer> priceMap;

    public BookingService(){

        priceMap = new HashMap<>();

        priceMap.put("Goa",5000);
        priceMap.put("Delhi",4000);
        priceMap.put("Dubai",20000);
    }

    public int getPrice(String destination){

        return priceMap.getOrDefault(destination,0);

    }

    // SAVE BOOKING TO FILE (Backend Storage)
    public void saveBooking(String name,String phone,String destination,
                            String date,int days,int members,int totalPrice){

        try{

            FileWriter writer = new FileWriter("BookingHistory.txt",true);

            writer.write(
                    "Passenger: " + name +
                    " | Phone: " + phone +
                    " | Destination: " + destination +
                    " | Date: " + date +
                    " | Days: " + days +
                    " | Members: " + members +
                    " | Total Price: ₹" + totalPrice +
                    "\n"
            );

            writer.close();

        }
        catch(IOException e){

            System.out.println("Error saving booking");

        }
    }
}