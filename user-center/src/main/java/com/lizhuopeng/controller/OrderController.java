package com.lizhuopeng.controller;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.PageBean;
import com.lizhuopeng.feign.JpegoptimProcessingDeskFeign;
import com.lizhuopeng.model.MiniPicOrder;
import com.lizhuopeng.model.TestModel;
import com.lizhuopeng.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Resource
    private JpegoptimProcessingDeskFeign jpegoptimProcessingDeskFeign;

    /**
     * RPC压缩图片，生成订单信息
     * @param picture
     * @return
     * @throws IOException
     */
    @PostMapping("user/makeMiniPicOrder")
    public byte[] img(@RequestPart("picture") MultipartFile picture,@RequestParam("compressionRatio") Integer compressionRatio) throws IOException {
        User login_user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = orderService.insertOrder(login_user, picture.getOriginalFilename());
        byte[] img = new byte[0];
        if(id != -1){
            try {
                img = jpegoptimProcessingDeskFeign.img(picture,compressionRatio);
            }catch (Exception e){
                log.warn("调用服务jpegoptim-processing-desk出现错误,用户名[{}],文件名[{}]",login_user.getUsername(),picture.getOriginalFilename());
                orderService.setOrderDiscard(id);
            }
        }
        return img;

    }



    /**
     * 得到用户的订单列表
     * @param offset
     * @param pageSize
     * @return
     */
    @GetMapping("user/getOrderList")
    public DataResult getOrderList(@RequestParam(required = false, defaultValue = "0") int offset,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize){
        User login_user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=login_user.getUsername();
        PageBean<MiniPicOrder> miniPicOrderPageBean = orderService.getOrderPageList(username, offset, pageSize);
        return DataResult.success(miniPicOrderPageBean);
    }

    /**
     * 测试 待删
     * @return
     */
    @GetMapping("user/testFeign")
    public DataResult testFeign(){
        TestModel testModel = jpegoptimProcessingDeskFeign.returnObject();
        return DataResult.success(testModel);
    }
}
