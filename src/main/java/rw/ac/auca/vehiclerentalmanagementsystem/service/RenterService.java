package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Renter;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.RenterRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RenterService {

    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    // CREATE
    public String saveRenter(Renter renter) {
        if (renter.getFirstName() == null || renter.getFirstName().trim().isEmpty()) {
            return "Renter first name cannot be empty";
        }
        if (renter.getLastName() == null || renter.getLastName().trim().isEmpty()) {
            return "Renter last name cannot be empty";
        }
        if (renter.getLicenseNumber() == null || renter.getLicenseNumber().trim().isEmpty()) {
            return "Renter license number cannot be empty";
        }
        if (renter.getPhone() == null || renter.getPhone().trim().isEmpty()) {
            return "Renter phone cannot be empty";
        }
        if (renter.getEmail() == null || renter.getEmail().trim().isEmpty()) {
            return "Renter email cannot be empty";
        }

        Optional<Renter> checkLicense = renterRepository.findByLicenseNumberIgnoreCase(renter.getLicenseNumber());
        if (checkLicense.isPresent()) {
            return "That renter license number already exists";
        }
        renterRepository.save(renter);
        return "Renter saved successfully";
    }

    // READ ALL
    public List<Renter> getAllRenters() {
        return renterRepository.findAll();
    }

    // READ ONE
    public Optional<Renter> getRenterById(UUID id) {
        return renterRepository.findById(id);
    }

    // UPDATE
    public String updateRenter(UUID id, Renter newRenter) {
        Optional<Renter> existing = renterRepository.findById(id);

        if (existing.isEmpty()) {
            return "The renter with that id does not exist";
        }
        if (newRenter.getFirstName() == null || newRenter.getFirstName().trim().isEmpty()) {
            return "Renter first name cannot be empty";
        }
        if (newRenter.getLastName() == null || newRenter.getLastName().trim().isEmpty()) {
            return "Renter last name cannot be empty";
        }
        if (newRenter.getLicenseNumber() == null || newRenter.getLicenseNumber().trim().isEmpty()) {
            return "Renter license number cannot be empty ";
        }
        if (newRenter.getPhone() == null || newRenter.getPhone().trim().isEmpty()) {
            return "Renter phone cannot be empty ";
        }
        if (newRenter.getEmail() == null || newRenter.getEmail().trim().isEmpty()) {
            return "Renter email cannot be empty";
        }

        Optional<Renter> sameLicense = renterRepository.findByLicenseNumberIgnoreCase(newRenter.getLicenseNumber());
        if (sameLicense.isPresent() && !sameLicense.get().getId().equals(id)) {
            return "That renter license number already exists";
        }

        Renter renter = existing.get();
        renter.setFirstName(newRenter.getFirstName());
        renter.setLastName(newRenter.getLastName());
        renter.setLicenseNumber(newRenter.getLicenseNumber());
        renter.setPhone(newRenter.getPhone());
        renter.setEmail(newRenter.getEmail());
        renterRepository.save(renter);
        return "Renter updated successfully";
    }

    // DELETE
    public String deleteRenter(UUID id) {
        Optional<Renter> existing = renterRepository.findById(id);

        if (existing.isEmpty()) {
            return "The renter with that id does not exist";
        }

        renterRepository.delete(existing.get());
        return "Renter deleted successfully";
    }

    //B1
    public List<Renter> getRentersByLastName(String lastName){
        return renterRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    //C4
    public Object getRentersOfVehicle(java.util.UUID vehicleId){
        if(!vehicleRepository.existsById(vehicleId)){
            return "The vehicle with that id does not exist";
        }
        return renterRepository.findRentersByVehicleId(vehicleId);
    }
}
