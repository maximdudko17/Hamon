package hamon.first.budget_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(/*@RequestBody TransactionDTO */){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
