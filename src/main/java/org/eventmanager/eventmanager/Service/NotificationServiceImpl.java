package org.eventmanager.eventmanager.Service;

import org.eventmanager.eventmanager.Data.model.*;
import org.eventmanager.eventmanager.Data.repository.NotificationRepo;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private UserServiceApp userServiceApp;
    @Autowired
    private EventMakerService eventMakerService;
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    private AttendeesService attendeesService;
    @Override
    public void sendNotificationToAllUserIfReservationDatePassWitoutUserCompleting(List<Reservation> reservation) {
        for(Reservation r : reservation){
            if (r.getEventDate().isAfter(LocalDate.now())){
            sendNotificationHelper(r.getAttendeeId().getUser().getEmail()
                , String.format("Hello %s",
                        r.getAttendeeId().getUser().getName()), String.format("Your reservation date for %s is passed kindly check the complete box if you attended", r.getEventName()));
    }
        }
    }

    @Override
    public void deletePassedReservationAfterSomeDuration() {
//        if ()
    }

    public void sendNotificationToNewUser(String email) throws UserNotFoundException {
        List<Notification> notifications = userServiceApp.findByEmail(email).getNotificationList();
        User user = userServiceApp.findByEmail(email);
        Notification notification = new Notification();
        notification.setEmail(email);
        notification.setTitle("Welcome to Event Manager");
        notification.setDescription("Hello "+user.getName()+" 😀,\nWelcome to event manager, Here we help you manage all even so you don't have to worry");
        notificationRepo.save(notification);
    }

    @Override
    public void sendCancelReservationNotification() {
    }

    @Override
    public void sendNoMoreTicketNotification(EventMaker maker) {

    }

    public Notification sendNotificationToAttendeesForBookedEvent(Reservation reservation) throws UserNotFoundException {
        Attendees attendees = attendeesService.findAttendees(reservation.getAttendeeId().getUser().getEmail());
        return  sendNotificationHelper(attendees.getUser().getEmail(),
                String.format("Hello %s",
                attendees.getUser().getName()), "You just book a reservation to event with due date of "+reservation.getEventDate());

    }

    public void checkAndSendNotificationToAttendeesForAlmostDueBookedEvent(List<Reservation> booked) {
         for(Reservation r : booked){
            if (r.getEventDate().isBefore(LocalDate.now())){
                sendNotificationHelper(r.getAttendeeId().getUser().getEmail()
                        , String.format("Hello %s",
                        r.getAttendeeId().getUser().getName()), String.format("You are %s months, %s days away from your booked reservation",r.getEventDate().minusMonths(LocalDate.now().getMonth().getValue()), r.getEventDate().minusDays(LocalDate.now().getDayOfMonth())));

            }

        }

    }




    public Notification sendDeleteEventNotification(Event event){
        Notification notification = new Notification();
        notification.setEmail(event.getEventMakerId().getUser().getEmail());
        notification.setTitle("Welcome to Event Manager");
        notification.setDescription("Hello you just deleted "+event.getName()+" \nafter having "+event.getAvailableAttendees()+" available attendees ");
        notificationRepo.save(notification);
    return notification;
    }



    @Override
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }

    @Override
    public List<Notification> getAllNotificationBelongingTo(String email) {
        Optional<List<Notification>> notificationList = notificationRepo.findNotificationByEmail(email);
        if (notificationList.isPresent()){
            return notificationList.get();
        }else {
            throw new RuntimeException("No notification");
        }

    }
    public Notification sendNotificationForEventCreation(Event event){
        return sendNotificationHelper(event.getEventMakerId()
                .getUser().getEmail(), String.format("Hello %s",
                event.getEventMakerId().getUser().getName()), "You just created a new event with due date of "+event.getEventDate());

    }

    private Notification sendNotificationHelper(String email, String title, String description){
        Notification notification = new Notification();
        notification.setEmail(email);
        notification.setTitle(title);
        notification.setDescription(title);
        notificationRepo.save(notification);
        return notification;
    }
}
