package org.eventmanager.eventmanager.Service;

import jakarta.transaction.Transactional;
import org.eventmanager.eventmanager.Data.model.Attendees;
import org.eventmanager.eventmanager.Data.model.Category;
import org.eventmanager.eventmanager.Data.model.Event;
import org.eventmanager.eventmanager.Data.model.Reservation;
import org.eventmanager.eventmanager.Data.repository.ReservationRepo;
import org.eventmanager.eventmanager.Utils.RequestMapper;
import org.eventmanager.eventmanager.Utils.ResponseMapper;
import org.eventmanager.eventmanager.dtos.request.CancelReservationRequest;
import org.eventmanager.eventmanager.dtos.request.FindReservationRequest;
import org.eventmanager.eventmanager.dtos.request.MakeReservationRequest;
import org.eventmanager.eventmanager.dtos.response.GeneralResponse;
import org.eventmanager.eventmanager.dtos.response.MakeReservationRsponse;
import org.eventmanager.eventmanager.exception.InvalidCategoryException;
import org.eventmanager.eventmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ReservationServiceImpli implements ReservationService{
    @Autowired
    AttendeesService attendeesService;
    @Autowired
    EventService eventService;

    @Autowired AttendeesService service;
    RequestMapper requestMapper = new RequestMapper();
    ResponseMapper responseMapper = new ResponseMapper();
    @Autowired
    NotificationService notificationService;
    @Autowired
    ReservationRepo reservationRepo;
    @Override
    @Transactional
    public MakeReservationRsponse makeReservation(MakeReservationRequest makeReservationRequest) throws InvalidCategoryException, UserNotFoundException {
        Event event = eventService.findEvent(makeReservationRequest).getEvent();
        Long ticketSize = event.getAvailableTicket();
        if (ticketSize>0){
            String ticketMade = UUID.randomUUID().toString().toUpperCase();
            List<String> list = event.getRegisteredTicket();
            list.add(ticketMade);
            ticketSize--;
            event.setAvailableAttendees(ticketSize);
            event.setRegisteredTicket(list);

            Reservation reservation = new Reservation();
            reservation.setAttendeeId(attendeesService.findAttendees(makeReservationRequest.getEmail()));
            reservation.setEventDate(event.getEventDate());
            reservation.setTicket(ticketMade);
            reservation.setCategory(event.getCategory());
            reservation.setDescription(event.getDescription());
            reservation.setEventName(event.getName());

            Attendees user = service.findAttendees(makeReservationRequest.getEmail());
            notificationService.checkAndSendNotificationToAttendeesForAlmostDueBookedEvent(user.getReservationMade());
            notificationService.sendNotificationToAttendeesForBookedEvent(reservation);

            return responseMapper.reservationMap(reservationRepo.save(reservation),user.getReservationMade());
        }
        throw new RuntimeException("Ticket is not available");

    }

    @Override
    public GeneralResponse cancelReservation(CancelReservationRequest request) throws InvalidCategoryException, UserNotFoundException {
        Optional<Reservation> reservation = reservationRepo.findReservationByEventNameAndCategory(request.getName(), request.getCategory());

        if (reservation.isPresent()){
            reservationRepo.deleteById(reservation.get().getId());
            notificationService.sendCancelReservationNotification();
            return new GeneralResponse("Reservation deleted success");
        }else {
            throw new RuntimeException("reservation not found ");
        }
    }

    @Override
    public Reservation viewReservation(FindReservationRequest findReservationRequest) throws UserNotFoundException {
      Optional<Reservation> reservations = reservationRepo.findReservationByEventNameAndCategoryAndAttendeeId(findReservationRequest.getName(),findReservationRequest.getCategory(), attendeesService.findAttendees(findReservationRequest.getEmail()));
      if (reservations.isPresent()){
          return reservations.get();
      }
      throw new RuntimeException("Reservation not found");
    }

    @Override
    public List<Reservation> viewAllReservation(Attendees attendees) {
        Optional<List<Reservation>> reservations = reservationRepo.findAllByAttendeeId(attendees);
        return reservations.get();
    }

    @Override
    public List<Reservation> findAll(){
        return reservationRepo.findAll();
    }
}
