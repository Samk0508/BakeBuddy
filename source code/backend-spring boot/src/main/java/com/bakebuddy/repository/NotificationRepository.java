package com.bakebuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {



}
