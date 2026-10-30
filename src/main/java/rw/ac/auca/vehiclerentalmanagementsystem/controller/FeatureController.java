package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Feature;
import rw.ac.auca.vehiclerentalmanagementsystem.service.FeatureService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFeature(@RequestBody Feature feature) {
        String returnedMessage = featureService.saveFeature(feature);
        if (returnedMessage.equals("Feature saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feature>> getAllFeatures() {
        return new ResponseEntity<>(featureService.getAllFeatures(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeatureById(@PathVariable UUID id) {
        Optional<Feature> feature = featureService.getFeatureById(id);
        if (feature.isPresent()) {
            return new ResponseEntity<>(feature.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The feature with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFeature(@PathVariable UUID id, @RequestBody Feature feature) {
        String returnedMessage = featureService.updateFeature(id, feature);
        if (returnedMessage.equals("Feature updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The feature with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFeature(@PathVariable UUID id) {
        String returnedMessage = featureService.deleteFeature(id);
        if (returnedMessage.equals("Feature deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The feature with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    //C3
    @GetMapping("/unused")
    public ResponseEntity<List<Feature>> getUnusedFeatures(){
        return new ResponseEntity<>(featureService.getUnusedFeatures(), HttpStatus.OK);
    }
}
