#cms 

网站地址：http://www.dev56.com/

#1.功能

1.cms 网站 
2.系统权限
3.微信公众号管理

#2.使用技术：
spring4 + shrio + ehcache + mybatis + MYSQL + H-ui.admin + Amaze UI

使用 maven 项目管理 ，一键发布项目war. 同时maven 结合 ant,yuicompressor 对css js 压缩.

通过 java -P  参数 区分不同环境下发布war

bat 脚本

@echo off
rem 编译 cms 项目
echo -------------------------------------
echo -        编译 cms 项目             -
echo -------------------------------------

echo 编译说明
echo.
rem echo    0  全部
echo    1  开发环境
echo    2  测试环境
echo    3  生产环境
echo.

set/p var=请选择序号:

set sect=
if %var%==1 (
set sect=dev
echo 编译开发环境包.
)
if %var%==2 (
set sect=test
echo 编译测试环境包.
)
if %var%==3 (
set sect=prod
echo 编译生产环境包.
)
@echo.启动编译......
call mvn clean package -P%sect%

@echo.启动结束......
pause

#3.MYSQL数据模型：

![输入图片说明](http://git.oschina.net/uploads/images/2016/1102/093504_7ea0e4a1_411145.png "数据库模型1")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1102/093601_a87043e3_411145.png "数据库模型2")

#4.网站地址

http://www.dev56.com/

![输入图片说明](http://git.oschina.net/uploads/images/2016/1102/091211_684205a6_411145.png "网站首页")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1102/091309_05e9a7ac_411145.png "网站首页-列表")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1102/091427_c7264f69_411145.png "内容页面")

在项目上有什么问题或bug ，可以联系：
QQ群：203498970
个人QQ:513961835

在未来项目会进行完善可以不足和扩展新的功能  ^_^  

网站地址: http://www.dev56.com/
