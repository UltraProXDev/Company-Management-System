package com.example.project.service;

import com.example.project.dto.ProjectRequest;
import com.example.project.dto.ProjectResponse;
import com.example.project.mapper.ProjectMapper;
import com.example.project.model.Project;
import com.example.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectResponse createProject(ProjectRequest projectRequest){
        Project project = new Project();
        Project savedProject = projectRepository.save(projectMapper.projectRequestMap(projectRequest, project));
        return projectMapper.projectResponseMap(savedProject);
    }

    public ProjectResponse readProject(Long id){
        Project readProject = projectRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Project not found."));
        return projectMapper.projectResponseMap(readProject);
    }

    public List<ProjectResponse> readAllProject() {
        List<Project> readAllProject = projectRepository.findByIsDeletedFalse();
        return readAllProject.stream().map(project -> projectMapper.projectResponseMap(project)).toList();
    }

    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest){
        Project updateProject = projectRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Project not found."));
        Project updatedProject = projectRepository.save(projectMapper.projectRequestMap(projectRequest, updateProject));
        return projectMapper.projectResponseMap(updatedProject);
    }

    public String deleteProject(Long id){
        if(projectRepository.existsById(id)){
            projectRepository.deleteById(id);
            return "Project Deleted Successfully.";
        }
        return "Project Not Found.";
    }

    public ProjectResponse softDeleteProject(Long id){
        Project softDeleteProject = projectRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Project not found or already deleted."));
        softDeleteProject.setIsDeleted(true);
        Project softDeletedProject = projectRepository.save(softDeleteProject);
        return projectMapper.projectResponseMap(softDeletedProject);
    }

    public ProjectResponse recoveryProject(Long id){
        Project recoveryProject = projectRepository.findByIdAndIsDeletedTrue(id).orElseThrow(() -> new RuntimeException("Project not exist or not deleted."));
        recoveryProject.setIsDeleted(false);
        Project recoverdProject = projectRepository.save(recoveryProject);
        return projectMapper.projectResponseMap(recoverdProject);
    }
}
