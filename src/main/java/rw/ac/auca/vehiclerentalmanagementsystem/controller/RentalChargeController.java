package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalCharge;
import rw.ac.auca.vehiclerentalmanagementsystem.service.RentalChargeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rental-charges")
public class RentalChargeController {

    @Autowired
    private RentalChargeService rentalChargeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRentalCharge(@RequestParam UUID rentalId, @RequestBody RentalCharge rentalCharge){
        String returnedMessage = rentalChargeService.saveRentalCharge(rentalId, rentalCharge);
        if(returnedMessage.equals("Rental charge saved successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equals("The rental with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else if(returnedMessage.equals("Rental charges are only for RETURNED rentals")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/renter/{licenseNumber}")
    public ResponseEntity<List<RentalCharge>> getChargesByRenter(@PathVariable String licenseNumber){
        return new ResponseEntity<>(rentalChargeService.getChargesByRenter(licenseNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRentalCharge(@PathVariable UUID id){
        String returnedMessage = rentalChargeService.deleteRentalCharge(id);
        if(returnedMessage.equals("Rental charge deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The rental charge with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

}
