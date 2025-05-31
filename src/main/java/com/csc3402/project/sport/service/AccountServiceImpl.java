package com.csc3402.project.sport.service;

import com.csc3402.project.sport.model.AccountLogin;
import com.csc3402.project.sport.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AccountLogin> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public AccountLogin registerAccount(AccountLogin account) {
        // Encode password before saving
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public List<AccountLogin> getAllAccounts() {
        return accountRepository.findAll();
    }
}
