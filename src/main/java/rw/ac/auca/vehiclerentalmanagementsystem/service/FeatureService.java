package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Feature;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.FeatureRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;


    public String saveFeature(Feature feature) {
        if (feature.getName() == null || feature.getName().trim().isEmpty()) {
            return "Feature name cannot be empty";
        }
        Optional<Feature> check = featureRepository.findByNameIgnoreCase(feature.getName());

        if (check.isPresent()) {
            return "That feature already exists";
        }
        featureRepository.save(feature);
        return "Feature saved successfully";
    }

    // READ ALL
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    // READ ONE
    public Optional<Feature> getFeatureById(UUID id) {
        return featureRepository.findById(id);
    }

    // UPDATE
    public String updateFeature(UUID id, Feature newFeature) {
        Optional<Feature> existing = featureRepository.findById(id);

        if (existing.isEmpty()) {
            return "The feature with that id does not exist";
        }

        if (newFeature.getName() == null || newFeature.getName().trim().isEmpty()) {
            return "Feature name cannot be empty";
        }

        Optional<Feature> sameName = featureRepository.findByNameIgnoreCase(newFeature.getName());
        if (sameName.isPresent() && !sameName.get().getId().equals(id)) {
            return "That feature already exists";
        }

        Feature feature = existing.get();
        feature.setName(newFeature.getName());
        featureRepository.save(feature);
        return "Feature updated successfully";
    }

    // DELETE
    public String deleteFeature(UUID id) {
        Optional<Feature> existing = featureRepository.findById(id);

        if (existing.isEmpty()) {
            return "The feature with that id does not exist";
        }

        featureRepository.delete(existing.get());
        return "Feature deleted successfully";
    }

}
