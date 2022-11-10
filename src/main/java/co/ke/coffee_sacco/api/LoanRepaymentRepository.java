package co.ke.coffee_sacco.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepaymentRepository  extends JpaRepository<LoanRepayment, Long> {
}
