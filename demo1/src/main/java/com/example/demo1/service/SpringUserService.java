package com.example.demo1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo1.entity.Account;
import com.example.demo1.entity.SpringUser;
import com.example.demo1.repository.AccountRepository;


@Service("SpringUserService")
public class SpringUserService implements UserDetailsService {
    

    @Autowired
    private AccountRepository accountRepository;
    
    public SpringUserService(AccountRepository accountRepository) {
		// TODO Auto-generated constructor stub
    	this.accountRepository=accountRepository;
	}
    
    
    //use to match input username/password with DB username/password, if not invoked, spring security uses auto generated username/password
    @Override
    public SpringUser loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            Account account1 = demo1Service.getAccount(Long.valueOf(username));
//            List<SimpleGrantedAuthority> authorities = account.getAuthorities().stream()
//                    .map(auth -> new SimpleGrantedAuthority(auth.name()))
//                    .collect(Collectors.toList());
//            System.out.println(account1.getPassword());
//            return new User(account1.getName(), account1.getPassword(), Collections.emptyList());
//        } catch (NotFoundException e) {
//            throw new UsernameNotFoundException("ID is wrong.");
//        }
    	Optional<Account> accounts = Optional.of(accountRepository.findByName(username));
    	if (accounts.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }
    	Account account=accounts.get();

    	SpringUser user = new SpringUser(account);
    	System.out.println(user.isEnabled());
    	System.out.println(user.isAccountNonExpired());
    	
    	if(!user.isEnabled()) {
    		System.out.println("not active");
//            throw new UsernameNotFoundException("Not Active");
    	}
    	//return new User(account.getId().toString(),account.getPassword(),Collections.emptyList());
        return user;
    	
    }
    
    
    
    
}