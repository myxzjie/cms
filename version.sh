#!/bin/bash
  
echo "[INFO] 修改版本打包脚本 请输入需要升级的版本（如1.0.1)"
  
read -p "请输入版本号:" newVersion
  
echo [INFO] 输入$newVersion 开始替换版本

mvn clean versions:set -DnewVersion=$newVersion

echo [INFO] 请检查是否所有子模块都升级版本成功 新版本为$newVersion