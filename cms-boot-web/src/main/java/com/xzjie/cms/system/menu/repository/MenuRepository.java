package com.xzjie.cms.system.menu.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.system.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


public interface MenuRepository extends BaseRepository<Menu, Long> {

    /**
     * 根据菜单名称查询
     *
     * @param name 菜单名称
     * @return /
     */
    Menu findByName(String name);

    /**
     * 根据组件名称查询
     *
     * @param name 组件名称
     * @return /
     */
    Menu findByComponentName(String name);

    /**
     * 根据菜单的 PID 查询
     *
     * @param pid /
     * @return /
     */
    List<Menu> findByPid(Long pid);

    List<Menu> findByPidOrderBySortDescIdAsc(Long pid);

    @Modifying
    @Transactional
    @Query(value = "update Menu m set m.state = 0 where m.id in (:ids) ")
    void delete(Set<Long> ids);

    /**
     * 根据角色菜查询菜单
     *
     * @param roles
     * @return /
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT m.* FROM sys_menu m INNER JOIN sys_permission p on m.id=p.menu_id\n" +
            "INNER JOIN sys_role r ON p.role_id=r.id\n" +
            "WHERE m.state =1 and r.role_code in ?1 and type in ?2 ORDER BY m.sort DESC,id ASC")
    List<Menu> findMenuByRoles(Set<String> roles, List<Integer> types);
}
