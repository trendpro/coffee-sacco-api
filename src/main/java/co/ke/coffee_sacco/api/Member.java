package co.ke.coffee_sacco.api;

import javax.persistence.*;

@Entity(name = "Member")
@Table(name = "member")
public class Member {
    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    private double loanLimit = 0.0;
    private double accountBalance = 0.0;
    private double deliveredProductQuantity =  0.0;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Member(String name, Branch branch) {
        this.name = name;
        this.branch = branch;
    }

    public Member() {
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

    public double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(double loanLimit) {
        this.loanLimit += loanLimit;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance += accountBalance;
    }

    public double getDeliveredProductQuantity() {
        return deliveredProductQuantity;
    }

    public void setDeliveredProductQuantity(double deliveredProductQuantity) {
        this.deliveredProductQuantity += deliveredProductQuantity;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch=" + branch +
                ", loanLimit=" + loanLimit +
                ", accountBalance=" + accountBalance +
                ", deliveredProductQuantity=" + deliveredProductQuantity +
                '}';
    }
}
