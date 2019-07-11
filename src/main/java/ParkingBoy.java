import exception.*;

import java.util.List;

abstract class ParkingBoy {

    abstract ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot);

    Ticket park(Car car, List<ParkingLot> parkingLots) {
        {
            if (parkingLots == null) {
                throw new NoAvailableParkingLotException();
            }

            boolean hasCarNumberExisted = false;
            boolean isCarNumberEmpty = car == null || car.getNumber().equals("");

            ParkingLot targetParkingLot = null;

            for (ParkingLot parkingLot : parkingLots) {
                if (!isCarNumberEmpty && parkingLot.hasCarNumberExisted(car.getNumber())) {
                    hasCarNumberExisted = true;
                }
                targetParkingLot = updateTargetParkingLot(parkingLot, targetParkingLot);
            }

            if (targetParkingLot == null) {
                throw new NoAvailableParkingLotException();
            }

            if (isCarNumberEmpty) {
                throw new EmptyCarNumberException();
            }

            if (hasCarNumberExisted) {
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
