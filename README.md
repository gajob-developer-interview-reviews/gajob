### HISTORY

**2022.09.20** <br/>
프로젝트 생성

> Java 11 /
> Spring Boot 2.7.3 /
> Gradle

> Dependancies
> 
> Spring Web, Thymeleaf, MySQL Driver, Spring Data JPA, Lombok

---
**2022.09.26** <br/>
크롤러 추가
> Dependancies
> 
> selenium-java

DB 설정 추가
> Dependancies
>
> spring-boot-starter-data-jpa

> mysql 설정 추가
> 
> mysql> set @@global.sql_mode = 'ONLY_FULL_GROUP_BY,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

---
**2022.09.28** <br/>
OAuth2.0 - 구글 로그인 적용 <br/>
> Dependancies <br/> <br/>
> spring-boot-starter-security , 
> thymeleaf-extras-springsecurity5 , 
> spring-security-test , 
> spring-boot-starter-oauth2-client

---
**2022.10.09** <br/>
> Dependancies <br/> <br/>
> json 

---
**2022.10.10** <br/>
REST API 응답 형식 추가 <br/>
> RestEntity <br/> <br/>
> HttpStatus , msg, data 를 가짐

> USE <br/> <br/>
> public ResponseEntity save( ... ) { <br/>
> return new ResponseEntity(RestEntity.res(HttpStatus.OK, "후기가 등록되었습니다.", null), HttpStatus.OK); <br/>
> }

---
**2022.10.25** <br/>
회사 지도 대체 이미지 추가 <br/>
> abstract-architecture-blue-entrance-perspective-financial.jpg <br/> 
> 작가 4045 출처 Freepik
