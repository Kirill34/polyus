package controller;

import model.Request;
import model.User;
import model.VehicleExemplar;
import model.VehicleOrder;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import repo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/request")
public class RequestController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private RequestRepository requestRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleTypeRepository vehicleTypeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleModelRepository vehicleModelRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleExemplarRepository vehicleExemplarRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private OrderRepository orderRepository;


    @GetMapping("/ofDriver/{driverId}")
    public List<Request> getDriverRequests(@PathVariable Long driverId)
    {
        return requestRepository.findAllByDriverId(driverId);
    }

    @GetMapping("/ofManager/{managerId}/active")
    public List<Request> getManagerActiveRequests(@PathVariable Long managerId)
    {
        return requestRepository.findAllByVehicleExemplarManagerIdAndStatus(managerId, Request.Status.IN_PROGRESS);
    }

    @GetMapping("/ofManager/{managerId}/")
    public List<Request> getManagerRequests(@PathVariable Long managerId)
    {
        return requestRepository.findAllByVehicleExemplarManagerId(managerId);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<Request> getCustomerActiveRequests(@PathVariable Long customerId)
    {
        return requestRepository.findAllByCustomerIdAndStatus(customerId, Request.Status.IN_PROGRESS);
    }

    @PostMapping("/{requestId}/accept")
    public boolean accept(Long driverId, @PathVariable Long requestId)
    {
        Request request = requestRepository.findById(requestId).get();
        if (request.getDriver().getId() != driverId)
            return false;
        request.setStatus(Request.Status.ACCEPTED);
        requestRepository.save(request);
        assert request.getOrder() != null;
        VehicleOrder order = request.getOrder();
        order.setStatus(VehicleOrder.Status.ACCEPTED);
        orderRepository.save(order);
        return true;
    }

    @PostMapping("/{requestId}/decline")
    public boolean decline(Long driverId, @PathVariable Long requestId)
    {
        Request request = requestRepository.findById(requestId).get();
        if (request.getDriver().getId() != driverId)
            return false;
        request.setStatus(Request.Status.DECLINED);
        requestRepository.save(request);
        return true;
    }

    @PostMapping("/{requestId}/start")
    public boolean start(Long driverId, @PathVariable Long requestId, @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime dateTime)
    {
        List<Request> driverRequests = requestRepository.findAllByDriverId(driverId);
        Request request = requestRepository.findById(requestId).get();//findById(requestId).get();

        //Request request = requestRepository.findAllByDriverId(driverId).get(0);

        if (!Objects.equals(request.getDriver().getId(), driverId))
            return false;
        request.setStatus(Request.Status.IN_PROGRESS);
        request.setRealStartDate(dateTime);
        request.getVehicleExemplar().setStatus(VehicleExemplar.Status.BUSY);
        requestRepository.save(request);
        return true;
    }

    @PostMapping("/{requestId}/finish")
    public boolean finish(Long driverId, @PathVariable Long requestId, @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime dateTime)
    {
        List<Request> driverRequests = requestRepository.findAllByDriverId(driverId);
        Request request = requestRepository.findById(requestId).get();
        if (request.getDriver().getId() != driverId)
            return false;
        request.setStatus(Request.Status.FINISHED);
        request.setRealFinishDateTime(dateTime);
        request.getVehicleExemplar().setStatus(VehicleExemplar.Status.FREE);
        requestRepository.save(request);
        return true;
    }

    @PostMapping("/{requestId}/sendCoordinates")
    public boolean sendCoordinates(Long driverId, @PathVariable Long requestId, double latitude, double longitude)
    {
        List<Request> driverRequests = requestRepository.findAllByDriverId(driverId);
        Request request = requestRepository.findById(requestId).get();
        if (request.getDriver().getId() != driverId)
            return false;
        VehicleExemplar exemplar = request.getVehicleExemplar();
        exemplar.setLatitude(latitude);
        exemplar.setLongitude(longitude);
        vehicleExemplarRepository.save(exemplar);
        return true;
    }


    @PostMapping("/addSimpleRequest")
    public Long addSimpleRequest(@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime startDateTime, @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime finishDateTime, Long driverId, Long customerId, Long vehicleExemplarId)
    {
        User driver = userRepository.findById(driverId).get();
        VehicleExemplar vehicleExemplar = vehicleExemplarRepository.findById(vehicleExemplarId).get();
        User customer = userRepository.findById(customerId).get();
        Request request = new Request(driver,customer,vehicleExemplar,startDateTime,finishDateTime,null);
        requestRepository.save(request);
        return request.getId();
    }

    @GetMapping("/forVehicle/{vehicleId}/forMonth")
    public List<Request> getForMonth(@PathVariable Long vehicleId)
    {
        VehicleExemplar exemplar = vehicleExemplarRepository.findById(vehicleId).get();
        return requestRepository.findAllByVehicleExemplarIdAndStartDateTimeBeforeAndFinishDateTimeAfter(vehicleId, LocalDateTime.now().plusDays(30), LocalDateTime.now());
    }

    @GetMapping("/forVehicle/{vehicleId}/forDay")
    public List<Request> getForDay(@PathVariable Long vehicleId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDate date)
    {
        LocalDateTime startOfDay = LocalDateTime.now().minusHours(LocalDateTime.now().getHour()).minusMinutes(LocalDateTime.now().getMinute());
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59);
        return requestRepository.findAllByVehicleExemplarIdAndStartDateTimeBeforeAndFinishDateTimeAfter(vehicleId, endOfDay, startOfDay);
    }

    @GetMapping("/forVehicle/{vehicleId}/byDayLoad")
    public HashMap<LocalDate,Integer> getByDateLoad(@PathVariable Long vehicleId)
    {
        List<Request> requests = getForMonth(vehicleId);
        HashMap<LocalDate,Integer> load = new HashMap<>();
        for (int i=0; i<31; i++)
            load.put(LocalDateTime.now().plusDays(i).toLocalDate(), 0);

        for (Request r: requests)
        {
            int diff = 0;
            r.getStartDateTime().getNano();
            System.out.println("Diff: "+r.getStartDateTime());

            if (r.getStartDateTime().toLocalDate().equals(r.getFinishDateTime().toLocalDate()) )
            {
                int finishHour = r.getFinishDateTime().getHour();
                int startHour = r.getStartDateTime().getHour();
                int duration = finishHour-startHour;
                load.replace(r.getStartDateTime().toLocalDate(), load.get(r.getStartDateTime().toLocalDate()) + duration );
            }

            if (r.getStartDateTime().toLocalDate().equals(r.getFinishDateTime().toLocalDate().minusDays(1)))
            {
                int finishHour = r.getFinishDateTime().getHour();
                int startHour = r.getStartDateTime().getHour();
                int duration = finishHour+(24-startHour);
                load.replace(r.getStartDateTime().toLocalDate(), load.get(r.getStartDateTime().toLocalDate()) + duration );
            }

        }

        return load;
    }

    @GetMapping("/forVehicle/{vehicleId}/byHourLoad")
    public HashMap<Integer,Integer> getByHourLoad(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDate date, @PathVariable Long vehicleId)
    {
        HashMap<Integer,Integer> load = new HashMap<>();
        List<Request> requests = getForDay(vehicleId,date);

        for (int i=0; i<24; i++)
            load.put(i,0);

        for (Request r: requests)
        {
            if (r.getStartDateTime().toLocalDate().equals(date) && r.getFinishDateTime().toLocalDate().equals(date))
            {
                for (int h = r.getStartDateTime().getHour(); h<= r.getFinishDateTime().getHour(); h++)
                    load.replace(h, load.get(h)+60);
            }
        }

        return load;
    }

}
