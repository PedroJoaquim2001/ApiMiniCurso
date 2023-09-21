package service;

import jakarta.transaction.Transactional;
import model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> findById(UUID id){
        return userRepository.findById (id);
    }

    public Page<UserModel> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Transactional
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    @Transactional
    public void delete(UserModel userModel){
        userRepository.delete(userModel);
    }
}
