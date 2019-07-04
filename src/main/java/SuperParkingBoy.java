import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SuperParkingBoy {

    Ticket park(Car car, List<ParkingLot> parkingLots) {
        if (parkingLots == null) {
            throw new NoAvailableParkingLotException();
        }

        Set<String> allCarsNumber = new HashSet<>();
        ParkingLot parkingLotWithHighestVacancyRate = null;

        double highestVacancyRate = 0;
        for (ParkingLot parkingLot : parkingLots) {
            allCarsNumber.addAll(parkingLot.getCarsNumberSet());
            if (parkingLot.getVacancyRate() > highestVacancyRate) {
                parkingLotWithHighestVacancyRate = parkingLot;
                highestVacancyRate = parkingLot.getVacancyRate();
            }
        }
        if (parkingLotWithHighestVacancyRate == null) {
            throw new NoAvailableParkingLotException();
        }

        if (car == null || car.getNumber().equals("")) {
            throw new EmptyCarNumberException();
        }

        if (allCarsNumber.contains(car.getNumber())) {
            throw new DuplicatedCarNumberException();
        }

        return parkingLotWithHighestVacancyRate.park(car);
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
