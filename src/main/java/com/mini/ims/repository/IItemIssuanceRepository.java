package com.mini.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.ims.model.Loan;

public interface IItemIssuanceRepository extends JpaRepository<Loan, Long> {

}
