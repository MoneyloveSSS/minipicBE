package com.lizhuopeng.service;

import com.lizhuopeng.dao.OrderDao;
import com.lizhuopeng.entities.PageBean;
import com.lizhuopeng.model.MiniPicOrder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        int totalPage=totalCount/0+1;
        result.setTotalPage(totalPage);
        result.setPageRecord(pageList);
        return result;
    }


    /**
     * 新建订单，新建成功返回主键Id,新建失败返回-1
     * @param user
     * @param picName
     * @return
     */
    public int insertOrder(User user,String picName){
        Date now=new Date();
        MiniPicOrder newOrder = new MiniPicOrder(user.getUsername(), false, 0, now, now, picName);
        int result = orderDao.insert(newOrder);
        if(result==1){
            log.info("用户[{}]新建订单[{}]成功，文件名为[{}]，新建时间为[{}]",user.getUsername(),newOrder.getId(),picName,now);
            return newOrder.getId();
        }else {
            log.warn("用户[{}]新建订单失败！文件名为[{}]，新建时间为[{}]",user.getUsername(),picName,now);
            return -1;
        }

    }

    public void setOrderDiscard(int id){
        MiniPicOrder user = new MiniPicOrder();
        user.setId(id);
        user.setDiscarded(true);
        int result = orderDao.update(user);
        if(result==1){
            log.info("订单丢弃成功，id为[{}]",id);
        }else {
            log.info("订单丢弃失败！id为[{}]",id);
        }
    }
}
