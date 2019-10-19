package com.qianjin.jack.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianjin.jack.domain.DTO.Search;
import org.apache.commons.lang3.StringUtils;

public class SqlUtil {

    public static <T> QueryWrapper<T> getWrapper(Search search,Class clazz){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(search.getKeyword()),search.getSearch(),search.getKeyword());
        return wrapper;
    }
}
