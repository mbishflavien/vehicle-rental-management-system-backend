package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Renter;
import rw.ac.auca.vehiclerentalmanagementsystem.service.RenterService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/renters")
public class RenterController {

    @Autowired
    private RenterService renterService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRenter(@RequestBody Renter renter) {
        String returnedMessage = renterService.saveRenter(renter);
        if (returnedMessage.equals("Renter saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Renter>> getAllRenters() {
        return new ResponseEntity<>(renterService.getAllRenters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRenterById(@PathVariable UUID id) {
        Optional<Renter> renter = renterService.getRenterById(id);
        if (renter.isPresent()) {
            return new ResponseEntity<>(renter.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The renter with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRenter(@PathVariable UUID id, @RequestBody Renter renter) {
        String returnedMessage = renterService.updateRenter(id, renter);
        if (returnedMessage.equals("Renter updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The renter with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRenter(@PathVariable UUID id) {
        String returnedMessage = renterService.deleteRenter(id);
        if (returnedMessage.equals("Renter deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The renter with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Renter>> getRentersByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(renterService.getRentersByLastName(lastName), HttpStatus.OK);
    }
}
