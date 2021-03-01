package com.lizhuopeng.controller;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.PageBean;
import com.lizhuopeng.model.MiniPicOrder;
import com.lizhuopeng.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("user/getOrderList")
    public DataResult getOrderList(@RequestParam(required = false, defaultValue = "0") int offset,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize){
        User login_user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=login_user.getUsername();
        PageBean<MiniPicOrder> miniPicOrderPageBean = orderService.getOrderPageList(username, offset, pageSize);
        return DataResult.success(miniPicOrderPageBean);
    }
}
