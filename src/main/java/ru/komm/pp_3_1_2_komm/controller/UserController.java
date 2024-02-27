package ru.komm.pp_3_1_2_komm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.komm.pp_3_1_2_komm.dao.UserDAO;
import ru.komm.pp_3_1_2_komm.model.User;

@Controller
@RequestMapping("/usr")
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
        return "/usr/allusers";
    }

    @GetMapping("/newuser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/usr/newuser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userDAO.addUser(user);
        return "redirect:/usr";
    }

    @GetMapping("/{id}")
    public String showPersonalPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userDAO.getUser(id));
        return "/usr/personalpage";
    }

    @GetMapping("/{id}/edituser")
    public String showEditUserForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getUser(id));
        return "/usr/edituser";
    }

    @PostMapping("/{id}")
    public String saveEditedUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.editUser(id, user);
        return "redirect:/usr";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/usr";
    }
}
