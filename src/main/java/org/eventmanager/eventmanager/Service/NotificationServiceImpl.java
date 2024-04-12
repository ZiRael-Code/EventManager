package org.eventmanager.eventmanager.Service;

public interface NotificationService {
    void sendNotificationToAllUsers();
    void deleteNotificationAfterSomeDuration();
    void sendNotificationToAttendees();
    void sendNotificationToEventMakers();

    void deleteNotification(Long id);
}
