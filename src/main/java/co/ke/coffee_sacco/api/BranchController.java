package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/branches")
public class BranchController {

    public final BranchService branchService;

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
}
