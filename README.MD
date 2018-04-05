Spring-Stateless-Restful-Server
===============================

This project is example of stateless restful server powered by spring boot 2.0 and JWT.

Goal
----
  - Stateless Restful API Server with JWT Authentication.

Used features.
--------------
  - First idea.
    + h2 db
    + spring boot 2.0
    + Authenticate with JWT and make server as stateless.
      * ref.
        - https://aboullaite.me/spring-boot-token-authentication-using-jwt/
        - http://wonwoo.ml/index.php/post/980
        - and other more..
    + Authority
      * ref.
        - http://www.baeldung.com/spring-security-method-security
    + webflux

  - Involved items on writing.
    + Spring security
      * ref.
  		- http://heowc.tistory.com/46

  - Precautions
  	+ Checklist to use JWT for authentication. (security problem)
  		* https://medium.com/@OutOfBedlam/jwt-%EC%9E%90%EB%B0%94-%EA%B0%80%EC%9D%B4%EB%93%9C-53ccd7b2ba10

Table
-----
  - User
    + authority : END_USER, SERVICE_PROVIDER, ADMIN


Welcome suggestions.
