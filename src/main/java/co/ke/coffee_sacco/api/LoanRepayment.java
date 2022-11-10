package co.ke.coffee_sacco.api;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "LoanRepayment")
@Table(name = "loan_repayment")
public class LoanRepayment {
    @Id
    @SequenceGenerator(
            name = "loan_repayment_sequence",
            sequenceName = "loan_repayment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_repayment_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
    private Date repaymentDate;
    private double amount;

    public LoanRepayment(Loan loan, double amount) {
        this.loan = loan;
        this.repaymentDate = new Date();
        this.amount = amount;
    }

    public LoanRepayment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LoanRepayment{" +
                "id=" + id +
                ", loan=" + loan +
                ", repaymentDate=" + repaymentDate +
                ", amount=" + amount +
                '}';
    }
}
