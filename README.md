# MVPFramework

此项目框架采用 OkHttp3 + RxJava2 + Retrofit2 + Dagger2 + FastJson 为主体的 mvp框架

* 今天(18-11-19 加入 JessYan 的 RetrofitManager 动态更换已注入的BaseUrl),简单说一下 使用情况
    * 主项目 已注入的BaseUrl：“https://github.com/” ，此时某些接口想改变BaseUrl。此时 在要改变接口上 添加 @Headers 然后动态更改就行了，操作步骤参看JessYan 写的：[动态更换某些接口，主项目的BaseUrl不变](https://github.com/JessYanCoding/RetrofitUrlManager/blob/master/README-zh.md)

    * 主项目 已注入的BaseUrl：“https://github.com/” ，此时项目整体想改变BaseUrl。changeUrl：“https://baidu.com/” 此时只需要在对应的位置改变即可：

    ```
    RetrofitUrlManager.getInstance().setGlobalDomain("your baseurl");

    ```
    * 主项目 已注入的BaseUrl：“https://github.com/wiki/part” 等等后面有多级时，此时项目整体想改变BaseUrl。changeUrl：“https://baidu.com/” 具体可看 JessYan 文章：[解决Retrofit多BaseUrl及运行时动态改变BaseUrl(二)](https://www.jianshu.com/p/35a8959c2f86)


***
> 以下是关于 使用模板 MVPFrameworkTemplate 的内容

* 写之前先声明一下，本框架当初是为了学习MVP 创建，所以在命名上过于随意。本不打算开源公开 只为自己平时学习、使用。目前项目归类已经整理好，
    * 如要学习框架，运行即可。
    * 如要仿照/基于本框架 搭建框架 文件夹 p v 及其下属 就不必在创建。

* 使用 模板MVPFrameworkTemplate
    * 如果单是看如何使用 并学习，只看本项目即可。
    * 如果是参照本框架搭建公司项目的基础MVP框架，在搭建时，本项目的文件夹 p v 及其就不必在创建。
        * 如果是在子目录下使用模板，创建出来的Act 页面 R文件导包会报错，删除错误的导包，自动重新导包即可。
        * 在使用 模板 MVPFrameworkTemplate创建 页面后，**只需要把 m文件夹下的 ApiComponent 以及 BaseActivity、BasePresenter、BaseViewIpm等 common 下使用到的Base相关类 导包删除 重新导包即可。**