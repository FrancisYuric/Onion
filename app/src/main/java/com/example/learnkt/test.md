# 更新日志
1. 开始下载，暂停下载（对于小文件来说，暂停下载就是取消下载）,继续下载
完成于 2020/1/15 20：00
2. 对于登录接口的数据返回缓存，希望存在多种缓存操作模式，像按照次数进行缓存啊，
像按照时间进行缓存啊，这些操作模式其实我都希望能支持，况且支持这些其实并不困难
这个功能在高并发的操作中其实效果挺明显的

3. View层和Net层抽离到对应的模块中，让业务层仅仅关注视图层
4. 如果我知道API以及返回的数据类型，那么Model，Presenter，View三个其实完全都不用我自己去创建，用APT去生成就行了
实际上生成的三者为BaseModel,BasePresenter,BaseView，通过注解的方式将此三者的实现类直接传输，注解的定义如下
@MVP(model= ModelImpl.class
            ,view=ViewImpl.class
            ,presenter=PresenterImpl.class)