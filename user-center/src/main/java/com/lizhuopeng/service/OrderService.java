package com.lizhuopeng.service;

import com.lizhuopeng.dao.OrderDao;
import com.lizhuopeng.entities.PageBean;
import com.lizhuopeng.model.MiniPicOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 分页查询订单
     * @param offset
     * @param pageSize
     * @return
     */
    public PageBean<MiniPicOrder> getOrderPageList(String username, int offset, int pageSize) {
        log.info("分页查询用户[{}]订单信息，offset=[{}],pageSize=[{}]",username,offset,pageSize);
        List<MiniPicOrder> pageList = orderDao.pageList(username,offset, pageSize);
        int totalCount = orderDao.pageListCount(username);

        PageBean<MiniPicOrder> result = new PageBean<>(offset,pageSize);
        result.setTotal(totalCount);
        int totalPage=totalCount/pageSize+1;
        result.setTotalPage(totalPage);
        result.setPageRecord(pageList);
        return result;
    }
}
