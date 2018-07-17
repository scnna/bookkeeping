package com.bookkeep.mapper;


import com.bookkeep.DTO.ProdOutDTO;
import com.bookkeep.domain.ProdOut;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProdOut record);

    int insertSelective(ProdOut record);

    ProdOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProdOut record);

    int updateByPrimaryKey(ProdOut record);

    List findListByProdOut(ProdOutDTO record);
}