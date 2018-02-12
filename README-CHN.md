# Android-CustomRatingBar
自定义的星形组件  

[![Release](https://jitpack.io/v/CaiJingLong/Android-CustomRatingBar.svg)](https://jitpack.io/#CaiJingLong/Android-CustomRatingBar)
---

[English doc](https://github.com/CaiJingLong/Android-CustomRatingBar/blob/master/README.md)

[中文文档](https://github.com/CaiJingLong/Android-CustomRatingBar/blob/master/README-CHN.md)

## 项目相关
- 平台：Android
- 语言：java and xml
- 目前版本：1.0

### 前言
---
因为项目中用到了评分系统，而系统自带的RatingBar功能看似完整，但大小定义太过困难，所以考虑自定义view  
## 截图
---
![layout](https://b6kk3w.bn1303.livefilestore.com/y3mwprYwIaG95BSlv1QElIYWkzH7feAoYVB1mDV226lRerdjKuWP5Ijwe9sysN5-DglMglx0w0v0zWF2m3O5LRhYH8iJovE81NRejxpD1mkIMwzZYPVgbMg2zADathTXWiW80UZVubc84dyfKjnlWGU1Ckmoah__tJejbEpb9g7cr8/layout-2016-03-19-222225.png?psid=1)

### gradle依赖
根目录的build.gradle

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

moudle(app)的build.gralde

    dependencies {
            compile 'com.github.CaiJingLong:Android-CustomRatingBar:-SNAPSHOT'
    }


###技术选型
---
    自定义View有继承View和ViewGroup的差别
    星形可以使用Path绘制，但太过繁琐，且一般星形都是设计师的切图，随时可能发生变化，所以考虑继承ViewGroup来实现
    而继承系统控件和ViewGroup各有优劣，ViewGroup相对轻量级一些，而继承LinearLayout也可以实现，这里我选用的是ViewGroup，因为系统控件中有很多系统级属性，这些属性如果被使用，则不能保证考虑周全
    
###自定义属性
    <declare-styleable name="RB">
        <!--每颗星星的宽度-->
        <attr name="starWidth" format="dimension"/>
        <!--每颗星星的高度-->
        <attr name="starHeight" format="dimension"/>
        <!--星星的数量-->
        <attr name="maxStar" format="integer"/>
        <!--最小的可选择数量-->
        <attr name="minStar" format="float"/>
        <!--当前星星-->
        <attr name="currentStar" format="float"/>
        <!--星星间距-->
        <attr name="padding" format="dimension"/>
        <!--空星星-->
        <attr name="emptyStar" format="reference"/>
        <!--满星星-->
        <attr name="fullStar" format="reference"/>
        <!--半星星-->
        <attr name="halfStar" format="reference"/>
        <!--可以被触摸改变-->
        <attr name="canChange" format="boolean"/>
    </declare-styleable>
    
## 使用
---
### xml文件
---

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:paddingTop="10dp"
        tools:context="com.dn.ratingbar.MainActivity">
    
        <com.kikt.view.CustomRatingBar
            android:id="@+id/rb"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            app:currentStar="3.5"
            app:fullStar="@mipmap/ic_launcher"
            app:maxStar="5"
            app:minStar="0.5"
            app:padding="3dp"
            app:starHeight="30dp"
            app:starWidth="30dp"/>
    
        <com.kikt.view.CustomRatingBar
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            app:currentStar="3.5"
            app:maxStar="5"
            app:minStar="0.5"
            app:padding="3dp"
            app:starHeight="30dp"
            app:starWidth="30dp"/>
    
        <com.kikt.view.CustomRatingBar
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            app:currentStar="4"
            app:maxStar="6"
            app:minStar="1.5"
            app:padding="3dp"
            app:starHeight="50dp"
            app:starWidth="50dp"/>
    </LinearLayout>

### java核心代码
---

    mRb.setOnStarChangeListener(this);
    
    @Override
    public void onStarChange(CustomRatingBar ratingBar, float star) {
        Log.d("MainActivity", "star:" + star);
    }

## 后记
    在写完项目后，我也发现了一些其他类似的项目，继承自LinearLayout的项目，可惜是使用imageView的onclick事件实现，目测无法拖动改变星星的数量
    后续会考虑将项目上传jcenter/mavencenter

## 相关
- [email](mailto:cjl_spy@163.com?subject=RatingBar-Feedback)
- 欢迎fork
- 欢迎指正
- 欢迎提bug
