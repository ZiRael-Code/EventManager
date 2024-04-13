package org.eventmanager.eventmanager.Data.repository;

import org.eventmanager.eventmanager.Data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    Optional<List<Notification>> findNotificationByEmail(String email);
}
