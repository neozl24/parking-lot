import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SmartParkingBoy {

    Ticket park(Car car, List<ParkingLot> parkingLots) {
        if (parkingLots == null) {
            throw new NoAvailableParkingLotException();
        }

        Set<String> allCarsNumber = new HashSet<>();
        ParkingLot parkingLotWithMostRemainingSpaces = null;

        int maxRemainingSpacesNumber = 0;
        for (ParkingLot parkingLot : parkingLots) {
            allCarsNumber.addAll(parkingLot.getCarsNumberSet());
            if (parkingLot.getRemainingSpaces() > maxRemainingSpacesNumber) {
                parkingLotWithMostRemainingSpaces = parkingLot;
                maxRemainingSpacesNumber = parkingLot.getRemainingSpaces();
            }
        }
        if (parkingLotWithMostRemainingSpaces == null) {
            throw new NoAvailableParkingLotException();
        }

        if (car == null || car.getNumber().equals("")) {
            throw new EmptyCarNumberException();
        }

        if (allCarsNumber.contains(car.getNumber())) {
            throw new DuplicatedCarNumberException();
        }

        return parkingLotWithMostRemainingSpaces.park(car);
    }

    Car pick(Ticket ticket, List<ParkingLot> parkingLots) {
        if (ticket == null) {
            throw new TicketNullException();
        }

        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.canFindCarByTicket(ticket)) {
                return parkingLot.pick(ticket);
            }
        }
        throw new TicketInvalidException();
    }
}
