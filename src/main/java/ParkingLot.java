import exception.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> lot = new HashMap<>();
    private Set<String> carsNumberSet = new HashSet<>();

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
        carsNumberSet.add(car.getNumber());
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

    private Boolean hasCarNumberExisted(String carNumber) {
        return carsNumberSet.contains(carNumber);
    }

    Boolean hasCar(Car car) {
        return car != null && lot.containsValue(car);
    }

    Boolean canFindCarByTicket(Ticket ticket) {
        return lot.containsKey(ticket);
    }

    Set<String> getCarsNumberSet() {
        return carsNumberSet;
    }
}
