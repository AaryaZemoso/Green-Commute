package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.User;
import com.zemoso.greencommute.exception.DataNotFoundException;
import com.zemoso.greencommute.repository.JobSkillsRepository;
import com.zemoso.greencommute.repository.SavedJobRepository;
import com.zemoso.greencommute.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    SavedJobRepository savedJobRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Test
    void getUserByIdTest() {

        User user = new User(1, "Aarya", Set.of());
        Mockito.when(userRepository.getById(1)).thenReturn(user);

        User fetchedUser = userService.getUserById(1);

        Assertions.assertThat(fetchedUser.getId()).isSameAs(user.getId());
    }

    @Test
    void addSaveJobTest() {

        Mockito.when(savedJobRepository.save(Mockito.any())).thenReturn(Mockito.any());
        userService.addSaveJob(1, 1);
        Mockito.verify(savedJobRepository).save(Mockito.any());
    }

    @Test
    void removeSaveJobTest() {

        Mockito.doNothing().when(savedJobRepository).delete(Mockito.any());
        userService.removeSaveJob(1, 1);
        Mockito.verify(savedJobRepository).delete(Mockito.any());
    }

    @Test
    void removeSaveJobTestException() {

        Mockito.when(savedJobRepository.getById(Mockito.any())).thenThrow(new EntityNotFoundException());

        try {
            userService.removeSaveJob(1, 1);
            Assertions.fail("It should throw DataNotFound Exception");
        } catch(DataNotFoundException de) {

        }
    }
}
