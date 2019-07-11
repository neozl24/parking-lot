import exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> lot = new HashMap<>();

    ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    Ticket park(Car car) throws RuntimeException {
        if (!hasSpace()) {
            throw new ParkingLotFullException();
        }

        if (car == null || car.getNumber().equals("")) {
            throw new EmptyCarNumberException();
        }

        if (hasCarNumberExisted(car.getNumber())) {
            throw new DuplicatedCarNumberException();
        }

        Ticket ticket = new Ticket();
        lot.put(ticket, car);
        return ticket;
    }

    Car pick(Ticket ticket) throws RuntimeException {
        if (ticket == null) {
            throw new TicketNullException();
        }
        Car car = lot.get(ticket);
        if (car == null) {
            throw new TicketInvalidException();
        }
        return car;
    }

    Boolean hasSpace() {
        return lot.size() < capacity;
    }

    int getRemainingSpaces() {
        int parkedCarsNumber = lot.size();
        return parkedCarsNumber < capacity ? capacity - parkedCarsNumber : 0;
    }

    double getVacancyRate() {
        int parkedCarsNumber = lot.size();
        return parkedCarsNumber < capacity ? 1 - parkedCarsNumber * 1.0 / capacity : 0;
    }

    Boolean hasCarNumberExisted(String carNumber) {
        return lot.values()
                .stream()
                .map(Car::getNumber)
                .collect(Collectors.toSet())
                .contains(carNumber);
    }

    Boolean hasCar(Car car) {
        return car != null && lot.containsValue(car);
    }

    Boolean canFindCarByTicket(Ticket ticket) {
        return lot.containsKey(ticket);
    }

}
