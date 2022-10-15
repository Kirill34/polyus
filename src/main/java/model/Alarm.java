package model;

import javax.persistence.*;

@Entity
@Table
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Alarm(User manager, String text)
    {
        this.manager=manager;
        this.text=text;
        this.read=false;
    }

    public Alarm()
    {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    private String text;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private boolean read;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
