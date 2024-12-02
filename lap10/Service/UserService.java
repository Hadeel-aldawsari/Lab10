package com.example.lap10.Service;

import com.example.lap10.Model.User;
import com.example.lap10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<User>getAll(){
      return userRepository.findAll();
    }

    public void add(User user){
        userRepository.save(user);
    }

    public Boolean update(Integer id,User user){
        User originalUser=userRepository.getById(id);
        if(originalUser==null)return  false;

        originalUser.setName(user.getName());
        originalUser.setAge(user.getAge());
        originalUser.setRole(user.getRole());
        originalUser.setEmail(user.getEmail());
        originalUser.setPassword(user.getPassword());
        userRepository.save(originalUser);
        return true;
    }

    public Boolean delete(Integer id){
        User user=userRepository.getById(id);
        if(user==null)return false;

        userRepository.delete(user);
        return true;
    }

    public Boolean isUserExist(Integer id){
        for (User u:userRepository.findAll()){
            if(u.getId().equals(id))
                return true;
        }
        return false;
    }



}
