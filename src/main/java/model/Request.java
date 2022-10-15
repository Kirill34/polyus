package model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_exemplar_id")
    private VehicleExemplar vehicleExemplar;

    public Request() {
        this.status = Status.WAITING;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;


    @ManyToOne
    @JoinColumn(name = "order_id")
    @Nullable
    private VehicleOrder vehicleOrder = null;

    public Request(User driver, User customer, VehicleExemplar vehicleExemplar, LocalDateTime startDateTime, LocalDateTime finishDateTime, VehicleOrder vehicleOrder) {
        this.driver = driver;
        this.customer = customer;
        this.vehicleExemplar = vehicleExemplar;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.status = Status.ACCEPTED;
        this.vehicleOrder = vehicleOrder;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setFinishDateTime(LocalDateTime finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    private LocalDateTime startDateTime;

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getFinishDateTime() {
        return finishDateTime;
    }

    public void setRealFinishDateTime(LocalDateTime realFinishDateTime) {
        this.realFinishDateTime = realFinishDateTime;
    }

    private LocalDateTime finishDateTime;


    public LocalDateTime getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(LocalDateTime realStartDate) {
        this.realStartDate = realStartDate;
    }

    private LocalDateTime realStartDate;

    public LocalDateTime getRealFinishDateTime() {
        return realFinishDateTime;
    }

    private LocalDateTime realFinishDateTime;

    public VehicleOrder getOrder() {
        return vehicleOrder;
    }

    public void setOrder(VehicleOrder vehicleOrder) {
        this.vehicleOrder = vehicleOrder;
    }

    public VehicleExemplar getVehicleExemplar() {
        return vehicleExemplar;
    }

    public void setVehicleExemplar(VehicleExemplar vehicleExemplar) {
        this.vehicleExemplar = vehicleExemplar;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Status {
        WAITING,
        ACCEPTED,
        IN_PROGRESS,
        DECLINED,
        FINISHED
    }


}
