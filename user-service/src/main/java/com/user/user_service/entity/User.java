package com.user.user_service.entity;

import com.user.user_service.constants.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, unique = true, length = 150)
    private String userEmail;

    private String userDesignation;

    private String userProfilePhoto; // Cloudinary link

    private Integer userPhoneNo;

    private String userAddress;

    private String userTimeZone;

    @Temporal(TemporalType.DATE)
    private Date userJoiningDate;

    private Boolean userIsNotificationAlert;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ElementCollection
    private List<Long> usertaskAssigned;

    @Transient
    private List<Task> userListOfTasksAssigned;

    @ElementCollection
    private List<Team> userInvolvedTeams;

    @ElementCollection
    private List<Long> userProject;


    //GETTERS AND SETTERS
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto;
    }

    public Integer getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(Integer userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public Date getUserJoiningDate() {
        return userJoiningDate;
    }

    public void setUserJoiningDate(Date userJoiningDate) {
        this.userJoiningDate = userJoiningDate;
    }

    public Boolean getUserIsNotificationAlert() {
        return userIsNotificationAlert;
    }

    public void setUserIsNotificationAlert(Boolean userIsNotificationAlert) {
        this.userIsNotificationAlert = userIsNotificationAlert;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Long> getUsertaskAssigned() {
        return usertaskAssigned;
    }

    public void setUsertaskAssigned(List<Long> usertaskAssigned) {
        this.usertaskAssigned = usertaskAssigned;
    }

    public List<Task> getUserListOfTasksAssigned() {
        return userListOfTasksAssigned;
    }

    public void setUserListOfTasksAssigned(List<Task> userListOfTasksAssigned) {
        this.userListOfTasksAssigned = userListOfTasksAssigned;
    }

    public List<Long> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<Long> userProject) {
        this.userProject = userProject;
    }

    public List<Team> getUserInvolvedTeams() {
        return userInvolvedTeams;
    }

    public void setUserInvolvedTeams(List<Team> userInvolvedTeams) {
        this.userInvolvedTeams = userInvolvedTeams;
    }
}


