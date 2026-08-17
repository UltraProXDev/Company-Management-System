package com.example.project.mapper;

import com.example.project.dto.ProjectRequest;
import com.example.project.dto.ProjectResponse;
import com.example.project.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project projectRequestMap(ProjectRequest projectRequest, Project project){
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStartDate(projectRequest.getStartDate());
        project.setEndDate(projectRequest.getEndDate());
        project.setStatus(projectRequest.getStatus());
        return project;
    }

    public ProjectResponse projectResponseMap(Project project){
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getCreatedAt()
        );
    }

}
