package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Vehicle;
import rw.ac.auca.vehiclerentalmanagementsystem.service.VehicleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) {
        String returnedMessage = vehicleService.saveVehicle(vehicle);
        if (returnedMessage.equals("Vehicle saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable UUID id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The vehicle with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        String returnedMessage = vehicleService.updateVehicle(id, vehicle);
        if (returnedMessage.equals("Vehicle updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The vehicle with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable UUID id) {
        String returnedMessage = vehicleService.deleteVehicle(id);
        if (returnedMessage.equals("Vehicle deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The vehicle with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    //C1
    @GetMapping("/by-feature")
    public ResponseEntity<List<Vehicle>> getVehiclesByFeature(@RequestParam String name){
        return new ResponseEntity<>(vehicleService.getVehiclesByFeature(name), HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}/features/{featureId}")
    public ResponseEntity<?> addFeatureToVehicle(@PathVariable java.util.UUID vehicleId, @PathVariable java.util.UUID featureId){
        String returnedMessage = vehicleService.addFeatureToVehicle(vehicleId, featureId);
        if(returnedMessage.equals("Feature added to the vehicle")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The vehicle with that id does not exist") || returnedMessage.equals("That feature does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else if(returnedMessage.equals("The vehicle already has that feature")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/{vehicleId}/parking-slot")
    public ResponseEntity<?> changeParkingSlot(@PathVariable java.util.UUID vehicleId, @RequestParam int slotNumber){
        String returnedMessage = vehicleService.changeParkingSlot(vehicleId, slotNumber);
        if(returnedMessage.equals("Parking slot changed successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The vehicle with that id does not exist") || returnedMessage.equals("The parking slot with that slotNumber does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else if(returnedMessage.equals("That parking slot is taken by another vehicle")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }
}