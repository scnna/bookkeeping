package com.bookkeep.service;

import com.bookkeep.DTO.WorkRordDTO;
import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.Member;
import com.bookkeep.domain.MemberProd;
import com.bookkeep.domain.ProdCome;
import com.bookkeep.mapper.MemberProdMapper;
import com.bookkeep.mapper.ProdComeMapper;
import com.bookkeep.util.ReturnResult;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品进厂信息Controller
 **/
@Service
public class WordRecordService extends BaseController {

    @Autowired
    MemberProdMapper memberProdMapper;
    @Autowired
    ProdComeMapper comeMapper;

    public ReturnResult insert(MemberProd memberProd, String memberList, HttpSession session) {
        JSONArray jsonArray = JSONArray.fromObject(memberList);//把String转换为json
        List<Member> list = JSONArray.toList(jsonArray, Member.class);//这里的t是Class<T>

//            if (getUserId(session) == null) {
//                return ReturnResult.error("请您先登录");
//            }
//            int userId = (int) getUserId(session);
        if (memberProd == null || list == null || list.size() < 1) {
            return ReturnResult.error("不能为空");
        }
        if (StringUtils.isEmpty(memberProd.getCurrentCount()) || memberProd.getCurrentCount() == 0) {
            return ReturnResult.error("请维护产品进货信息");
        }
        int sumCount = 0;
        for (Member member : list) {
            sumCount += member.getCount();
        }
        int nowNum = memberProd.getCurrentCount();
        if (sumCount > nowNum) {
            return ReturnResult.error("保存失败。本次合计工作量为：" + sumCount + "；不能超过本批次产品剩余量：" + nowNum);
        }

        for (Member member : list) {
            memberProd.setMemberId(member.getId());
            memberProd.setCount(member.getCount());
            //产品剩余数量=产品目前数量-count;
            nowNum = nowNum - member.getCount();
            if (StringUtils.isEmpty(memberProd.getId())) {
                memberProd.setCreateTime(new Date());
//                    memberProd.setCreateUser(userId);
                memberProdMapper.insert(memberProd);
            } else {
                MemberProd memberProdOld = memberProdMapper.selectByPrimaryKey(memberProd.getId());
                memberProdOld.setStartTime(memberProd.getStartTime());
                memberProdOld.setEndTime(memberProd.getEndTime());
                memberProdOld.setComeId(memberProd.getComeId());
                memberProdOld.setMemberId(memberProd.getMemberId());
                memberProdOld.setCount(memberProd.getCount());
                memberProdOld.setUpdateTime(new Date());
                nowNum = nowNum + memberProdOld.getCount() - memberProd.getCount();
                memberProdMapper.updateByPrimaryKey(memberProdOld);
            }
        }
        ProdCome prod = new ProdCome();
        prod.setId(memberProd.getComeId());
        prod.setCurrentCount(nowNum);
        comeMapper.updateByPrimaryKeySelective(prod);
        return ReturnResult.success("插入成功");
    }

    public ReturnResult getList(HttpSession session) {
        WorkRordDTO workRordDTO = new WorkRordDTO();
        return ReturnResult.success(memberProdMapper.getList(workRordDTO));
    }

    public ReturnResult findListByWorkRecord(HttpSession session) {
        WorkRordDTO workRordDTO = new WorkRordDTO();
        return ReturnResult.success(memberProdMapper.findListByWorkRecord(workRordDTO));
    }

    public String delete(int id) {
        try {
            memberProdMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    public ReturnResult getMemberPage(String prodId) {
        WorkRordDTO workRordDTO = new WorkRordDTO();
        if (StringUtils.isEmpty(prodId)) {
        }
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        List<WorkRordDTO> docs = memberProdMapper.findListByWorkRecord(new WorkRordDTO());
        return ReturnResult.success(docs);
    }
}
