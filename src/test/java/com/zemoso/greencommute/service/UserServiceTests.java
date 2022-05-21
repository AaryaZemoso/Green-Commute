package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.User;
import com.zemoso.greencommute.repository.JobSkillsRepository;
import com.zemoso.greencommute.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    JobSkillsRepository jobSkillsRepository;

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

        Mockito.doNothing().when(jobSkillsRepository).saveByUserIdAndSkillId(1, 1);
        userService.addSaveJob(1, 1);
        Mockito.verify(jobSkillsRepository).saveByUserIdAndSkillId(1, 1);
    }

    @Test
    void removeSaveJobTest() {

        Mockito.doNothing().when(jobSkillsRepository).deleteByUserIdAndSkillId(1, 1);
        userService.removeSaveJob(1, 1);
        Mockito.verify(jobSkillsRepository).deleteByUserIdAndSkillId(1, 1);
    }
}
