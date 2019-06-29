import exception.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> lots = new HashMap<>();
    private Set<String> carsNumberSet = new HashSet<>();

    ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    Ticket park(Car car) throws RuntimeException {
        String carNumber = car.getNumber();
        if (!hasSpace()) {
            throw new ParkingLotFullException();
        }
        if (carNumber.equals("")) {
            throw new EmptyCarNumberException();
        }
        if (hasCarNumberExisted(carNumber)) {
            throw new DuplicatedCarNumberException();
        }
        Ticket ticket = new Ticket();
        lots.put(ticket, car);
        carsNumberSet.add(carNumber);
        return ticket;
    }

    Car pick(Ticket ticket) throws RuntimeException {
        if (ticket == null) {
            throw new TicketNullException();
        }
        Car car = lots.get(ticket);
        if (car == null) {
            throw new TicketInvalidException();
        }
        return car;
    }

    Boolean hasSpace() {
        return lots.size() < capacity;
    }

    private Boolean hasCarNumberExisted(String carNumber) {
        return carsNumberSet.contains(carNumber);
    }

    Boolean hasCar(Car car) {
        return car != null && lots.containsValue(car);
    }

    Boolean canFindCarByTicket(Ticket ticket) {
        return lots.containsKey(ticket);
    }

    Set<String> getCarsNumberSet() {
        return carsNumberSet;
    }
}
