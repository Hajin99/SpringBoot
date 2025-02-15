package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    public final NotificationRepository notificationRepository;

    @GetMapping("/notification")
    String notifications(Model model) {
        List<Notification> notifications = notificationRepository.findAll();
        model.addAttribute("notifications", notifications);

        return "notification.html";
    }
}
