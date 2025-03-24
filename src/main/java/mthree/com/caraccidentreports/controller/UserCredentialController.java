package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.UserCredential;
import mthree.com.caraccidentreports.service.UserCredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-credential")
@CrossOrigin
public class UserCredentialController {

    @Autowired
    UserCredentialServiceImpl userCredentialService;

    @GetMapping("/{username}")
    public UserCredential getUserCredentialByUsername(@PathVariable String username) {
        return userCredentialService.getUserCredentialByUsername(username);
    }

    @PostMapping("/add")
    public UserCredential addUserCredential(@RequestBody UserCredential userCredential) {
        return userCredentialService.addNewUserCredential(userCredential);
    }
}