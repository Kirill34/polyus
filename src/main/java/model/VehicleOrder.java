package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class VehicleOrder {

    public VehicleOrder(User customer, LocalDateTime startOfWork, LocalDateTime finishOfWork, VehicleModel requiredModel)
    {
        this.customer=customer;
        this.startOfWork=startOfWork;
        this.finishOfWork=finishOfWork;
        this.requiredModel=requiredModel;
        this.status=Status.ACCEPTED;
    }

    public VehicleOrder(User customer, LocalDateTime startOfWork, LocalDateTime finishOfWork, VehicleType vehicleType, double minWidth, double minLength, double weight) {
        this.customer = customer;
        this.startOfWork = startOfWork;
        this.finishOfWork = finishOfWork;
        this.vehicleType = vehicleType;
        this.minWidth = minWidth;
        this.minLength = minLength;
        this.weight = weight;
        this.status = Status.WAITING;
    }

    public VehicleOrder() {

    }

    public enum Status {
        WAITING,
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    public LocalDateTime getStartOfWork() {
        return startOfWork;
    }

    public LocalDateTime getFinishOfWork() {
        return finishOfWork;
    }

    public void setStartOfWork(LocalDateTime startOfWork) {
        this.startOfWork = startOfWork;
    }

    public void setFinishOfWork(LocalDateTime finishOfWork) {
        this.finishOfWork = finishOfWork;
    }

    public void setMinWidth(double minWidth) {
        this.minWidth = minWidth;
    }

    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMinWidth() {
        return minWidth;
    }

    public double getMinLength() {
        return minLength;
    }

    public double getWeight() {
        return weight;
    }

    private LocalDateTime startOfWork;

    private LocalDateTime finishOfWork;

    @ManyToOne
    @JoinColumn(name = "required_model_id")
    private VehicleModel requiredModel;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    private double minWidth;

    private double minLength;

    private double weight;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public VehicleModel getRequiredModel() {
        return requiredModel;
    }

    public void setRequiredModel(VehicleModel requiredModel) {
        this.requiredModel = requiredModel;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
