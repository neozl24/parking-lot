class GraduateParkingBoy extends ParkingMan {

    @Override
    ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot) {
        if (originalTargetParkingLot == null && comparedParkingLot.hasSpace()) {
            return comparedParkingLot;
        }
        return originalTargetParkingLot;
    }
}
