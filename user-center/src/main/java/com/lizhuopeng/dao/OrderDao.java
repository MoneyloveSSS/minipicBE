package com.lizhuopeng.dao;

import com.lizhuopeng.model.MiniPicOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDao {

    /**
     * [新增]
     * @date 2021/02/28
     **/
    int insert(MiniPicOrder miniPicOrder);

    /**
     * [刪除]
     * @date 2021/02/28
     **/
    int delete(int id);

    /**
     * [更新]
     * @date 2021/02/28
     **/
    int update(MiniPicOrder miniPicOrder);

    /**
     * [查询] 根据主键 id 查询
     * @date 2021/02/28
     **/
    MiniPicOrder load(int id);

    /**
     * [查询] 根据用户名分页查询
     * @date 2021/02/28
     **/
    List<MiniPicOrder> pageList(@Param("username") String username, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * [查询] 根据用户名分页查询 count
     * @date 2021/02/28
     **/
    int pageListCount(@Param("username") String username);

}
