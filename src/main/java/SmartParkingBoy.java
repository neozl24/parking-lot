import exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SmartParkingBoy extends ParkingMan {

    @Override
    ParkingLot findTargetParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithMostRemainingSpaces = null;

        int maxRemainingSpacesNumber = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getRemainingSpaces() > maxRemainingSpacesNumber) {
                parkingLotWithMostRemainingSpaces = parkingLot;
                maxRemainingSpacesNumber = parkingLot.getRemainingSpaces();
            }
        }
        return parkingLotWithMostRemainingSpaces;
    }
}
