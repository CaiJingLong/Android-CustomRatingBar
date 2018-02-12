#Android-CustomRatingBar
Custom Rating Bar  
 
[![Release](https://jitpack.io/v/CaiJingLong/Android-CustomRatingBar.svg)](https://jitpack.io/#CaiJingLong/Android-CustomRatingBar)
---

## doc

[English doc](https://github.com/CaiJingLong/Android-CustomRatingBar/blob/master/README.md)

[中文文档](https://github.com/CaiJingLong/Android-CustomRatingBar/blob/master/README-CHN.md)

## project
- platform :Android 
- language :java
- version ：1.0

### install
Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

    dependencies {
            compile 'com.github.CaiJingLong:Android-CustomRatingBar:-SNAPSHOT'
    }


## screenshot
---
![layout](https://b6kk3w.bn1303.livefilestore.com/y3mwprYwIaG95BSlv1QElIYWkzH7feAoYVB1mDV226lRerdjKuWP5Ijwe9sysN5-DglMglx0w0v0zWF2m3O5LRhYH8iJovE81NRejxpD1mkIMwzZYPVgbMg2zADathTXWiW80UZVubc84dyfKjnlWGU1Ckmoah__tJejbEpb9g7cr8/layout-2016-03-19-222225.png?psid=1)

### description 
---
    extends viewgroup

### xml attribute 
```xml    
        <declare-styleable name="RB">
        <!--star width-->
        <attr name="starWidth" format="dimension"/>
        <!--star height-->
        <attr name="starHeight" format="dimension"/>
        <!--star number-->
        <attr name="maxStar" format="integer"/>
        <!--min selected star-->
        <attr name="minStar" format="float"/>
        <!--current star count -->
        <attr name="currentStar" format="float"/>
        <!--star padding-->
        <attr name="padding" format="dimension"/>
        <!--empty star src-->
        <attr name="emptyStar" format="reference"/>
        <!--full star src-->
        <attr name="fullStar" format="reference"/>
        <!--half star src-->
        <attr name="halfStar" format="reference"/>
        <!--is can be change-->
        <attr name="canChange" format="boolean"/>
    </declare-styleable>
```

## use
---
### xml文件
---

```xml

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
```
### use java code

---

```java

    void onCreate(){
        mRb = findViewById(R.id.rb);
        mRb.setOnStarChangeListener(this);

        @Override
        public void onStarChange(CustomRatingBar ratingBar, float star) {
            Log.d("MainActivity", "star:" + star);
        }
    }
```

## about me
- [email](mailto:cjl_spy@163.com?subject=RatingBar-Feedback)
- or issue
