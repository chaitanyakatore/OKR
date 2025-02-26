package com.task.task_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.task_service.constants.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskHeading;
    private String taskDescription;
    private Long taskOwner;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private Date taskStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskDueDate;

    private String taskTag;

    private boolean taskIsActive;

    private Long taskAssociatedKeyResult;
    private Long taskAssociatedObjective;
    private TaskStatus taskStatus;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskHeading() {
        return taskHeading;
    }

    public void setTaskHeading(String taskHeading) {
        this.taskHeading = taskHeading;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Long getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(Long taskOwner) {
        this.taskOwner = taskOwner;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public boolean isTaskIsActive() {
        return taskIsActive;
    }

    public void setTaskIsActive(boolean taskIsActive) {
        this.taskIsActive = taskIsActive;
    }

    public Long getTaskAssociatedKeyResult() {
        return taskAssociatedKeyResult;
    }

    public void setTaskAssociatedKeyResult(Long taskAssociatedKeyResult) {
        this.taskAssociatedKeyResult = taskAssociatedKeyResult;
    }

    public Long getTaskAssociatedObjective() {
        return taskAssociatedObjective;
    }

    public void setTaskAssociatedObjective(Long taskAssociatedObjective) {
        this.taskAssociatedObjective = taskAssociatedObjective;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
