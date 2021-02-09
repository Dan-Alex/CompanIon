package com.alexdan.docflow.services;

import com.alexdan.docflow.data.DocumentRepository;
import com.alexdan.docflow.data.TaskRepository;
import com.alexdan.docflow.exceptions.TaskNotFoundException;
import com.alexdan.docflow.models.Task;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, DocumentRepository documentRepository){

        this.taskRepository = taskRepository;
        this.documentRepository= documentRepository;
    }

    public Set<Task> getAllTasks(User user) {

        return taskRepository.findAllTasks(user.getId());
    }

    public Set<Task> getCompletedSentTasks(User user){

        return  taskRepository.findCompletedSentTasks(user.getId());
    }

    public Task getTask(long id){

        return taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException(id));
    }

    public Task saveTask(Task task){

        Task savedTask = taskRepository.save(task);
        task.getDocuments().forEach(document -> {
            document.setTask(savedTask);
            documentRepository.save(document);
        });

        return savedTask;
    }

}
