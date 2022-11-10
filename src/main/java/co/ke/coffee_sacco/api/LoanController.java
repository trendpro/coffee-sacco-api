package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List index() {
        return  loanService.fetchLoans();
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan){
        return loanService.loanApplication(loan);
    }

    @PutMapping(path = "{loanId}")
    public void update(
            @PathVariable("loanId") Long loanId,
            @RequestParam(required = true) String status) {
        loanService.updateLoanStatus(loanId, status);
    }

    @PostMapping("/repay-loan")
    public void repayLoan(@RequestBody Map<String, Object> reqMap) {
        Long loanId = ((Number) reqMap.get("loanId")).longValue();

        loanService.repayLoan(loanId);
    }
}
