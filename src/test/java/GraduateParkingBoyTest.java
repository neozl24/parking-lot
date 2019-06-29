import exception.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {
    @Test
    void should_throw_no_available_parking_lot_exception_when_parking_given_all_parking_lots_at_full() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        Car car1 = new Car("A123456");
        Car car2 = new Car("");

        assertThrows(NoAvailableParkingLotException.class, () -> graduateParkingBoy.park(car1, null));
        assertThrows(NoAvailableParkingLotException.class, () -> graduateParkingBoy.park(car1, parkingLots));
        assertThrows(NoAvailableParkingLotException.class, () -> graduateParkingBoy.park(car2, parkingLots));
    }

    @Test
    void should_throw_empty_car_number_exception_when_parking_given_car_without_number_and_available_parking_lots() {
        ParkingLot parkingLot = new ParkingLot(1);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        assertThrows(EmptyCarNumberException.class, () -> graduateParkingBoy.park(null, parkingLots));
        assertThrows(EmptyCarNumberException.class, () -> graduateParkingBoy.park(new Car(""), parkingLots));
    }

    @Test
    void should_throw_duplicated_car_number_exception_when_parking_given_car_with_valid_number_and_available_parking_lots() {
        final String carNumber = "A123456";
        Car car = new Car(carNumber);
        Car carWithDuplicatedNumber = new Car(carNumber);

        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        graduateParkingBoy.park(car, parkingLots);

        assertThrows(DuplicatedCarNumberException.class, () -> graduateParkingBoy.park(carWithDuplicatedNumber, parkingLots));
    }

    @Test
    void should_return_ticket_and_park_into_the_first_available_parking_lot_when_parking_given_a_car_with_valid_number_and_more_than_one_available_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        final String carNumber = "A123456";
        Car car = new Car(carNumber);

        Ticket ticket = graduateParkingBoy.park(car, parkingLots);
        assertNotNull(ticket);

        assertTrue(parkingLot1.hasCar(car));
        assertFalse(parkingLot2.hasCar(car));
    }

    @Test
    void should_throw_ticket_null_exception_when_picking_given_no_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        assertThrows(TicketNullException.class, () -> graduateParkingBoy.pick(null, parkingLots));
    }

    @Test
    void should_throw_ticket_invalid_exception_when_picking_given_ticket_of_nonexistent_car() {
        ParkingLot parkingLot = new ParkingLot(1);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        Ticket ticketOfNonExistentCar = new Ticket();

        assertThrows(TicketInvalidException.class, () -> graduateParkingBoy.pick(ticketOfNonExistentCar, parkingLots));
    }

    @Test
    void should_return_corresponding_car_when_picking_given_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        Car car = new Car("A123456");

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        Ticket ticket = graduateParkingBoy.park(car, parkingLots);
        Car returnedCar = graduateParkingBoy.pick(ticket, parkingLots);

        assertEquals(car, returnedCar);
    }
}
