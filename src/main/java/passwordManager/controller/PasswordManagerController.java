package passwordManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.exceptions.IncorrectPasswordException;
import passwordManager.exceptions.PasswordNotFoundException;
import passwordManager.services.PasswordServices;

@RestController
@RequestMapping("/passwords")
public class PasswordManagerController {
    @Autowired
    private PasswordServices passwordServices;
        @PostMapping
        public String addPassword(@RequestBody PasswordCreateRequest passwordCreateRequest) {
            passwordServices.addPassword(passwordCreateRequest);
            return "Password added";
        }

        @GetMapping("/{website}")
        public GetPasswordResponse getPassword(@RequestBody GetPasswordRequest website) {
            return passwordServices.getPassword(website);
        }

        @PutMapping("/{website}")
        public String updatePassword(@RequestBody UpdateRequest updateRequest) {
            try{
                passwordServices.updatePassword(updateRequest);
                return "Password updated successfully";
            }catch (IncorrectPasswordException e){
                return e.getMessage();
            }
        }

        @DeleteMapping("/{website}")
        public String deletePassword(@RequestBody DeleteRequest website) {
            try{
           passwordServices.deletePassword(website);
           return "Password deleted";
        }catch (PasswordNotFoundException e){
            return e.getMessage();
            }
        }
    }

