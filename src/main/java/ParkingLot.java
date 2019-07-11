import exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> ticketToCar = new HashMap<>();

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
        ticketToCar.put(ticket, car);
        return ticket;
    }

    Car pick(Ticket ticket) throws RuntimeException {
        if (ticket == null) {
            throw new TicketNullException();
        }
        Car car = ticketToCar.get(ticket);
        if (car == null) {
            throw new TicketInvalidException();
        }
        return car;
    }

    Boolean hasSpace() {
        return ticketToCar.size() < capacity;
    }

    int getRemainingSpaces() {
        int parkedCarsNumber = ticketToCar.size();
        return parkedCarsNumber < capacity ? capacity - parkedCarsNumber : 0;
    }

    double getVacancyRate() {
        int parkedCarsNumber = ticketToCar.size();
        return parkedCarsNumber < capacity ? 1 - parkedCarsNumber * 1.0 / capacity : 0;
    }

    Boolean hasCarNumberExisted(String carNumber) {
        return ticketToCar.values()
                .stream()
                .map(Car::getNumber)
                .collect(Collectors.toSet())
                .contains(carNumber);
    }

    Boolean hasCar(Car car) {
        return car != null && ticketToCar.containsValue(car);
    }

    Boolean canFindCarByTicket(Ticket ticket) {
        return ticketToCar.containsKey(ticket);
    }

}
