package com.project.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.project.domain.datatable.LadderTableEntry;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface UserRepository extends JpaRepository<LadderTableEntryEntity, Integer> {

}
	