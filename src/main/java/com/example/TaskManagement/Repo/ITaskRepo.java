package com.example.TaskManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManagement.model.Tasks;

@Repository
public interface ITaskRepo extends JpaRepository<Tasks, Long> {

}
