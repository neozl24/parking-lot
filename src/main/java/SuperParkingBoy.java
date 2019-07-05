import java.util.List;

class SuperParkingBoy extends ParkingMan {

    @Override
    ParkingLot findTargetParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithHighestVacancyRate = null;

        double highestVacancyRate = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getVacancyRate() > highestVacancyRate) {
                parkingLotWithHighestVacancyRate = parkingLot;
                highestVacancyRate = parkingLot.getVacancyRate();
            }
        }

        return parkingLotWithHighestVacancyRate;
    }
}
