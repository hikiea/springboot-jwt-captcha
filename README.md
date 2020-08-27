# springboot-jwt-验证码

SpringBoot集成JWT和验证码实现token+验证码的双重验证

jwt验证功能大幅度参考：https://www.jianshu.com/p/e88d3f8151db

验证码功能大幅度参考：https://www.cnblogs.com/yanl55555/p/12541815.html

## 一、登录流程

### （一）获取验证码

- 请求方式

  ```
  GET
  ```

- 接口

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

- 接口

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
        "user": {
            "id": "3",
            "username": "admin",
            "password": "admin",
            "power": "admin"
        },
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiMyIsImFkbWluIiwiYWRtaW4iXX0.T6KiZVND3Hat7XoM6kcEN4sTIAzNwlsY5BpEpcxOFmY"
    }
    ```

  - 请求头参数

- 返回结果

  ```java
  {
      "user": {
          "id": "用户id",
          "username": "用户名",
          "password": "用户密码",
          "power": "admin"
      },
      "token": "用户的token"
  }
  ```



## 二、获取用户信息流程

### （一）获取用户信息`api`

- 请求方式

  ```
  GET
  ```

- 接口

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
      "用户名：": "用户名",
      "当前权限：": "用户权限",
      "message": "登录成功"
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

- 接口

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
  [
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
  ```



