package com.xzjie.cms.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.model.VisitLog;
import com.xzjie.cms.vo.VisitStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Vito
 * @since 2022/3/19 1:30 下午
 */
public interface VisitLogRepository extends BaseRepository<VisitLog, Long>{

    @Query(nativeQuery = true,value = "SELECT * FROM (\n" +
            "(SELECT 'article' NAME, count(id) total FROM cms_article where state= 1) \n" +
            "UNION ALL\n" +
            "( SELECT 'day' NAME, count( 1 ) total FROM sys_visit_log v WHERE DATE_SUB( CURDATE(), INTERVAL 0 DAY ) <= date( pv_date )) \n" +
            "UNION ALL\n" +
            "( SELECT 'day7' NAME, count( 1 ) total FROM sys_visit_log v  WHERE DATE_SUB( CURDATE(), INTERVAL 7 DAY ) <= date( pv_date )) \n" +
            "UNION ALL" +
            "( SELECT 'day30' NAME, count( 1 ) total FROM sys_visit_log v WHERE DATE_SUB( CURDATE(), INTERVAL 30 DAY ) <= date( pv_date )) \n" +
            "\n) t")
    List<VisitStatistics> findVisitStatistics();

    @Query(nativeQuery = true, value = "SELECT * FROM (\n" +
            "SELECT date(pv_date) name,count(id) total FROM sys_visit_log v \n" +
            "where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(pv_date)\n" +
            "GROUP BY date(pv_date)) t\n" +
            "ORDER BY name asc")
    List<VisitStatistics> findVisitData();
}
