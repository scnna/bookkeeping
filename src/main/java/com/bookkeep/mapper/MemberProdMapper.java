package com.bookkeep.mapper;

import com.bookkeep.DTO.WorkRordDTO;
import com.bookkeep.domain.MemberProd;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberProdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberProd record);

    int insertSelective(MemberProd record);

    MemberProd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberProd record);

    int updateByPrimaryKey(MemberProd record);

    List findListByWorkRecord(WorkRordDTO record);

    List getList(WorkRordDTO record);
}