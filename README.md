<div id="top"></div>
<br />
<div align="center">
  <a href="https://github.com/gajob-for-Recruitment-reviews">
    <img src="https://avatars.githubusercontent.com/u/117059035?s=400&u=b9441b2de1f395bc73084bdd80f6665bfb659190&v=4" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">GAJOB - 개발자를 위한 채용 후기 플랫폼</h3>

  <p align="center">
    <h4><a href="http://www.gajob.site">👉사이트 바로가기👈</a></h4>    
    <br>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
### Table of Contents
  <ol>
    <li><a href="#introduce">Introduce</a></li>
    <li><a href="#features">Features</a></li>
    <li><a href="#tech-stacks">Tech Stacks</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#history">History</a></li>
  </ol>


---

### Introduce

**가잡 - 개발자 채용 후기**는 개발자를 위한 채용 후기 공유 플랫폼 입니다.

- 구직할 때 회사 면접 후기 많이 찾아보시나요? 💁🏻‍♀️
- 인적성, 면접에 맞춰진 플랫폼... 코딩테스트, 과제 등에 대한 정보는 어디서 얻죠? 😂

그래서 만들었습니다!

- **이제 코딩테스트, 과제 등 다양한 항목에 대한 후기를 남겨보세요!**
- **다른 사람이 남긴 후기도 자유롭게 확인할 수 있습니다.**

---

### Features

- 후기 확인
- 후기 등록
- 구글 계정으로 로그인 및 회원가입
- 내가 쓴 후기 관리

![image](https://user-images.githubusercontent.com/80824750/200771808-3e445c19-b053-427e-8ae5-d8b1631e47cc.png)


<!-- - **구글 계정으로 시작하기** </br></br>
구글 계정으로 간편하게 가입하세요


- **회사별 후기 확인하기** </br></br>

- **후기 등록하기** </br></br>

- **내가 쓴 후기 확인하고, 삭제하기** </br></br> -->


---


### Tech Stacks
<table>
	<tr><th rowspan="4">Front-end</th><td>Language</td><td>HTML/CSS/JS</td></tr>
	<tr><td>Template Engine</td><td>Thymeleaf</td></tr>
	<tr><td>Asynchronous</td><td>Ajax</td></tr>
	<tr><td>Open API</td><td>Kakao Map API</td></tr>
	<tr><th rowspan="6">Back-end</th><td>Language</td><td>Java 11</td></tr>
	<tr><td>Framework</td><td>Spring Boot 2.7.3</td></tr>
	<tr><td>ORM</td><td>Spring Data JPA, JPQL</td></tr>
	<tr><td>Authorization</td><td>Spring Security, OAuth2.0</td></tr>
	<tr><td>Database</td><td>MySQL</td></tr>
	<tr><td>Open API</td><td>Google Login API</td></tr>
	<tr><th rowspan="2">Management</th><td>Communication</td><td>Notion</td></tr>
	<tr><td>Version Control</td><td>Github</td></tr>
	<tr><th>Naver Cloud Service</th><td colspan="2">Server, Global DNS</td></tr>
</table>

---

### Contact

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/yewon-Noh">
        <img src="https://avatars.githubusercontent.com/u/80824750?v=4" width="110px;" alt=""/><br />
        <sub><b>노예원</b></sub></a><br />
        <sub><b>Full-stack</b></sub></a><br />
        <a href="https://github.com/yewon-Noh" title="Github">💻</a>
        <a href="mailto:yewo2nn16@gmail.com" title="Email">✉</a>
    </td>
  </tr>
</table>  


---
### HISTORY

<details>
<summary>Look Project History 👀👀</summary>

<br/>

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

---
**2022.10.30** <br/>
favicon 추가 <br/>
> favicon.ico <br/>
> 생성 link : https://favicon.io/ <br/><br/>
> ![favicon-32x32](https://user-images.githubusercontent.com/80824750/198868678-89397e81-4605-43a4-a405-3e6dad8651e7.png)

---
**2022.11.07** <br/>
이미지 캐시 설정 <br/>
> WebMvcConfig.java <br/>
> 참고 : https://tbread-development.tistory.com/25
<br/>

브라우저 성능 검사 <br/>
> [Google PageSpeed Insights](https://pagespeed.web.dev/?hl=ko) <br/><br/>
> 휴대전화 <br/>
> ![결과_휴대전화](https://user-images.githubusercontent.com/80824750/200248603-b1c731e3-c160-4726-8f1a-f7b3c32558e0.png)
> 
> 데스크톱 <br/>
> ![결과_데스크톱](https://user-images.githubusercontent.com/80824750/200248864-58a19152-db78-4a4e-8ece-f27962281303.png)

---
**2022.11.09** <br/>
robots.txt 및 meta-data 설정 <br/>

> **검색엔진 최적화 점수 증가** <br/><br/>
> 휴대전화 : 75 -> 83 <br/>
> ![검색엔진_휴대전화](https://user-images.githubusercontent.com/80824750/200773215-1ade1fe4-3927-4584-82ad-b72c92eee858.png)
> 
> 데스크톱 : 70 -> 80 <br/>
> ![검색엔진_데스크톱](https://user-images.githubusercontent.com/80824750/200773304-a78730d8-2949-4f05-9bac-19b55377f243.png)


</details>
