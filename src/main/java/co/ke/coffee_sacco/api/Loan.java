package co.ke.coffee_sacco.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "Loan")
@Table(name = "loan")
public class Loan {
    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    private double amount;
    private int loanDurationInMonths = 1;
    private String loanStatus = "pending";
    private String loanType = "cash"; // or farmInput

    public Loan(Member member, double amount, int loanDurationInMonths, String loanType) {
        this.member = member;
        this.branch = member.getBranch();
        this.amount = amount;
        this.loanDurationInMonths = loanDurationInMonths;
//        this.loanStatus = "pending";
        this.loanType = loanType;
    }

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getLoanDurationInMonths() {
        return loanDurationInMonths;
    }

    public void setLoanDurationInMonths(int loanDurationInMonths) {
        this.loanDurationInMonths = loanDurationInMonths;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", member=" + member +
                ", branch=" + branch +
                ", amount=" + amount +
                ", loanDurationInMonths=" + loanDurationInMonths +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanType='" + loanType + '\'' +
                '}';
    }
}
