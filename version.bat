echo off  
  
echo [INFO]修改版本打包脚本 请输入需要升级的版本（如v1.0.1):  
  
set /p newVersion=   
  
echo [INFO]输入%newVersion% 开始替换版本  
  
call mvn clean versions:set -DnewVersion=%newVersion%  
  
echo [INFO]请检查是否所有子模块都升级版本成功 新版本为%newVersion%  