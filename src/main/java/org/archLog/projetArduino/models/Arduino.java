package org.archLog.projetArduino.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "arduinos")
public class Arduino {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private boolean manual;
    @Column
    private String state;
    @Column
    private String port;
    @Column
    private int baud;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date updated;

    public Arduino(boolean manual, String state, String port, int baud) {
        this.manual = manual;
        this.state = state;
        this.port = port;
        this.baud = baud;
    }

    public Arduino() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBaud() {
        return baud;
    }

    public void setBaud(int baud) {
        this.baud = baud;
    }

    @Override
    public String toString() {
        return "Arduino{" +
                "id=" + id +
                ", manual=" + manual +
                ", state='" + state + '\'' +
                ", port='" + port + '\'' +
                ", baud=" + baud +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
