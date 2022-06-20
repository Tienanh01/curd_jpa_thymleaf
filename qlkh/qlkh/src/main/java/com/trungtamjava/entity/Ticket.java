package com.trungtamjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String phone_number;
    private String text_customer;
    @Temporal(TemporalType.DATE)
    private Date tg_tiepnhan;
    private String content;
    private Boolean status;
    @Temporal(TemporalType.DATE)
    private Date tg_xuly;
    @ManyToOne
    @JoinColumn(name = "pb_id")
    private Department department;
}
