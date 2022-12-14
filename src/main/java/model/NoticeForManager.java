package model;

import javax.persistence.*;

@Table
@Entity
public class NoticeForManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public NoticeForManager(String text, User forManager)
    {
        this.text=text;
        this.forManager=forManager;
        this.isRead =false;
    }

    public NoticeForManager() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    private String text;

    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "for_manager_id")
    private User forManager;

    public User getForManager() {
        return forManager;
    }

    public void setForManager(User forManager) {
        this.forManager = forManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
