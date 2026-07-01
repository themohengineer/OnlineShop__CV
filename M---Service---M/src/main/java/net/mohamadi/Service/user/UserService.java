package net.mohamadi.Service.user;


import net.mohamadi.Data_Access.entity.user.Customer;
import net.mohamadi.Data_Access.entity.user.Role;
import net.mohamadi.Data_Access.entity.user.User;
import net.mohamadi.Data_Access.repository.user.CustomerRepository;
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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final CustomerRepository customerRepository;

    private final JwtUtil jwtUtil;

    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PermissionRepository permissionRepository,
                       CustomerRepository customerRepository,
                       JwtUtil jwtUtil,
                       ModelMapper mapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.customerRepository = customerRepository;
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


    public UserDto readUserByUserName(String username) throws Exception {
        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }


    public UserDto read(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }

    public UserDto create(UserDto dto) throws Exception {
        checkFullValidation(dto);

        Optional<User> oldUser = userRepository.findFirstByUsername(dto.getUsername());

        if (oldUser.isPresent())
            throw new ValidationException("کاربری با این نام ثبت نام کرده" +
                    " لطفا ابتدا وارد شوید!");




        Customer customer = customerRepository
                .save(mapper
                        .map(dto.getCustomer(), Customer.class)
                );

        User user = mapper.map(dto, User.class);
        user.setCustomer(customer);
        user.setPassword(HashUtil.toSHA1(user.getPassword()));
        user.setRegisterDate(LocalDateTime.now());
        user.setEnable(true);
        Optional<Role> userRole = roleRepository.findFirstByNameEqualsIgnoreCase("user");
        if (userRole.isPresent()) {
            HashSet<Role> roles = new HashSet<>();
            roles.add(userRole.get());
            user.setRoles(roles);
        }
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);

    }

    private static void checkFullValidation(UserDto dto) throws ValidationException {
        if (dto.getCustomer() == null)
            throw new ValidationException("Please enter a customer Info.");
        if (dto.getCustomer().getFirstName() == null || dto.getCustomer().getFirstName().isEmpty())
            throw new ValidationException("Firstname is null or empty");
        if (dto.getCustomer().getLastName() == null || dto.getCustomer().getLastName().isEmpty())
            throw new ValidationException("Lastname is null or empty");
        if (dto.getUsername() == null || dto.getUsername().isEmpty())
            throw new ValidationException("Username is null or empty");
        if (dto.getPassword() == null || dto.getPassword().isEmpty())
            throw new ValidationException("Password is null or empty");
        if (dto.getEmail() == null || dto.getEmail().isEmpty())
            throw new ValidationException("Email is null or empty");
        if (dto.getMobile() == null || dto.getMobile().isEmpty())
            throw new ValidationException("Mobile is null or empty");
        if (dto.getCustomer().getTel() == null || dto.getCustomer().getTel().isEmpty())
            throw new ValidationException("Tel is null or empty");
        if (dto.getCustomer().getAddress() == null || dto.getCustomer().getAddress().isEmpty())
            throw new ValidationException("Address is null or empty");
        if (dto.getCustomer().getPostalCode() == null || dto.getCustomer().getPostalCode().isEmpty())
            throw new ValidationException("PostalCode is null or empty");
    }


}
