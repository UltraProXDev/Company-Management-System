package com.example.project.controller;

import com.example.project.dto.ProjectRequest;
import com.example.project.dto.ProjectResponse;
import com.example.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @GetMapping("/read")
    public ResponseEntity<ProjectResponse> readProject(@RequestParam Long id){
        return ResponseEntity.ok(projectService.readProject(id));
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<ProjectResponse>> readAllProject(){
        List<ProjectResponse> readAllProject = projectService.readAllProject();
        return ResponseEntity.ok(readAllProject);
    }

    @PutMapping("/update")
    public ResponseEntity<ProjectResponse> updateProject(@RequestParam Long id, @Valid @RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.updateProject(id, projectRequest));
    }

    @DeleteMapping("/delete")
    public String deleteProject(@RequestParam Long id){
        return projectService.deleteProject(id);
    }

    @PatchMapping("/soft/delete")
    public ResponseEntity<ProjectResponse> softDeteteProject(@RequestParam Long id){
        return ResponseEntity.ok(projectService.softDeleteProject(id));
    }

    @PatchMapping("/recovery")
    public ResponseEntity<ProjectResponse> recoveryProject(@RequestParam Long id){
        return ResponseEntity.ok(projectService.recoveryProject(id));
    }
}
