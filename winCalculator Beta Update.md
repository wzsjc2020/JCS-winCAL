# winCalculator

## author: wzsjc2021 

项目开始时间：2021年12月25日

这是一个仿win10计算器的小程序

## Beta V1.3  final  2022-5-5

1.新增了sideMenuButton 以代替旧版的 menu
2.sideMenuButton 的大小由45X45像素 更改为50X50像素
3.新增了disableSideBar() 以替换旧版的 disableSideBar()
4.将原有保存在LayoutToolkit 包下的组件转移到WidgetToolkit 包下
5.新增了StripLayout，可以按照比例创建相应大小的组件

```java
public StripLayout(int componentCount, boolean isHorizontal, int hgap, int vgap, float[] share) {
    this.componentCount = componentCount;		//父类组件中子类的数量
    this.isHorizontal = isHorizontal;			//是否为横向排列
    this.hgap = hgap;							//横向的间隔
    this.vgap = vgap;							//竖直的间隔
    this.share = share;							//所有组件的比例
}
```

6.新增了upperArea与lowerArea，使得程序结构更加清晰
upperArea 用于写入某些常用的操作
lowerArea 用于编写显示用户界面

```java
private JPanel upperArea = new JPanel();
private JPanel lowerArea = new JPanel();
```

7.添加了罩杯计算器横板与罩杯计算器竖版的两个测试组件
8.winCalV1.3 beta 为最终测试版，不再更新Beta下的任何测试版本



## Beta V1.2 2022-4.31

1.重新架构了该程序
2.新增了侧边栏SideMenuBar，新增了Register类，傻瓜式注册侧边栏程序

```java
panelRegister(String tag, JPanel panel, BufferedImage image)
```

3.新增了TToggleLabel, TTransparentLabel, TTransparentTextField 三个组件
4.添加了随机数生成器与罩杯计算器的两个测试组件

## Beta V1.1 2021-12.30-12.31

1. 现在窗口可拖动的范围更大了
2. PanelStandardCal 从 Calculator 中分离出来了， 调用win10cal.standardCalPanel.setVisable(Calculator win10cal, Boolean close)可以关闭PanelStandardCal面板
3. 现在图片不再放在main.win10cal目录下，而是分类放置在main.resources目录下
4. 20211229.1高危BUG已修复
5. 现在可以使用LayoutToolKit.convertButtonFont 和 LayoutToolKit.convertLabelFont 来批量编辑字体大小
6. 修改了程序图标以及程序的整体颜色，现在可以在CalculatorIntializer中自定义背景、
7. 修复了PanelSidemenu在鼠标移动时颜色未能及时更新的bug

## Beta V1.0 2021-12.25-12.29

1. 新增了注册侧边菜单，设置菜单，功能面板中Setting类
2. 新增了窗口的监听，包括窗口的移动，窗口的缩小，窗口的关闭，不包括窗口的缩放与最大化
3. 实验了java程序打包exe

## BUGS

### 20211229.0  未解决 高危

编译时出现窗口不正常变大

解决办法

1. 将系统中所有java版本删除，并重新安装jdk-8u301-windows-x64或更高版本
2. 若重新安装后依旧无法解决，则打开应用，

### 20211229.1  已解决 高危

图片不在库中会导致程序报错，无法使用error.png来替换丢失图片

解决办法

1. 重新编写了图片加载程序，分别用来判断图片是否有效，以及当图片无效时自动返回默认的错误贴图

   ```java
    autoSelectImage(String imageIcon)
    isMissingIcon(String imageIcon)
   ```





