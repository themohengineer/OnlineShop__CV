package net.mohamadi.Service.user;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Common.utils.HashUtil;
import net.mohamadi.Data_Access.entity.user.Customer;
import net.mohamadi.Data_Access.entity.user.Role;
import net.mohamadi.Data_Access.entity.user.User;
import net.mohamadi.Data_Access.repository.user.CustomerRepository;
import net.mohamadi.Data_Access.repository.user.RoleRepository;
import net.mohamadi.Data_Access.repository.user.UserRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.user.LimitedUserDto;
import net.mohamadi.dto.user.LoginDto;
import net.mohamadi.dto.user.UserDto;
import net.mohamadi.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements CRUDService<UserDto>, HasValidation<UserDto> {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;

    private final JwtUtil jwtUtil;

    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repository,
                       RoleRepository roleRepository,
                       CustomerRepository customerRepository,
                       JwtUtil jwtUtil,
                       ModelMapper mapper
    ) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }


    //متد زیر برای DTO داشتن از سمت کلاینت به سرور است!
    // یعنی برعکس قبلی ها که از سرور به کلاینت بود.


    public LimitedUserDto login(LoginDto dto) throws Exception {

        String password = HashUtil.toSHA1(dto.getPassword());

        User user = repository
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
        User user = repository.findFirstByUsername(username)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }


    public UserDto read(Long id) throws Exception {
        User user = repository.findById(id)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto create(UserDto dto) throws ValidationException {
        checkValidation(dto);

        Optional<User> oldUser = repository.findFirstByUsername(dto.getUsername());

        if (oldUser.isPresent())
            throw new ValidationException("کاربری با این نام ثبت نام کرده" +
                    " لطفا ابتدا وارد شوید!");


        Customer customer = customerRepository
                .save(mapper
                        .map(dto.getCustomer(), Customer.class)
                );

        User user = mapper.map(dto, User.class);
        user.setId(null);
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
        User savedUser = repository.save(user);
        return mapper.map(savedUser, UserDto.class);

    }

    @Override
    public Page<UserDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, UserDto.class)
                );
    }


    @Override
    public UserDto update(UserDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        User oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setMobile(Optional.ofNullable(dto.getMobile()).orElse(oldData.getMobile()));
        oldData.setEmail(Optional.ofNullable(dto.getEmail()).orElse(oldData.getEmail()));
        oldData.setEnable(Optional.ofNullable(dto.getEnable()).orElse(oldData.getEnable()));
        if (dto.getCustomer() != null)
            oldData.setCustomer(
                    Optional.ofNullable(mapper.map(dto.getCustomer(), Customer.class))
                            .orElse(oldData.getCustomer())
            );
        repository.save(oldData);
        return mapper.map(oldData, UserDto.class);

    }

    @Override
    public void checkValidation(UserDto dto) throws ValidationException {
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

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
