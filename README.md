# WoodenFish

自制的安卓电子木鱼Android Wooden Fish，点击会有+1动画，可以设置是否开启振动和声音，可以自定义数值，置零数值。

### 效果视频展示

抖音视频地址：

https://v.douyin.com/BFq7QUW/


### 开发环境

Android Studio

API > 21 安卓5.0以上  minSdk 21   targetSdk 33

### 实现说明

点击+1，木鱼图片设置监听setOnTouchListener，重写OnTouch方法，然后处理缩放、+1动画，声音播放，振动等操作；其中数据存储处理用到了SharedPreferences保存数据键值对。


### 用到的依赖

点击的+1动画用到的仓库

https://github.com/DYNC-Android/AddHeart

### UI和多媒体部分

PS自制的木鱼图片

其他UI图标来源于网站IconFont

https://www.iconfont.cn/

木鱼敲击音频来源于

https://www.tosound.com/search/word-%E6%9C%A8%E9%B1%BC

+1图片来源于制作图网

https://www.zhizuotu.com/

### 效果图片展示

![Screenshot_2023-01-31-20-18-45-15_911ae26e9e65e8927a789d974f8fb67e](https://user-images.githubusercontent.com/57706599/215757883-88577cf8-1a57-4735-83e6-3ba3e4a6c643.jpg)
