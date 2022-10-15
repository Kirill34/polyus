package model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Controller
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private float power;

    private float loadCapacity;

    private float loadLength;

    private float loadWidth;

    private float maxSpeed;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private VehicleType type;

    public VehicleModel(String name, float power, float loadCapacity, float loadLength, float loadWidth, float maxSpeed, VehicleType type, float fuelСonsumption100km, float fuelConsumptionHour) {
        this.name = name;
        this.power = power;
        this.loadCapacity = loadCapacity;
        this.loadLength = loadLength;
        this.loadWidth = loadWidth;
        this.maxSpeed = maxSpeed;
        this.type = type;
        this.fuelСonsumption100km = fuelСonsumption100km;
        this.fuelConsumptionHour = fuelConsumptionHour;
    }

    public VehicleModel() {

    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getFuelСonsumption100km() {
        return fuelСonsumption100km;
    }

    public float getFuelConsumptionHour() {
        return fuelConsumptionHour;
    }

    private float fuelСonsumption100km;

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setFuelСonsumption100km(float fuelСonsumption100km) {
        this.fuelСonsumption100km = fuelСonsumption100km;
    }

    public void setFuelConsumptionHour(float fuelConsumptionHour) {
        this.fuelConsumptionHour = fuelConsumptionHour;
    }

    private float fuelConsumptionHour;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setLoadCapacity(float loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void setLoadLength(float loadLength) {
        this.loadLength = loadLength;
    }

    public void setLoadWidth(float loadWidth) {
        this.loadWidth = loadWidth;
    }


    public float getLoadCapacity() {
        return loadCapacity;
    }

    public float getLoadLength() {
        return loadLength;
    }

    public float getLoadWidth() {
        return loadWidth;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }
}
