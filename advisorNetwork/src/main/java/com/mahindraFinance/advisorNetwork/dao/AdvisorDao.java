package com.mahindraFinance.advisorNetwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahindraFinance.advisorNetwork.model.Advisor;

@Repository
public interface AdvisorDao extends JpaRepository<Advisor, Long>   {

}
