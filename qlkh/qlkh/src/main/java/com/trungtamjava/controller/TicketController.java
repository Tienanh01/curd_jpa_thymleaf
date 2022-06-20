package com.trungtamjava.controller;

import com.trungtamjava.dao.DepartmentRepsitory;
import com.trungtamjava.dao.TicketRepsitory;
import com.trungtamjava.entity.Department;
import com.trungtamjava.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket")

public class TicketController {

    @Autowired
    private TicketRepsitory ticketRepsitory;


    @Autowired
    private DepartmentRepsitory departmentRepsitory;

    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("departments",departmentRepsitory.findAll());
        return "ticket/add";
    }
    @PostMapping("/add")
    public String add(@RequestParam("tg_tiepnhan_d") @DateTimeFormat(pattern = "YYYY-MM-dd") Date tg_tiepnhan, @RequestParam("tg_xuly_d") @DateTimeFormat(pattern = "YYYY-MM-dd") Date tg_xuly, Model model, @ModelAttribute Ticket ticket,
                      @RequestParam("department_id") long pb_id){

        ticket.setTg_tiepnhan(tg_tiepnhan);
        ticket.setTg_xuly(tg_xuly);
        Department department =  new Department();
        department.setId(pb_id);
        ticket.setDepartment(department);
        ticketRepsitory.save(ticket);
        return "redirect:/ticket/view";
    }
    @GetMapping("/view")
    public String view(Model model){
        List<Ticket>  tickets = ticketRepsitory.findAll();
        model.addAttribute("list",tickets);
        return "ticket/view";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id , Model model){
        model.addAttribute("ticket",ticketRepsitory.findById(id).orElse(null));

        return "ticket/detail";
    }
        @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        ticketRepsitory.deleteById(id);
        return "redirect:/ticket/view";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id ,Model model ){
        Ticket ticket = ticketRepsitory.getById(id);
        model.addAttribute("ticket",ticket);
        model.addAttribute("departments",departmentRepsitory.findAll());
        return "ticket/edit";
    }
    @PostMapping("/edit")
    public String edit( @ModelAttribute Ticket ticket,@RequestParam("tg_tiepnhan_d") @DateTimeFormat(pattern = "YYYY-MM-dd") Date tg_tiepnhan, @RequestParam("tg_xuly_d") @DateTimeFormat(pattern = "YYYY-MM-dd") Date tg_xuly,
                        @RequestParam("department_id") Long pb_id){

        ticket.setTg_xuly(tg_xuly);
        ticket.setTg_tiepnhan(tg_tiepnhan);
        Department department = new Department();
        department.setId(pb_id);
        ticket.setDepartment(department);

        ticketRepsitory.save(ticket);
        return "redirect:/ticket/view";
    }

}
