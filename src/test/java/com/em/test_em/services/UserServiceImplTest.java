package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;


import com.em.test_em._DTO.UserDTO;
import com.em.test_em.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class UserServiceImplTest {
    
    @Mock
    private UserServiceImpl userServiceImplMock;
    
    @Mock
    private UserRepository userRepository;
    
    @Test
    public void testgetUserById() {
        UserDTO expectedUserById = new UserDTO();
        expectedUserById.setId(1L);

        // Mock the repository method
        when(userServiceImplMock.getUserById(1L)).thenReturn(expectedUserById);

        // Call the service method to get the task by ID
        Optional<UserDTO> result = Optional.of(userServiceImplMock.getUserById(1L));

        // Now, you can perform assertions on the result if needed
        assertTrue(result.isPresent());
        assertEquals(expectedUserById, result.get());
    }

    @Test
    public void testCreateUser() {
        UserDTO createdUser = Mockito.mock(UserDTO.class);

        // Mock the repository method
        Mockito.when(userServiceImplMock.createUser(Mockito.any(UserDTO.class))).thenReturn(createdUser);

        // Call the service method to create a user
        UserDTO result = userServiceImplMock.createUser(new UserDTO());

        // Now, you can perform assertions on the result if needed
        assertNotNull(result);
        assertEquals(createdUser, result);
    }

    @Test
    public void testUpdateUser() {
        // Create a UserDTO to represent the updated user data
        UserDTO updatedUserData = new UserDTO();
        updatedUserData.setId(1L);
        updatedUserData.setUsername("newUsername");
        updatedUserData.setPassword("password");
        updatedUserData.setFirstName("firstName");
        updatedUserData.setLastName("lastName");
        updatedUserData.setEmail("email");
        updatedUserData.setEmail("email");
        updatedUserData.setExecutor(true);
        
        
        
        // Mock the repository method
        Mockito.when(userServiceImplMock.updateUser(Mockito.any(UserDTO.class), Mockito.anyLong()))
               .thenReturn(updatedUserData);

        // Call the service method to update a user
        UserDTO result = userServiceImplMock.updateUser(new UserDTO(), 1L);

        // Now, you can perform assertions on the result if needed
        assertNotNull(result);
        assertEquals(updatedUserData, result);
    }

    
    @Test
    public void testDeleteUser() {
        // Specify the user ID to be deleted
        Long userId = 1L;
        
        // Call the deleteUser method
        userServiceImplMock.deleteUser(userId);
    }

}

