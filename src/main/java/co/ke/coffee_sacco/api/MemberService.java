package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, ProductDeliveryRepository productDeliveryRepository, BranchRepository branchRepository) {
        this.memberRepository = memberRepository;
        this.productDeliveryRepository = productDeliveryRepository;
        this.branchRepository = branchRepository;
    }

    public Member createMember(Member member) {
        Branch branch = branchRepository.findById(member.getBranch().getId())
                .orElseThrow();
        member.setBranch(branch);
        return memberRepository.save(member);
    }

    public List fetchMembers() { return memberRepository.findAll();};

    public Member fetchMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException(
                        "Member with Id " + memberId + " does not exist"
                )
        );
    }

    @Transactional
    public void updateMemberAccountBalance(Long memberId,double accountBalance) {
        Member member = this.fetchMember(memberId);

        member.setAccountBalance(accountBalance);
    }

    @Transactional
    public void updateMemberLoanLimit(Long memberId, double loanLimit) {
        Member member = this.fetchMember(memberId);

        member.setLoanLimit(loanLimit);
    }

    @Transactional
    public void updateMemberDeliveredProductQuantity(Long memberId,
            double deliveredProductQuantity) {
        Member member = this.fetchMember(memberId);

        member.setDeliveredProductQuantity(deliveredProductQuantity);
    }

    @Transactional
    public void deliverProduct(Long memberId, double quantity) {
        Member member = this.fetchMember(memberId);

        ProductDelivery productDelivery = new ProductDelivery(quantity, member);
        productDeliveryRepository.save(productDelivery);

        // update member's delivered quantity
        member.setDeliveredProductQuantity(quantity);
    }
}
