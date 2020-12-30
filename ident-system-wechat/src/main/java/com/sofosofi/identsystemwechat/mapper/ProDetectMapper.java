package com.sofosofi.identsystemwechat.mapper;

import com.sofosofi.identsystemwechat.entity.ProDetect;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ProDetectMapper extends Mapper<ProDetect> {
    @Select("SELECT sum(total_num) totalNum, sum(true_num) trueNum, sum(false_num) falseNum  from pro_detect where create_by = #{userName,jdbcType=VARCHAR}")
    ProDetect selectSumByUserName(@Param("userName") String userName);
}