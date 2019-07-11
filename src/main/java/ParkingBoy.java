import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class ParkingBoy {

    abstract ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot);

    Ticket park(Car car, List<ParkingLot> parkingLots) {
        {
            if (parkingLots == null) {
                throw new NoAvailableParkingLotException();
            }

            Set<String> allCarsNumber = new HashSet<>();

            ParkingLot targetParkingLot = null;

            for (ParkingLot parkingLot : parkingLots) {
                allCarsNumber.addAll(parkingLot.getCarsNumberSet());
                targetParkingLot = updateTargetParkingLot(parkingLot, targetParkingLot);
            }

            if (targetParkingLot == null) {
                throw new NoAvailableParkingLotException();
            }

            if (car == null || car.getNumber().equals("")) {
                throw new EmptyCarNumberException();
            }

            if (allCarsNumber.contains(car.getNumber())) {
                throw new DuplicatedCarNumberException();
            }

            return targetParkingLot.park(car);
        }
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
