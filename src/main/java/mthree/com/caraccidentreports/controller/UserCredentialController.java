package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.UserCredential;
import mthree.com.caraccidentreports.service.CredentialsNotMatchException;
import mthree.com.caraccidentreports.service.InvalidPasswordException;
import mthree.com.caraccidentreports.service.InvalidUsernameException;
import mthree.com.caraccidentreports.service.UserCredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-credential")
@CrossOrigin
public class UserCredentialController {

    @Autowired
    UserCredentialServiceImpl userCredentialServiceImpl;

    @GetMapping("/{username}")
    public UserCredential getUserCredentialByUsername(@PathVariable String username) {
        return userCredentialServiceImpl.getUserCredentialByUsername(username);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addUserCredential(@RequestBody UserCredential userCredential) {
        try {
            userCredentialServiceImpl.addNewUserCredential(userCredential);
            return ResponseEntity.ok("User added successfully!");
        } catch (InvalidUsernameException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the user.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredential userCredential) {
        try {
            UserCredential user = userCredentialServiceImpl.verifyUserCredentials(userCredential);

            // Return JSON instead of a string
            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("password", user.getPassword());
            response.put("email", user.getEmail());
            response.put("message", "Logged in successfully!");

            return ResponseEntity.ok(response);
        } catch (CredentialsNotMatchException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        try{
            userCredentialServiceImpl.deleteUserCredential(username);
            return ResponseEntity.ok("User deleted successfully!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the user.");
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserCredential> updateUserCredential(@PathVariable String username, @RequestBody UserCredential userCredential) {
        try {
            UserCredential updatedUser = userCredentialServiceImpl.updateUserCredential(username, userCredential);
            return ResponseEntity.ok(updatedUser);
        } catch (InvalidUsernameException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}