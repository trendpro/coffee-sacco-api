package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public UserService(UserRepository userRepository, BranchRepository branchRepository) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }

    public User createUser(User user) {
        Branch branch = branchRepository.findById(user.getBranch().getId())
                .orElseThrow();
        user.setBranch(branch);
        return userRepository.save(user);
    }

    public List fetchUsers(){
        return userRepository.findAll();
    }
}
