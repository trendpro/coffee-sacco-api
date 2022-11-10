package co.ke.coffee_sacco.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository  extends JpaRepository<Loan, Long> {
}
