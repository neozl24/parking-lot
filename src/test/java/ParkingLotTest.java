import exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
    @Test
    void should_return_parking_lot_full_exception_when_parking_given_a_full_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        String carNumber = "A123456";
        String anotherCarNumber = "B123456";
        Car car1 = new Car(carNumber);
        Car car2 = new Car(carNumber);
        Car car3 = new Car(anotherCarNumber);
        Car car4 = new Car("");
        parkingLot.park(car1);

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car2));
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car3));
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car4));
    }

    @Test
    void should_return_empty_car_number_exception_when_parking_given_an_available_parking_lot_and_car_without_number()  {
        Car car = new Car("");
        ParkingLot parkingLot = new ParkingLot(100);

        assertThrows(EmptyCarNumberException.class, () -> parkingLot.park(car));
    }

    @Test
    void should_return_duplicated_car_number_exception_when_parking_given_an_available_parking_lot_and_car_with_valid_number() {
        String carNumber = "A123456";
        Car car1 = new Car(carNumber);
        Car car2 = new Car(carNumber);
        ParkingLot parkingLot = new ParkingLot(100);
        parkingLot.park(car1);

        assertThrows(DuplicatedCarNumberException.class, () -> parkingLot.park(car2));
    }

    @Test
    void should_return_ticket_when_parking_given_an_available_parking_lot_and_car_with_valid_number() {
        final String carNumber = "A12345";
        Car car = new Car(carNumber);
        ParkingLot parkingLot = new ParkingLot(100);
        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_null_exception_when_picking_given_no_ticket() {
        ParkingLot parkingLot = new ParkingLot(100);
        assertThrows(TicketNullException.class, () -> parkingLot.pick(null));
    }

    @Test
    void should_return_ticket_invalid_exception_when_picking_given_ticket_of_nonexistent_car() {
        ParkingLot parkingLot = new ParkingLot(100);
        Ticket ticket = new Ticket();

        assertThrows(TicketInvalidException.class, () -> parkingLot.pick(ticket));
    }

    @Test
    void should_return_corresponding_car_when_picking_given_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("A123456");
        Ticket ticket = parkingLot.park(car);
        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(pickedCar, car);
    }
}
