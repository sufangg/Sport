package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.AccountLogin;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountLogin> findByUsername(String username);
    AccountLogin registerAccount(AccountLogin account);
    List<AccountLogin> getAllAccounts();
}
