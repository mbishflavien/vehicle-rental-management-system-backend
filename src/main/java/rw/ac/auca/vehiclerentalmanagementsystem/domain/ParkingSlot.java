package rw.ac.auca.vehiclerentalmanagementsystem.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parking_slot")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "slot_number", unique = true)
    private int slotNumber;

    @Column(name = "name", unique = true)
    private String name;

    public ParkingSlot() {
    }

    @JsonCreator
    public static ParkingSlot fromId(@JsonProperty("id") UUID id) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setId(id);
        return parkingSlot;
    }

    public ParkingSlot(int slotNumber, String name) {
        this.slotNumber = slotNumber;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}