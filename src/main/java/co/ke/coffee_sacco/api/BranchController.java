package co.ke.coffee_sacco.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public List index() {
        return branchService.fetchBranches();
    }

    @PostMapping
    public Branch create(@RequestBody Branch branch) {
       return branchService.createBranch(branch);
    }

    @DeleteMapping(path = "{branchId}")
    public void delete(@PathVariable("branchId") Long branchId) {
        branchService.deleteBranch(branchId);
    }

    @PutMapping(path = "{branchId}")
    public void update(
            @PathVariable("branchId") Long branchId,
            @RequestParam(required = true) String name) {
        branchService.updateBranch(branchId, name);
    }

    @PostMapping("/sell-product")
    public void saleProduct(@RequestBody Map<String, Object> reqMap) {
        double sellingPrice = ((Number) reqMap.get("sellingPrice")).doubleValue();

        branchService.saleProduct(sellingPrice);
    }
}
