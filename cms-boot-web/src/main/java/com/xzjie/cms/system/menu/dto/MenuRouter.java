package com.xzjie.cms.system.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * // if set to true, lt will not appear in sidebar nav.
 * // e.g. login or 401 page or as some editing pages /edit/1 (Default: false)
 * hidden: true
 *
 * // this route cannot be clicked in breadcrumb navigation when noRedirect is set
 * redirect: noRedirect
 *
 * // when you route a children below the declaration of more than one route,
 * // it will automatically become a nested mode - such as the component page
 * // when there is only one, the child route will be displayed as the root route
 * // if you want to display your root route
 * // regardless of the number of children declarations under the route
 * // you can set alwaysShow: true
 * // so that it will ignore the previously defined rules and always show the root route
 * alwaysShow: true
 *
 * // set router name. It must be set，in order to avoid problems with <keep-alive>.
 * name: 'router-name'
 *
 * meta: {
 *   // required roles to navigate to this route. Support multiple permissions stacking.
 *   // if not set means it doesn't need any permission.
 *   roles: ['admin', 'editor']
 *
 *   // the title of the route to show in various components (e.g. sidebar, breadcrumbs).
 *   title: 'title'
 *
 *   // svg icon class
 *   icon: 'svg-name' // or el-icon-x
 *
 *   // when set true, the route will not be cached by <keep-alive> (default false)
 *   noCache: true
 *
 *   // if false, the item will hidden in breadcrumb(default is true)
 *   breadcrumb: false
 *
 *   // if set to true, it can be fixed in tags-view (default false)
 *   affix: true // this is very useful in some scenarios, // click on the article to enter the article details page,
 *
 *   // When you set, the related item in the sidebar will be highlighted
 *   // for example: a list page route of an article is: /article/list
 *   // at this time the route is /article/1, but you want to highlight the route of the article list in the sidebar,
 *   // you can set the following
 *   activeMenu: '/article/list'
 * }
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuRouter {

    private Long id;

    private Long pid;

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMeta meta;

    private List<MenuRouter> children = new ArrayList<>();
}
