package com.hcq.seckill.repository;

import com.hcq.seckill.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);


    @Modifying
    @Query(value = "update t_user set account=account-?2 where id =?1", nativeQuery = true)
    void reduceAccount(Long id, Long reduceAmount);
}
