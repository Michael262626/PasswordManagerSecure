package passwordManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import passwordManager.data.model.Password;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.ApiResponse;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.exceptions.IncorrectPasswordException;
import passwordManager.exceptions.PasswordNotFoundException;
import passwordManager.services.PasswordServices;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/passwords")
public class PasswordManagerController {
    @Autowired
    private PasswordServices passwordServices;
        @PostMapping
        public ResponseEntity<?> addPassword(@RequestBody PasswordCreateRequest passwordCreateRequest) {
            try{
                var result = passwordServices.addPassword(passwordCreateRequest);
                return new ResponseEntity<>(new ApiResponse(true,result), CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),BAD_REQUEST);
            }
        }

        @GetMapping("/{website}")
        public ResponseEntity<ApiResponse> findPassword(@RequestBody GetPasswordRequest getPasswordRequest) {
            try {
                var password = passwordServices.getPassword(getPasswordRequest);
                return ResponseEntity.ok(new ApiResponse(true, password));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage()));
            }
        }

        @PutMapping("/{update}")
        public ResponseEntity<?> updatePassword(@RequestBody UpdateRequest updateRequest) {
            try{
                var result = passwordServices.updatePassword(updateRequest);
                return new ResponseEntity<>(new ApiResponse(true,result), CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),BAD_REQUEST);
            }
        }

        @DeleteMapping("/{delete}")
        public ResponseEntity<?> deletePassword(@RequestBody DeleteRequest website) {
            try{
           var result = passwordServices.deletePassword(website);
                return new ResponseEntity<>(new ApiResponse(true,result), CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),BAD_REQUEST);
            }
        }
    }

