import java.util.List;

class GraduateParkingBoy extends ParkingMan {

    @Override
    ParkingLot findTargetParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot firstAvailableParkingLot = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (firstAvailableParkingLot == null && parkingLot.hasSpace()) {
                firstAvailableParkingLot = parkingLot;
            }
        }
        return firstAvailableParkingLot;
    }
}
