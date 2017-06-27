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
        "timestamp": "2013-03-12 00:00:00"
    }

#### Licence

MIT

