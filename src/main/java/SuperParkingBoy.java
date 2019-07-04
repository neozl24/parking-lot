import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SuperParkingBoy extends ParkingMan {

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
}
