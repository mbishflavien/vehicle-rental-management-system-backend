package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.ParkingSlot;
import rw.ac.auca.vehiclerentalmanagementsystem.service.ParkingSlotService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/save")
    public ResponseEntity<?> saveParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        String returnedMessage = parkingSlotService.saveParkingSlot(parkingSlot);
        if (returnedMessage.equals("Parking slot saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParkingSlot>> getAllParkingSlots() {
        return new ResponseEntity<>(parkingSlotService.getAllParkingSlots(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParkingSlotById(@PathVariable UUID id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotService.getParkingSlotById(id);
        if (parkingSlot.isPresent()) {
            return new ResponseEntity<>(parkingSlot.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The parking slot with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateParkingSlot(@PathVariable UUID id, @RequestBody ParkingSlot parkingSlot) {
        String returnedMessage = parkingSlotService.updateParkingSlot(id, parkingSlot);
        if (returnedMessage.equals("Parking slot updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The parking slot with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParkingSlot(@PathVariable UUID id) {
        String returnedMessage = parkingSlotService.deleteParkingSlot(id);
        if (returnedMessage.equals("Parking slot deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The parking slot with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    //C2
    @GetMapping("/free")
    public ResponseEntity<List<ParkingSlot>> getFreeParkingSlots(){
        return new ResponseEntity<>(parkingSlotService.getFreeParkingSlots(), HttpStatus.OK);
    }
}
