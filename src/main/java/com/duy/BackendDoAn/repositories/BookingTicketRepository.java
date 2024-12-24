package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.BookingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingTicketRepository extends JpaRepository<BookingTicket, String> {
}
