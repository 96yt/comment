package com.yt.comment.mapper;

import com.yt.comment.entity.Dic;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface DicMapper {
    List<Dic> select(Dic dic);
}
