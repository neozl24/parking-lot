import java.util.HashMap;
import java.util.Map;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> lots = new HashMap<>();

    ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    Ticket park(Car car) throws Exception {
        String carNumber = car.getNumber();
        int currentCarsAmount = lots.size();
        if (currentCarsAmount >= capacity) {
            throw new Exception();
        }
        return new Ticket(carNumber);
    }
}
