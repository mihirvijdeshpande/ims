package com.mini.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.ims.model.Borrower;

public interface IBorrowerRepository extends JpaRepository<Borrower, Long> {

}
