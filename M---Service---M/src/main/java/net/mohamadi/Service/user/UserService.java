package net.mohamadi.Service.user;





import net.mohamadi.Data_Access.entity.user.User;
import net.mohamadi.Data_Access.repository.user.PermissionRepository;
import net.mohamadi.Data_Access.repository.user.RoleRepository;
import net.mohamadi.Data_Access.repository.user.UserRepository;

import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Common.utils.HashUtil;
import net.mohamadi.dto.user.LimitedUserDto;
import net.mohamadi.dto.user.LoginDto;
import net.mohamadi.dto.user.UserDto;

import net.mohamadi.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private final JwtUtil jwtUtil;

    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PermissionRepository permissionRepository, JwtUtil jwtUtil, ModelMapper mapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }


    //متد زیر برای DTO داشتن از سمت کلاینت به سرور است!
    // یعنی برعکس قبلی ها که از سرور به کلاینت بود.


    public LimitedUserDto login(LoginDto dto) throws Exception {

        String password = HashUtil.toSHA1(dto.getPassword());

        User user = userRepository
                .findFirstByUsernameEqualsIgnoreCaseAndPassword(
                        dto.getUsername(),
                        password
                )
                .orElseThrow(NotFoundExceptionss::new);

        if (!user.getEnable()) {
            throw new ValidationException("Your user is disable. contact with support.");
        }

        LimitedUserDto result = mapper.map(user, LimitedUserDto.class);
        result.setToken(jwtUtil.generateToken(result.getUsername()));

        return result;
    }


    public UserDto getUserByUserName(String username) throws Exception {
        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }


    public UserDto getUserById(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }


}
