package com.example.day20exerciseq2.Controller;

import com.example.day20exerciseq2.ApiResponse.ApiResponse;
import com.example.day20exerciseq2.Model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    ArrayList<Account> accounts = new ArrayList<>();


    // EndPoint to get all the customers
    @GetMapping("/get")
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Endpoint to add new account
    @PostMapping("/add")
    public ApiResponse addAccount(@RequestBody Account account) {
        accounts.add(account);
        return new ApiResponse("Account added successfully");
    }

    // Endpoint to update a customer
    @PutMapping("/update/{index}")
    public ApiResponse updateAccount(@PathVariable int index, @RequestBody Account account) {
        accounts.set(index, account);
        return new ApiResponse("Account updated successfully");
    }

    // Endpoint to delete an account
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteAccount(@PathVariable int index) {
        accounts.remove(index);
        return new ApiResponse("Account deleted successfully");
    }

    // endpoint to deposit for the user
    @PutMapping("/deposit/{amount}/{id}")
    public ApiResponse depositMoney (@PathVariable double amount, @PathVariable String id) {

        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                account.setBalance(account.getBalance() + amount);
                return new ApiResponse("Deposit successfully");
            }
        }
        return new ApiResponse("Account not found");
    }


    // Endpoint for withdraw money
    @PutMapping("/withdraw/{amount}/{id}")
    public ApiResponse withdrawMoney (@PathVariable double amount, @PathVariable String id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    return new ApiResponse("Withdraw successfully");
                }else {
                    return new ApiResponse("Withdrawal failed, No enough balance");
                }

            }
        }
        return new ApiResponse("Account not found");
    }

}
