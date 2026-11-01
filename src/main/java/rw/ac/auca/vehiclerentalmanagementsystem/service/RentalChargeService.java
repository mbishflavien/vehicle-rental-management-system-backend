package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalCharge;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.RentalChargeRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.RentalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalChargeService {

    @Autowired
    private RentalChargeRepository rentalChargeRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public String saveRentalCharge(UUID rentalId, RentalCharge rentalCharge){
        Optional<Rental> rental = rentalRepository.findById(rentalId);
        if(rental.isEmpty()){
            return "The rental with that id does not exist";
        }
        if(rental.get().getStatus() != RentalStatus.RETURNED){
            return "Rental charges are only for RETURNED rentals";
        }
        rentalCharge.setRental(rental.get());
        rentalCharge.setChargedDate(LocalDate.now());
        rentalChargeRepository.save(rentalCharge);
        return "Rental charge saved successfully";
    }

    public List<RentalCharge> getChargesByRenter(String licenseNumber){
        return rentalChargeRepository.findByRenterLicenseNumber(licenseNumber);
    }

    public String deleteRentalCharge(UUID id){
        Optional<RentalCharge> charge = rentalChargeRepository.findById(id);
        if(charge.isEmpty()){
            return "The rental charge with that id does not exist";
        }
        rentalChargeRepository.delete(charge.get());
        return "Rental charge deleted successfully";
    }

    public boolean hasCharges(UUID rentalId){
        return rentalChargeRepository.existsByRentalId(rentalId);
    }



}
