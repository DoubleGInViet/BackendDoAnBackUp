package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.responses.payment.VnPayResponse;
import com.duy.BackendDoAn.services.BookingRoomService;
import com.duy.BackendDoAn.services.BookingTicketService;
import com.duy.BackendDoAn.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final SimpMessagingTemplate messagingTemplate;
    private final PaymentService paymentService;
    private final BookingRoomService bookingRoomService;
    private final BookingTicketService bookingTicketService;
    @GetMapping("/vn-pay")
    public ResponseEntity<VnPayResponse> pay(HttpServletRequest request) {
        VnPayResponse response = paymentService.createVnPayPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vn-pay-callback")
    public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String status = request.getParameter("vnp_ResponseCode");
        String txnRef = request.getParameter("vnp_TxnRef");
        String type = request.getParameter("vnp_OrderInfo");

        if (status.equals("00") && type.equals("room")) {
            bookingRoomService.setStatusAfterPayment(txnRef);
            messagingTemplate.convertAndSend("/topic/payment-status", "Payment successfully");
        } else if (status.equals("00") && type.equals("ticket")) {
            bookingTicketService.setBookingTicketStatusPayment(txnRef);
            messagingTemplate.convertAndSend("/topic/payment-status", "Payment successfully");
        }
    }
}
