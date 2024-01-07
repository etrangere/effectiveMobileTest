package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.em.test_em._DTO.UserDTO;
import com.em.test_em.repositories.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
// to keep database fresh for each test
@Transactional
public class UserServiceImplTest {

  @Mock private UserServiceImpl userServiceImplMock;

  @Mock private UserRepository userRepository;

  @Test
  public void testgetUserById() {

    // creating object and setting attribute
    UserDTO expectedUserById = new UserDTO();
    expectedUserById.setId(1L);

    // repository method and objective of test
    when(userServiceImplMock.getUserById(1L)).thenReturn(expectedUserById);

    // what to be checked or verified
    Optional<UserDTO> result = Optional.of(userServiceImplMock.getUserById(1L));

    // assert to check
    assertTrue(result.isPresent());
    assertEquals(expectedUserById, result.get());
  }

  @Test
  public void testCreateUser() {

    // mock UserDTO
    UserDTO createdUser = Mockito.mock(UserDTO.class);

    // repository method and objective of test
    Mockito.when(userServiceImplMock.createUser(Mockito.any(UserDTO.class)))
        .thenReturn(createdUser);

    // what to be checked or verified
    UserDTO result = userServiceImplMock.createUser(new UserDTO());

    // assert to check
    assertNotNull(result);
    assertEquals(createdUser, result);
  }

  @Test
  public void testUpdateUser() {

    // creating object and setting attribute
    UserDTO updatedUserData = new UserDTO();
    updatedUserData.setId(1L);
    updatedUserData.setUsername("newUsername");
    updatedUserData.setPassword("password");
    updatedUserData.setFirstName("firstName");
    updatedUserData.setLastName("lastName");
    updatedUserData.setEmail("email");
    updatedUserData.setEmail("email");
    updatedUserData.setExecutor(true);

    // repository method and objective of test
    Mockito.when(userServiceImplMock.updateUser(Mockito.any(UserDTO.class), Mockito.anyLong()))
        .thenReturn(updatedUserData);

    // what to be checked or verified
    UserDTO result = userServiceImplMock.updateUser(new UserDTO(), 1L);

    // assert to check
    assertNotNull(result);
    assertEquals(updatedUserData, result);
  }

  @Test
  public void testDeleteUser() {
    // specify the user ID to be deleted
    Long userId = 1L;

    // call the deleteUser method
    userServiceImplMock.deleteUser(userId);
  }
}
