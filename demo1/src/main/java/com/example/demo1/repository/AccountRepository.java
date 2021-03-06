package com.example.demo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo1.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	
	Account findByName(String name);
	List<Account> findAccountsByName(String name); 
	
	List<Account> findAll();
	
	Account findByEmailAddress(String emailAddress);
	
	Account findByMobile(int mobile);
	
	List<Account> findByNameLikeIgnoreCase(String name, Sort sort);
	Sort sort = Sort.by(Sort.Direction.ASC, "price");
	
	Account findByResetPasswordToken(String token);
	
//	@Query("from account a where a.name=:name")
//    Account findUser(@Param("name") String name);
//	@Transactional
//	@Modifying
//	@Query("update Account set is_active=?1 where id=?2")
//	void updateActive(Boolean isactive, Long id);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "insert into accounts_roles(account_id, role_id) values (:id1, select role_id from roles where name = :role )",
			nativeQuery = true)
	void insertRelation(@Param("id1") Long id1, @Param("role") String role);
	
	
	@Transactional
	@Modifying
	@Query(value = "delete from accounts_roles where account_id=:id1 and role_id=select role_id from roles where name=:role",
			nativeQuery = true)
	void deleteRelation(@Param("id1") Long id1, @Param("role") String role);
	

	
	
}
