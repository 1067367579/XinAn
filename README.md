# 心安-殡葬服务小程序后端

## 功能简介

该项目后端使用了Springboot框架, 结合MyBatis框架访问MySQL数据库来进行具体实现
项目包含五个模块: 

### 通用模块
内含上传文件接口,使用阿里云OSS的API进行图片文件的存储

### 清单模块
内含葬礼邀请, 愿望清单, 社交平台管理, 密码柜管理, 宠物管理, 珍宝收藏馆

#### 葬礼邀请
该功能使用户可以从自己的好友列表中找到指定好友添加到邀请名单

#### 愿望清单
用户可以在此添加愿望 删除愿望

#### 社交平台管理
用户可以在此对自己的社交平台状态进行记录

#### 密码柜管理
用户可以在此将自己在各平台上的密码进行管理 将密码存储在此

#### 宠物管理
用户可以在此添加自己宠物的信息, 并且可以进行转让给好友的操作

#### 珍宝收藏馆
用户可以在此添加自己的珍藏信息

### 服务模块
内含服务流程记录, 商家信息查找
可以查看到本地(指定到城市和区/县一级)的商家, 并且商家列表会按照距离远近的顺序列出, 商家数据为自己使用Selenium进行对地图爬取实现
并且在此可以对商家进行收藏操作

### 心安圈模块
类似于公开的朋友圈, 用户可以在此发布文字与图片结合的贴文, 发布后平台上的所有用户均可看到, 可以对此进行点赞操作, 也可以查看指定用户的心安圈

### 个人信息模块
主要显示用户的信息: 昵称, 个性签名, 头像, 心安号, 对其他信息的总览(愿望数, 清单完成的进度, 好友数),

还有对用户生日倒计时天数的显示, 好友列表查看, 根据心安号(手机号)添加好友, 删除好友, 查看好友请求, 同意添加好友, 查看收藏商家, 和个人信息设置(头像,昵称,签名,手机号,性别,生日)的功能

## 前端仓库地址

前端仓库: https://github.com/ho1id4yy/xinan-front-end.git
