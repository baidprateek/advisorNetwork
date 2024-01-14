package com.mahindraFinance.advisorNetwork.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahindraFinance.advisorNetwork.model.UserData;

@Repository
public interface UserDao extends JpaRepository<UserData, Long>   {

	List<UserData> findByEmailAndPassword(String email, String password);
}
