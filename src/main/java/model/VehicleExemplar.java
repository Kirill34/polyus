package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class VehicleExemplar {

    public VehicleExemplar() {

    }

    public enum Status {
        FREE,
        BUSY,
        IN_SERVICE,
        NEED_HELP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private VehicleModel model;

    private Date produceDate;

    private Date lastCheckUpDate;

    private Date nextDateCheckUpDate;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public VehicleExemplar(VehicleModel model, Date produceDate, Date lastCheckUpDate, Date nextDateCheckUpDate, String number, double latitude, double longitude, User manager) {
        this.model = model;
        this.produceDate = produceDate;
        this.lastCheckUpDate = lastCheckUpDate;
        this.nextDateCheckUpDate = nextDateCheckUpDate;
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.manager = manager;
        this.status = Status.FREE;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public void setLastCheckUpDate(Date lastCheckUpDate) {
        this.lastCheckUpDate = lastCheckUpDate;
    }

    public void setNextDateCheckUpDate(Date nextDateCheckUpDate) {
        this.nextDateCheckUpDate = nextDateCheckUpDate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private String number;

    public Date getProduceDate() {
        return produceDate;
    }

    public Date getLastCheckUpDate() {
        return lastCheckUpDate;
    }

    public Date getNextDateCheckUpDate() {
        return nextDateCheckUpDate;
    }

    public String getNumber() {
        return number;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double latitude;

    private double longitude;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
