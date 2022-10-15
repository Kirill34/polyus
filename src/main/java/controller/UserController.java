package controller;

import model.NoticeForManager;
import model.User;
import model.UserToken;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repo.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    UserTokenRepository userTokenRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleTypeRepository vehicleTypeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleModelRepository vehicleModelRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleExemplarRepository vehicleExemplarRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private NoticeRepository noticeRepository;

    @PostMapping("/addDriver")
    public Long addDriver(String email, String phoneNumber, @DateTimeFormat(pattern = "dd.MM.yyyy") Date birthday, String password, String firstName, String secondName)
    {
        User u = new User(email,phoneNumber,birthday,password,firstName,secondName, User.UserType.DRIVER);
        userRepository.save(u);
        return u.getId();
    }


    @PostMapping("/addManager")
    public Long addManager(String email, String phoneNumber, @DateTimeFormat(pattern = "dd.MM.yyyy") Date birthday, String password, String firstName, String secondName)
    {
        User u = new User(email,phoneNumber,birthday,password,firstName,secondName, User.UserType.MANAGER);
        userRepository.save(u);
        return u.getId();
    }

    @PostMapping("/addCustomer")
    public Long addCustomer(String email, String phoneNumber, @DateTimeFormat(pattern = "dd.MM.yyyy") Date birthday, String password, String firstName, String secondName)
    {
        User u = new User(email,phoneNumber,birthday,password,firstName,secondName, User.UserType.CUSTOMER);
        userRepository.save(u);
        return u.getId();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userRepository.findById(id).get();
    }

    @PostMapping("/login")
    public String login(String login, String password)
    {
        User u = userRepository.getUserByEmail(login);
        User u1 = userRepository.getUserByPhoneNumber(login);
        User user = null;
        if (u != null)
            user = u;
        if (u1 != user)
            user = u1;
        if (u == null && u1 == null)
            return "";
        String token = RandomStringUtils.randomAlphanumeric(200);
        UserToken userToken = new UserToken(u, token);
        userTokenRepository.save(userToken);
        return token;
    }

    @GetMapping("/count/driver")
    public long getDriverCount()
    {
        return userRepository.countByType(User.UserType.DRIVER);
    }

    @GetMapping("/count/vehicle/ofManager/{managerId}")
    public long getVehicleCount(@PathVariable Long managerId)
    {
        return vehicleExemplarRepository.countByManagerId(managerId);
    }

    @GetMapping("/free")
    public List<User> getFreeDrivers()
    {
        return userRepository.findFreeDrivers(LocalDateTime.now(), LocalDateTime.of(2022,10,14,23,56,0));
    }

    @GetMapping("/customerPage")
    public ModelAndView getCustomerPage(Model model)
    {
        return new ModelAndView("customer");
    }

    @GetMapping("/managerPage")
    public ModelAndView getManagerPage(Model model)
    {
        return new ModelAndView("manager");
    }

    @GetMapping("/manager/{id}/notices")
    public List<NoticeForManager> getManagerNotices(@PathVariable Long id)
    {
        return noticeRepository.findAllByForManagerIdAndRead(id,false);
    }


}
