import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingLotTest {
    @Test
    void should_return_ticket_when_parking_given_an_available_parking_lot_and_car_with_number() throws Exception {
        final String carNumber = "CN123456";
        Car car = new Car(carNumber);
        ParkingLot parkingLot = new ParkingLot(100);
        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
        assertEquals(car.getNumber(), ticket.getNumber());
    }
}
