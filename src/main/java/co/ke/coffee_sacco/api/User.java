package co.ke.coffee_sacco.api;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    private String role;
    private String name;

    public User(Branch branch, String role, String name) {
        this.branch = branch;
        this.role = role;
        this.name = name;
    }

    public User() {
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", branch=" + branch +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
