package co.ke.coffee_sacco.api;

import javax.persistence.*;

@Entity(name = "Branch")
@Table(name = "branch")
public class Branch {
    @Id
    @SequenceGenerator(
            name = "branch_sequence",
            sequenceName = "branch_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "branch_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public Branch(Long id) {
        this.id = id;
    }

    public Branch() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
