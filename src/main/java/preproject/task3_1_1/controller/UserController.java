package preproject.task3_1_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import preproject.task3_1_1.model.User;
import preproject.task3_1_1.repo.UserRepo;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{username}")
    public String userMain(@PathVariable String username, Model model) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        return "main";
    }
}
