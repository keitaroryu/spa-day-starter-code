package org.launchcode.spaday.controllers;


import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("index")
    public String displayAllUsers(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {

        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            return "redirect:index";
        } else {
            model.addAttribute("error", "Password did not match, re-enter password.");
            model.addAttribute("user", user.getUsername());
            model.addAttribute("useremail", user.getEmail());
            return "user/add";
        }

    }

    @GetMapping("detail/{userId}")
    public String displayEditForm(Model model, @PathVariable int userId) {
        model.addAttribute("userById", UserData.getById(userId));
        model.addAttribute("UserDetailsTitle", "Details for " + UserData.getById(userId).getUsername());
        return "user/detail";
    }

}
