class GraduateParkingBoy extends ParkingBoy {

    @Override
    ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot) {
        if (originalTargetParkingLot == null && comparedParkingLot.hasSpace()) {
            return comparedParkingLot;
        }
        return originalTargetParkingLot;
    }
}
