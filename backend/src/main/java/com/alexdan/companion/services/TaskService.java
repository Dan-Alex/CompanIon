package com.alexdan.companion.services;

import com.alexdan.companion.data.DocumentRepository;
import com.alexdan.companion.data.TaskRepository;
import com.alexdan.companion.data.UserRepository;
import com.alexdan.companion.exceptions.TaskNotFoundException;
import com.alexdan.companion.models.Task;
import com.alexdan.companion.models.TasksStatuses;
import com.alexdan.companion.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       DocumentRepository documentRepository,
                       UserRepository userRepository){

        this.taskRepository = taskRepository;
        this.documentRepository= documentRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllIncomingTasks(User user) {

        return userRepository.findById(user.getId()).get().getIncomingTasks();
    }

    public List<Task> getCompletedOutgoingTasks(User user){

        return  userRepository.findById(user.getId()).get().getOutgoingTasks().
                                                            stream().
                                                            filter(task -> task.getStatus() == TasksStatuses.COMPLETED).
                                                            collect(Collectors.toList());
    }

    public List<Task> getNewIncomingTasks(User user) {

        return this.getAllIncomingTasks(user).stream().
                                                filter(task -> task.getStatus() == TasksStatuses.NEW).
                                                collect(Collectors.toList());
    }

    public List<Task> getPerformedIncomingTasks(User user) {

        return this.getAllIncomingTasks(user).stream().
                                                filter(task -> task.getStatus() == TasksStatuses.PERFORMED).
                                                collect(Collectors.toList());
    }

    public List<Task> getCompletedIncomingTasks(User user) {

        return this.getAllIncomingTasks(user).stream().
                                                filter(task -> task.getStatus() == TasksStatuses.COMPLETED).
                                                collect(Collectors.toList());
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
