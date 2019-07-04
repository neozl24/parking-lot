import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GraduateParkingBoy extends ParkingMan {

    Ticket park(Car car, List<ParkingLot> parkingLots) {
        if (parkingLots == null) {
            throw new NoAvailableParkingLotException();
        }

        Set<String> allCarsNumber = new HashSet<>();
        ParkingLot firstAvailableParkingLot = null;

        for (ParkingLot parkingLot : parkingLots) {
            allCarsNumber.addAll(parkingLot.getCarsNumberSet());
            if (firstAvailableParkingLot == null && parkingLot.hasSpace()) {
                firstAvailableParkingLot = parkingLot;
            }
        }
        if (firstAvailableParkingLot == null) {
            throw new NoAvailableParkingLotException();
        }

        if (car == null || car.getNumber().equals("")) {
            throw new EmptyCarNumberException();
        }

        if (allCarsNumber.contains(car.getNumber())) {
            throw new DuplicatedCarNumberException();
        }

        return firstAvailableParkingLot.park(car);
    }
}
