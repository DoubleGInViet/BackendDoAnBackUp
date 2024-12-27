package com.duy.BackendDoAn.repositories;

import com.duy.BackendDoAn.models.BookingTicket;
import com.duy.BackendDoAn.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingTicketRepository extends JpaRepository<BookingTicket, String> {
    Page<BookingTicket> findByUser(User user, Pageable pageable);
}
