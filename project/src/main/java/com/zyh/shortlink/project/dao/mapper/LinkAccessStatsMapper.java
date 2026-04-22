package com.zyh.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyh.shortlink.project.dao.entity.LinkAccessStatsDO;
import com.zyh.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import com.zyh.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    @Insert("insert into t_link_access_stats(id,full_short_url,date,pv,uv,uip,hour,weekday,create_time,update_time,del_flag) " +
            "values(#{linkAccessStats.id},#{linkAccessStats.fullshortUrl},#{linkAccessStats.date},#{linkAccessStats.pv},#{linkAccessStats.uv},#{linkAccessStats.uip},#{linkAccessStats.hour},#{linkAccessStats.weekday},now(),now(),0)" +
            "on duplicate key update pv=pv+#{linkAccessStats.pv},uv=uv+#{linkAccessStats.uv},uip=uip+#{linkAccessStats.uip}")
    void shortLinkStats(@Param("linkAccessStats") LinkAccessStatsDO linkAccessStats);


    @Select("SELECT" +
            "tlas.date,sum(tlas.pv),sum(tlas.uv),sum(tlas.uip)" +
            "FROM t_link tl innner join t_link_access_stats tlas" +
            "ON tl.fullShortLink=tlas.fullShortLink" +
            "Where tlas.fullShortLink=#{param.fullShortLink} AND" +
                   "tl.gid=#{param.gid} AND" +
                   "tl.del_flag=0 AND" +
                   "tl.enableStats=#{param.enableStats} AND" +
                   "tlas.date BETWEEN #{param.startDate} AND #{param.endDate}" +
            "GROUP BY  tlas.full_short_url, tl.gid, tlas.date;")
    List<LinkAccessStatsDO> listStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据分组获取指定日期内基础监控数据
     */
    @Select("SELECT " +
            "    tlas.date, " +
            "    SUM(tlas.pv) AS pv, " +
            "    SUM(tlas.uv) AS uv, " +
            "    SUM(tlas.uip) AS uip " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_stats tlas ON tl.full_short_url = tlas.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlas.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlas.date;")
    List<LinkAccessStatsDO> listStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 根据短链接获取指定日期内小时基础监控数据
     */
    @Select("SELECT " +
            "    tlas.hour, " +
            "    SUM(tlas.pv) AS pv " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_stats tlas ON tl.full_short_url = tlas.full_short_url " +
            "WHERE " +
            "    tlas.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = #{param.enableStatus} " +
            "    AND tlas.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlas.full_short_url, tl.gid, tlas.hour;")
    List<LinkAccessStatsDO> listHourStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据分组获取指定日期内小时基础监控数据
     */
    @Select("SELECT " +
            "    tlas.hour, " +
            "    SUM(tlas.pv) AS pv " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_stats tlas ON tl.full_short_url = tlas.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlas.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlas.hour;")
    List<LinkAccessStatsDO> listHourStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * 根据短链接获取指定日期内小时基础监控数据
     */
    @Select("SELECT " +
            "    tlas.weekday, " +
            "    SUM(tlas.pv) AS pv " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_stats tlas ON tl.full_short_url = tlas.full_short_url " +
            "WHERE " +
            "    tlas.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = #{param.enableStatus} " +
            "    AND tlas.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlas.full_short_url, tl.gid, tlas.weekday;")
    List<LinkAccessStatsDO> listWeekdayStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * 根据分组获取指定日期内小时基础监控数据
     */
    @Select("SELECT " +
            "    tlas.weekday, " +
            "    SUM(tlas.pv) AS pv " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_stats tlas ON tl.full_short_url = tlas.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlas.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlas.weekday;")
    List<LinkAccessStatsDO> listWeekdayStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);
}
