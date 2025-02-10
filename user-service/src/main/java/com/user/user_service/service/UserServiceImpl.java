package com.user.user_service.service;

import com.user.user_service.entity.Task;
import com.user.user_service.entity.Team;
import com.user.user_service.entity.User;
import com.user.user_service.exception.ResourceNotFoundException;
import com.user.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    // Logger instance for logging important events and errors
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user and saves it to the database.
     * @param user User object to be created.
     * @return The saved User.
     */
    @Override
    public User createNewUser(User user) {
        LOGGER.info("Creating new user...");
        return userRepository.save(user);
    }

    /**
     * Fetches all users.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Fetches a user by its ID.
     *
     * @param userId The ID of the user to fetch.
     * @return The User object if found.
     * @throws ResourceNotFoundException if no user is found with the given ID.
     */
    @Override
    public User getUserById(Long userId) {
        LOGGER.info("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    /**
     * Updates a user by its ID.
     *
     * @param userId The ID of the user to update.
     * @return The updated User object.
     * @throws ResourceNotFoundException if no user is found with the given ID.
     */
    @Override
    public User updateUserById(Long userId, User user) {
        // Get existing user
        User existingUser = getUserById(userId);

        existingUser.setUserName(user.getUserName());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserDesignation(user.getUserDesignation());
        existingUser.setUserProfilePhoto(user.getUserProfilePhoto());
        existingUser.setUserPhoneNo(user.getUserPhoneNo());
        existingUser.setUserAddress(user.getUserAddress());
        existingUser.setUserTimeZone(user.getUserTimeZone());
        existingUser.setUserIsNotificationAlert(user.getUserIsNotificationAlert());
        existingUser.setUserRole(user.getUserRole());
        existingUser.setUserInvolvedTeams(user.getUserInvolvedTeams());
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user by its ID.
     *
     * @param userId The ID of the user to delete.
     * @throws ResourceNotFoundException if no user is found with the given ID.
     */
    @Override
    public void deleteUser(Long userId) {
        LOGGER.info("Deleting user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        userRepository.delete(user);
    }

    /**
     * Fetches all tasks assigned to a user.
     *
     * @param userId The ID of the user whose tasks are to be fetched.
     * @return A list of tasks assigned to the user.
     */
//    @Override
//    public List<Task> getAllTasksOfUser(Long userId) {
//        LOGGER.info("Fetching all tasks for user with ID: {}", userId);
//        return userRepository.;
//    }
    @Override
    public List<Task> getAllTasksOfUser(Long userId) {
        return List.of();
    }

    /**
     * Fetches all project IDs associated with a user.
     *
     * @param userId The ID of the user whose projects are to be fetched.
     * @return A list of project IDs associated with the user.
     * @throws ResourceNotFoundException if no user is found with the given ID.
     */
    @Override
    public List<Long> getAllProjectOfUser(Long userId) {
        LOGGER.info("Fetching all projects for user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        return user.getUserProject();
    }

    @Override
    public List<Team> getAllTeamsOfUser(Long userId) {
        return List.of();
    }
}
