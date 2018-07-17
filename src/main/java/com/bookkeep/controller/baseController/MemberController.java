package com.bookkeep.controller.baseController;

import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.Member;
import com.bookkeep.mapper.MemberMapper;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 成员基础信息Controller
 **/
@RestController
@RequestMapping("member")
public class MemberController extends BaseController {

    @Autowired
    MemberMapper memberMapper;

    @PostMapping("/insert")
    public ReturnResult insert(Member member, HttpSession session) {
        try {
            if (getUserId(session) == null) {
                return ReturnResult.error("请您先登录");
            }
            int userId = (int) getUserId(session);
            if (member == null) {
                return ReturnResult.error("用户名称不能为空");
            }
            if (StringUtils.isEmpty(member.getId())) {
                member.setType("1");
                member.setCreateTime(new Date());
                member.setCreateUser(userId);
                memberMapper.insert(member);
                return ReturnResult.success("插入成功");
            } else {
                Member memberOld = memberMapper.selectByPrimaryKey(member.getId());
                memberOld.setName(member.getName());
                memberOld.setPhone(member.getPhone());
                memberOld.setUpdateTime(new Date());
                memberMapper.updateByPrimaryKey(memberOld);
                return ReturnResult.success("修改成功");
            }
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    @GetMapping("getList")
    public List getList(HttpSession session, String type) {
        Member member = new Member();
        member.setType(type);
        return memberMapper.findListByMember(member);
    }

    @GetMapping("delete")
    public String delete(int id) {
        try {

            memberMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    @GetMapping("/pagination")
    public ReturnResult getMemberPage() {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
//        PageHelper.startPage(currentPage, pageSize);
        List<Member> docs = memberMapper.findListByMember(new Member());
//        PageInfo<Member> memberInfo = new PageInfo<>(docs);
        return ReturnResult.success(docs);
    }
}
