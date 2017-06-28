Ark
===

> 前端打点后端收集服务

#### 功能描述

* 提供http api进行日志打点工作
* 支持打点信息落地文件
* 支持打点信息pipe to kafka different channels


#### 打点结构定义(通用)

    {
        "actionId": "action#1"
        "pageId": "page#1",
        "moduleId": "module#1",
        "referActionId": "asddsadasd",
        
        "path": "/api/.json(.html)",
        "param": "{}",
        
        "mid": "hello",
        "timestamp": "2013-03-12 00:00:00"，
        
        "extra": ""
    }

#### 打点API（持久化文件）
    
① 接口url及请求方式
    
| URL    | 请求方式|
|--------|--------|
|  /ark  |   POST |
    
② 调用示例
http://host:port/ark

```
{
    "typ": 0,
    "content": {
        "actionId": "action#1"
        "pageId": "page#1",
        "moduleId": "module#1",
        "referActionId": "asddsadasd",
                
        "path": "/api/.json(.html)",
        "param": "{}",
                
        "mid": "hello",
        "timestamp": "2013-03-12 00:00:00"，
                
        "extra": ""
    }
}
```
    
③ 返回json样例
    
```

```

#### Licence

MIT

