package controller;

import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.*;

import java.util.Date;

@RestController
@RequestMapping("/")
public class IndexController {

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



    @GetMapping("/init")
    public void initDB()
    {
        User driver1 = new User("drive1@ya.ru", "79021234567",new Date(1980,12,22) ,"12345678","Иван","Иванов", User.UserType.DRIVER);
        userRepository.save(driver1);



    }
}
