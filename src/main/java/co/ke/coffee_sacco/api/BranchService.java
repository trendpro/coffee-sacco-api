package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final MemberRepository memberRepository;
    private final ProductSaleRepository productSaleRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository, MemberRepository memberRepository, ProductSaleRepository productSaleRepository) {
        this.branchRepository = branchRepository;
        this.memberRepository = memberRepository;
        this.productSaleRepository = productSaleRepository;
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List fetchBranches() {
        return branchRepository.findAll();
    }

    public void deleteBranch(Long branchId) {
        boolean exists = branchRepository.existsById(branchId);
        if (!exists) {
            throw new IllegalStateException("Student with given ID not does not exist");
        }
        branchRepository.deleteById(branchId);
    }

    @Transactional
    public void updateBranch(Long branchId, String name) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalStateException(
                        "Branch with id " + branchId + " does not exist!"
                        )
                );
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(branch.getName(), name)) {
            branch.setName(name);
        }
    }

    @Transactional
    public void saleProduct(double sellingPrice) {
        List<Member> members = memberRepository.findAll();

        for(Member member: members) {
            double deliveredProductQuantity = member.getDeliveredProductQuantity();

            if (deliveredProductQuantity <= 0) {
                break;
            }

            // update account balance
            member.setAccountBalance(deliveredProductQuantity * sellingPrice);

            // decrement deliveredProductQuantity
            member.setDeliveredProductQuantity(-deliveredProductQuantity);

            // loan limit = 80% of accountLimit
            member.setLoanLimit(0.8 * member.getAccountBalance());

            // persist the sale in db
            ProductSale productSale = new ProductSale(
                    sellingPrice,
                    deliveredProductQuantity,
                    member.getBranch());

            productSaleRepository.save(productSale);
        }
    }
}
