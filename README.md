# springboot-jwt-redis-验证码

SpringBoot集成JWT和验证码实现token+验证码的双重验证

jwt验证功能大幅度参考：https://www.jianshu.com/p/e88d3f8151db

验证码功能大幅度参考：https://www.cnblogs.com/yanl55555/p/12541815.html

在上述基础上，增加了使用基于redis的token黑名单实现token的销毁

实现对游离状态的token控制

## 一、登录流程

### （一）获取验证码

- 请求方式

  ```
  GET
  ```

- 请求接口

  ```java
  /api/code
  ```

- 请求参数

  - 路径参数
  - 条件参数
  - 请求体参数

- 返回结果

  ```
  一个验证码图片
  ```

### （二）登录

- 请求方式

  ```
  POST
  ```

- 请求接口

  ```
  /api/login
  ```

- 请求参数

  - 路径参数

  - 条件参数

  - 请求体参数

    ```java
    格式：Json
    
    {
        "username":"用户名",
        "password":"密码",
        "code":"验证码"
    }
    ```

  - 请求头参数

- 返回结果

  ```java
  {
      "code": 200,
      "message": "登录成功",
      "data": {
          "user": {
              "id": "1",
              "username": "string",
              "password": "string",
              "power": "user"
          },
          "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiMSIsInN0cmluZyIsInVzZXIiXSwiZXhwIjoxNjAwMzI2OTczLCJpYXQiOjE1OTk0NjI5NzN9.qxIL8McvL09Gb983_rRuVPGoP1dcIdorhkpmoMl8-mo"
      }
  }
  ```



## 二、获取用户信息流程

### （一）获取用户信息`api`

- 请求方式

  ```
  GET
  ```

- 请求接口

  ```
  /api/getUser
  ```

- 请求参数

  - 路径参数

  - 条件参数

  - 请求体参数

  - 请求头参数

    ```
    key:"token"
    
    value:"服务器给用户颁发的token"
    ```

- 返回结果

  ```
  {
      "code": 200,
      "message": "用户获取成功！",
      "data": {
          "用户名：": "string",
          "当前权限：": "user"
      }
  }
  ```



## 三、管理员`API`

管理员API全部需要**管理员（admin）权限**才能访问

管理员API全部需要**管理员（admin）权限**才能访问

管理员API全部需要**管理员（admin）权限**才能访问

### （一）获取所有用户信息

- 请求方式

  ```
  GET
  ```

- 请求接口

  ```
  管理员API全部需要管理员（admin）权限才能访问
  ```

- 请求参数

  - 路径参数

  - 条件参数

  - 请求体参数

  - 请求头参数

    ```
    key:"token"
    
    value:"服务器给用户颁发的token"
    ```

- 返回结果

  ```java
  {
      "code": 200,
      "message": "所有用户获取成功",
      "data": [
          {
              "id": "1",
              "username": "string",
              "password": "string",
              "power": "user"
          },
          {
              "id": "2",
              "username": "char",
              "password": "char",
              "power": "user"
          },
          {
              "id": "3",
              "username": "admin",
              "password": "admin",
              "power": "admin"
          }
      ]
  }
  ```



## 四、退出登录

退出登录功能主要操作token，把token加入黑名单中

- 请求方式

  ```
  GET
  ```

- 请求接口

  ```
  /api/logout
  ```

- 请求参数

  - 路径参数

  - 条件参数

  - 请求体参数

  - 请求头参数

    ```
    key:"token"
    
    value:"服务器给用户颁发的token"
    ```

- 返回结果

  ```
  {
      "code": 200,
      "message": "token已加入黑名单，登出成功",
      "data": null
  }
  ```

  







