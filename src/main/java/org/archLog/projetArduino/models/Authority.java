package org.archLog.projetArduino.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id")
    private long id;
    @Column(name = "authority", nullable = false)
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}