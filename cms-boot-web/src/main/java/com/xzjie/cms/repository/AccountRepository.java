package com.xzjie.cms.repository;

import com.xzjie.cms.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Account findAccountByNameAndState(String name, Integer state);

    Account findAccountByPhoneAndState(String phone, Integer state);

    default Account findAccountByName(String name){
        return findAccountByNameAndState(name,1);
    }

    default Account findAccountByPhone(String phone){
        return findAccountByPhoneAndState(phone,1);
    }

    @Modifying
    @Transactional
    @Query("UPDATE Account SET avatar = :avatar WHERE name = :name")
    int updateAvatar(String name, String avatar);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET password = :password WHERE userId = :userId")
    int updatePassword(Long userId, String password);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET phone = :phone WHERE userId = :userId")
    int updatePhone(Long userId, String phone);

    @Modifying
    @Transactional
    @Query("UPDATE Account SET email = :email WHERE userId = :userId")
    int updateEmail(Long userId, String email);

    boolean existsByName(String name);
}
