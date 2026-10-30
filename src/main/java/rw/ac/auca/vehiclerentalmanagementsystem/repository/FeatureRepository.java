package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Feature;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {

    Optional<Feature> findByNameIgnoreCase(String name);
    //C3
    @Query("SELECT f FROM Feature f WHERE f.vehicles IS EMPTY")
    List<Feature> findUnusedFeatures();
}
