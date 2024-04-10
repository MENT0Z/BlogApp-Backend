package com.nphck.demo.services.implem;

import com.nphck.demo.exceptions.ResourceNotFoundException;
import com.nphck.demo.model_entities.User;
import com.nphck.demo.payloads.UserDTO;
import com.nphck.demo.repositories.Userepo;
import com.nphck.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {

   // @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private Userepo userepo;

    @Autowired
    public ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO user) {
        User user1 = this.UserDtoTouser(user);
        User saveduser = this.userepo.save(user1);
        return this.UserToUserDTO(saveduser);
    }

    @Override
    public UserDTO updateUser(UserDTO userdto, Integer uid) {
        User user = this.userepo.findById(uid).orElseThrow(()-> new ResourceNotFoundException("users"," id ",uid));
       user.setName(userdto.getName());
       user.setEmail(userdto.getEmail());
       user.setPassword(userdto.getPassword());
       user.setAbout(userdto.getAbout());
       User updateduser = this.userepo.save(user);
        return this.UserToUserDTO(updateduser);
    }

    @Override
    public UserDTO getUserById(Integer uid) {
        User user = this.userepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("user"," id ",uid));
        return this.UserToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
    List<User>users = this.userepo.findAll();
    List<UserDTO>toreturn = users.stream().map(user -> this.UserToUserDTO(user)).collect(Collectors.toList());
    return toreturn;
    }

    @Override
    public void deleteUser(Integer Uid) {
    User user = this.userepo.findById(Uid).orElseThrow(()->new ResourceNotFoundException("users"," id ",Uid));
    this.userepo.delete(user);
    }

    @Override
    public void deleteAllUsers() {
        this.userepo.deleteAll();
    }

    private User UserDtoTouser(UserDTO userdto)
    {
//       User user1 = new User();
//       user1.setId(userdto.getId());
//       user1.setName(userdto.getName());
//       user1.setEmail(userdto.getEmail());
//       user1.setPassword(userdto.getPassword());
//       user1.setAbout(userdto.getAbout());
//       return user1;

        // we can use the same concept using model mapper dependency which we added
        User userr = this.modelMapper.map(userdto,User.class);
        return userr;
    }
    private UserDTO UserToUserDTO(User user){
//        UserDTO userDTO=new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setAbout(user.getAbout());
//        return userDTO;
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
        return userDTO;
    }
}
