package courseonline4399.online.controller;

import courseonline4399.online.model.Role;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.UseroleRepository;
import courseonline4399.online.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Admin_Authorities {
    @Autowired
    AccountRoleService accountRoleService;
    @GetMapping(value = {"/admin/authority"})
    public String Authority(Model model) {
        List<Userole> useroles = accountRoleService.findAll();
        model.addAttribute("useroles", useroles);


        return "admin/table/authorities-table";
    }

    @PostMapping("/admin/update-role/user/{useroleId}/role/{roleName}")
    public String updateRole(@PathVariable("useroleId") Integer useroleId, @PathVariable("roleName") String roleName) {
        try {
            Userole updateuserole = accountRoleService.findById(useroleId);
            Role role = new Role();
            role.setId(roleName);
            role.setName(roleName);
            updateuserole.setRole(role);

            accountRoleService.update(updateuserole);

            //return new UseroleRepository("Role updated successfully", HttpStatus.OK);
            return "redirect:/admin/authority";
        } catch (Exception e) {
            //return new UseroleRepository("Error updating role", HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/admin/authority";

        }
    }
    @PostMapping("/admin/delete-role/user/{useroleId}/role/{roleName}")
    public String deleteRole(@PathVariable("useroleId") Integer useroleId, @PathVariable("roleName") String roleName) {
        try {
            Userole updateuserole = accountRoleService.findById(useroleId);
            Role role = new Role();
            role.setId("USER");
            role.setName("USER");
            updateuserole.setRole(role);

            accountRoleService.update(updateuserole);

            //return new UseroleRepository("Role updated successfully", HttpStatus.OK);
            return "redirect:/admin/authority";
        } catch (Exception e) {
            //return new UseroleRepository("Error updating role", HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/admin/authority";

        }
    }

}

