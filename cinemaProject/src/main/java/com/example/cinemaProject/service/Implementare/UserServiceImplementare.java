package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.User;
import com.example.cinemaProject.repository.UserRepository;
import com.example.cinemaProject.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImplementare implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImplementare(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findByAge(int nr) {
        return userRepository.findAllByAge(nr);
    }

    @Override
    public User updateUser(User user, int newAge) {
        User updateUser = userRepository.findById(user.getId()).get();
        updateUser.setAge(newAge);
        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
