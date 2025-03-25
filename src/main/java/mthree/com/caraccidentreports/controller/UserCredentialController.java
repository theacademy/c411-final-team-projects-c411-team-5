package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.UserCredential;
import mthree.com.caraccidentreports.service.InvalidPasswordException;
import mthree.com.caraccidentreports.service.InvalidUsernameException;
import mthree.com.caraccidentreports.service.UserCredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/validate")
    public boolean validateUserCredentials(@RequestBody UserCredential userCredential) {
        return userCredentialService.validateUserCredentials(userCredential);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addUserCredential(@RequestBody UserCredential userCredential) {
        try {
            UserCredential savedUser = userCredentialService.addNewUserCredential(userCredential);
            return ResponseEntity.ok("User added successfully! Username: " + savedUser.getUsername());
        } catch (InvalidUsernameException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}