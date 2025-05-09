package com.example.electricity_management_system.service;

import com.example.electricity_management_system.dto.UserRegisterDto;
import com.example.electricity_management_system.dto.UserUpdateDto;
import com.example.electricity_management_system.model.UserModel;
import com.example.electricity_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserModel createUser(UserRegisterDto user){
        UserModel userModel=new UserModel(user);
        userModel.password=passwordEncoder.encode(userModel.password);
        return  userRepository.save(userModel);
    }
    public List<UserModel> getAllUsers(){
        return  userRepository.findAll();
    }
    public UserModel getUserById(Long id){
        Optional<UserModel> user= userRepository.findById(id);
        return user.orElse(null);
    }
    public String deleteUserById(Long id){
        Optional<UserModel> user=userRepository.findById(id);
        if(user.isPresent()){
            return "User Deleted successfully";
        }
        else{
            return "User not found";
        }
    }
    public UserModel updateUser(Long id,UserUpdateDto userRequest){
        Optional<UserModel>optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            UserModel user=optionalUser.get();
            if(!userRequest.email.isEmpty()){
                user.email=userRequest.email;
            }
            if(!userRequest.names.isEmpty()){
                user.email=userRequest.email;
            }
            if(!userRequest.phoneNumber.isEmpty()){
                user.email=userRequest.email;
            }
            if(!userRequest.nationalID.isEmpty()){
                user.email=userRequest.email;
            }
            return userRepository.save(user);
        }
        else{
            return null;
        }
    }



}
