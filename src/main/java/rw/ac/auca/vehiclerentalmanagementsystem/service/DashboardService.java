package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private RenterRepository renterRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalChargeRepository rentalChargeRepository;

    public Map<String, Long> getSummary(){
        Map<String, Long> map = new LinkedHashMap<>();
        map.put("renters", renterRepository.count());
        map.put("vehicles", vehicleRepository.count());
        // free slots reuse C2 logic roughly: count all - count vehicles with slot? easier count free via repo if you want or compute
        // but easier: get free list size
        long freeSlots = parkingSlotRepository.findFreeParkingSlots().size();
        map.put("freeSlots", freeSlots);
        // rentalsToday
        long rentalsToday = rentalRepository.findAll().stream().filter(r -> r.getPickupDate() != null && r.getPickupDate().equals(LocalDate.now())).count();
        map.put("rentalsToday", rentalsToday);
        map.put("booked", rentalRepository.countByStatus(RentalStatus.BOOKED));
        map.put("charges", rentalChargeRepository.count());
        return map;
    }

}
