package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
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
}
