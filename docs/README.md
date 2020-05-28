# gdmec-bxg
gdmec 移动应用项目开发综合课程项目源码仓库


## 0316
### 获取版本信息

```java
try {
    PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
    textView.setText("v" + info.versionName);
} catch (PackageManager.NameNotFoundException e) {
    e.printStackTrace();
    textView.setText("v1.0");
}
```



### 定时跳转

```java
Timer timer = new Timer();
TimerTask task = new TimerTask() {
    @Override
    public void run() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
};
timer.schedule(task,3000);
```

