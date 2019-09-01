package com.syncsort.kanban.ppmtool.repositories;

import com.syncsort.kanban.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectIdentifierID);

    @Query("delete from Project where projectIdentifier = ?1")
    @Modifying
    void deleteByProjectIdentifier(String projectIdentifierID);

}
