package com.trungtamjava.dao;


import com.trungtamjava.entity.Department;
import com.trungtamjava.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TicketRepsitory extends JpaRepository<Ticket,Long> {

}
