class SuperParkingBoy extends ParkingBoy {

    @Override
    ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot) {
        if (originalTargetParkingLot == null && comparedParkingLot.getVacancyRate() > 0) {
            return comparedParkingLot;
        } else if (originalTargetParkingLot == null) {
            return null;
        } else if (comparedParkingLot.getVacancyRate() > originalTargetParkingLot.getVacancyRate()) {
            return comparedParkingLot;
        }
        return originalTargetParkingLot;
    }
}
