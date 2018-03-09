package com.wsk.movie.springdata.admin;

import com.wsk.movie.springdata.admin.entity.CriticReportEntity;
import com.wsk.movie.springdata.admin.more.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @DESCRIPTION :
 * @AUTHOR : WuShukai1103
 * @TIME : 2018/3/8  18:51
 */
public interface ReportRepository extends JpaRepository<CriticReportEntity, Integer>{
    @Query(value = "SELECT cr.uid,cr.pid,cr.ctime,pc.time as ptime,pc.critic,pc.thumbnails as image,ui.`name` as username, ui.phone FROM `critic_report` cr LEFT JOIN publishcritic pc ON cr.pid = pc.id LEFT JOIN userinformation ui ON ui.id = cr.uid ", nativeQuery = true)
    List<ReportEntity> find();
}
