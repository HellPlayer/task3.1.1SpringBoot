package preproject.task3_1_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import preproject.task3_1_1.model.User;
import preproject.task3_1_1.repo.UserRepo;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{username}")
    public String userMain(@PathVariable String username, Model model) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/admin")
    public String adminMain() {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String showUsers(Model model) {
        List<User> userList = userRepo.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser() {
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email
    ) {
        User user = new User(username, password, email);
        userRepo.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser() {
        return "delete";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser() {
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam Long id,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {
        User user = userRepo.getOne(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/admin";
    }
}
