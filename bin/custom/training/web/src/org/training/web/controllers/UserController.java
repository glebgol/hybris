package org.training.web.controllers;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String handleRequest(final ModelMap model, @RequestParam(required = false) String uid) {
        UserModel user = null;

        if (uid == null || uid.isEmpty()) {
            user = userService.getCurrentUser();
        } else {
            user = userService.getUserForUID(uid);
        }

        model.put("user", user);
        return "user";
    }
}
