package com.yt.comment.mapper;

import com.yt.comment.entity.Member;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface MemberMapper {

    /**
     * 根据查询条件查询会员列表
     * @param member
     * @return
     */
    List<Member> select(Member member);
}
