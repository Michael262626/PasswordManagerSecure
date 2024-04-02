package passwordManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.exceptions.IncorrectPasswordException;
import passwordManager.services.PasswordServices;

@RestController
@RequestMapping("/passwords")
public class PasswordManagerController {
    @Autowired
    private PasswordServices passwordServices;
        @PostMapping
        public void addPassword(@RequestParam PasswordCreateRequest passwordCreateRequest) {
            passwordServices.addPassword(passwordCreateRequest);
        }

        @GetMapping("/{website}")
        public GetPasswordResponse getPassword(@PathVariable GetPasswordRequest website) {
              return  passwordServices.getPassword(website);
        }

        @PutMapping("/{website}")
        public String updatePassword(@RequestParam UpdateRequest updateRequest) {
            try{
                passwordServices.updatePassword(updateRequest);
                return "password updated successfully";
            }catch (IncorrectPasswordException e){
                return e.getMessage();
            }
        }

        @DeleteMapping("/{website}")
        public void deletePassword(@PathVariable DeleteRequest website) {
           passwordServices.deletePassword(website);
        }
    }

