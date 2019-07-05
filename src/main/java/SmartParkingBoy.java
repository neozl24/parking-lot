class SmartParkingBoy extends ParkingMan {

    @Override
    ParkingLot updateTargetParkingLot(ParkingLot comparedParkingLot, ParkingLot originalTargetParkingLot) {
        if (originalTargetParkingLot == null && comparedParkingLot.getRemainingSpaces() > 0) {
            return comparedParkingLot;
        } else if (originalTargetParkingLot == null) {
            return null;
        } else if (comparedParkingLot.getRemainingSpaces() > originalTargetParkingLot.getRemainingSpaces()) {
            return comparedParkingLot;
        }
        return originalTargetParkingLot;
    }
}
