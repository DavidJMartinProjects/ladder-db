package com.project.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
//public interface UserRepository extends CrudRepository<CharacterInfo, Integer> {
public interface UserRepository extends JpaRepository<LadderTableEntry, Integer> {

}
