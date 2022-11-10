package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List index() {
        return memberService.fetchMembers();
    }

    @PostMapping
    public Member create(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping(path = "{memberId}")
    public Member show(@PathVariable("memberId") Long memberId) {
        return memberService.fetchMember(memberId);
    }

    @PostMapping("/deliver-product")
    public void deliverProduct(@RequestBody Map<String, Object> reqMap) {
        Long memberId = ((Number) reqMap.get("memberId")).longValue();
        double quantity = ((Number) reqMap.get("quantity")).doubleValue();

        memberService.deliverProduct(memberId, quantity);
    }

}
