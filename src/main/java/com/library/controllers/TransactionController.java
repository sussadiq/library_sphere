package com.library.controllers;

import com.library.entities.Transaction;
import com.library.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Issue a book
    @PostMapping("/issue")
    public Transaction issueBook(@RequestBody Transaction transaction) {
        return transactionService.issueBook(transaction);
    }

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
