package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanRepaymentRepository loanRepaymentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanRepaymentRepository loanRepaymentRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.loanRepaymentRepository = loanRepaymentRepository;
        this.memberRepository = memberRepository;
    }

    public Loan loanApplication(Loan loan) {
        Member member = memberRepository.findById(loan.getMember().getId())
                .orElseThrow();
        loan.setMember(member);
        return loanRepository.save(loan);
    }

    public List fetchLoans(){
        return loanRepository.findAll();
    }

    public Loan fetchLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalStateException(
                                "Loan with Id " + loanId + " does not exist!"
                        )
                );
    }

    @Transactional
    public void updateLoanStatus(Long loanId, String status) {
        Loan loan = this.fetchLoan(loanId);

        loan.setLoanStatus(status);
    }

    public void repayLoan(Long loanId) {
        Loan loan = this.fetchLoan(loanId);
        loan.setLoanStatus("repaid");

        LoanRepayment loanRepayment = new LoanRepayment(loan, loan.getAmount());
        loanRepaymentRepository.save(loanRepayment);
    }

    private Member fetchMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException(
                                "Member with Id " + memberId + " does not exist!"
                        )
                );
    }
}
