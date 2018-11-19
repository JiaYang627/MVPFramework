# MVPFramework

此项目框架采用 OkHttp3 + RxJava2 + Retrofit2 + Dagger2 + FastJson 为主体的 mvp框架

* 今天(18-11-19 加入 JessYan 的 RetrofitManager 动态更换已注入的BaseUrl),简单说一下 使用情况
    * 主项目 已注入的BaseUrl：“https://github.com/”，此时某些接口想改变BaseUrl。此时 在要改变接口上 添加 @Headers 然后动态更改就行了，操作步骤参看JessYan 写的：[动态更换某些接口，主项目的BaseUrl不变](https://github.com/JessYanCoding/RetrofitUrlManager/blob/master/README-zh.md)

    * 主项目 已注入的BaseUrl：“https://github.com/”，此时项目整体想改变BaseUrl。changeUrl：“https://baidu.com/” 此时只需要在对应的位置改变即可：

    ```
    RetrofitUrlManager.getInstance().setGlobalDomain("your baseurl");

    ```
    * 主项目 已注入的BaseUrl：“https://github.com/wiki/part” 等等后面有多级时，此时项目整体想改变BaseUrl。changeUrl：“https://baidu.com/” 具体可看 JessYan 文章：[解决Retrofit多BaseUrl及运行时动态改变BaseUrl(二)](https://www.jianshu.com/p/35a8959c2f86)