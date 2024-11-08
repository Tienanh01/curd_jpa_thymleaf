package com.trungtamjava.controller;

import com.trungtamjava.dao.DepartmentRepsitory;
import com.trungtamjava.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping({"/department","/"})
public class DeparmentController {
    @Autowired
    private  DepartmentRepsitory departmentRepsitory;


    @GetMapping("/view")
    public String view(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                       @RequestParam(value = "size", required = false, defaultValue = "3") int size){
        Page<Department> list = (Page) departmentRepsitory.findAll(PageRequest.of(page, size));
        model.addAttribute("departments",list);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",list.getTotalPages());
        return "department/view";
    }
    @GetMapping("/add")
    public String create(){

        return "department/add";
    }
    @PostMapping("/add")
    public String create(@RequestParam("date_At") @DateTimeFormat(pattern = "YYYY-MM-dd")  Date date,@ModelAttribute Department d){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
//        sdf.format(d.getDate());

//        System.out.println(d.getDate());
        d.setDate(date);
        departmentRepsitory.save(d);
        return "redirect:/department/view";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("id") long id , Model model){
        model.addAttribute("departments",departmentRepsitory.findById(id).orElse(null));
        return "department/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id ){
        departmentRepsitory.deleteById(id);
        return "redirect:/department/view";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id , Model model){
        Department department = departmentRepsitory.getById(id);
        model.addAttribute("department", department );
        return "department/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute  Department d,@RequestParam("date_At") @DateTimeFormat(pattern = "YYYY-MM-dd")  Date date){
        d.setDate(date);
        departmentRepsitory.save(d);
        return "redirect:/department/view";
    }
}
