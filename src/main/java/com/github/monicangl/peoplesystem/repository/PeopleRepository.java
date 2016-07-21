package com.github.monicangl.peoplesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.monicangl.peoplesystem.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, String>
{
    People findByLoginName(String loginName);
}
