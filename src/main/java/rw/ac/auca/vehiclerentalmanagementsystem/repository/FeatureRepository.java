package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Feature;

import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {

    Optional<Feature> findByNameIgnoreCase(String name);
}
