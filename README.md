<img width="350" height="200" alt="logo" src="https://github.com/user-attachments/assets/b21ef912-bb7f-4cb8-b488-964bdad05ee0" />


<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## Contents-->
<details open>
<summary><h2> Contents </h2></summary>

🚀 [Project Overview](#--Project-Overview--) 🚀

🧑‍🤝‍🧑 [Project Team "돋보기"](#--Project-Team-돋보기--) 🧑‍🤝‍🧑

📜 [Project Rules](#--Project-Rules--) 📜

🚩 [Getting Started Guide](#--Getting-Started-Guide--) 🚩

🎨 [Output](#--Output--) 🎨

✨ [Project UI & Feature Overview](#--Project-UI--Feature-Overview--) ✨

🔧 [Troubleshooting](#--Troubleshooting--) 🔧

📂 [File Structure](#--File-Structure--) 📂
<br>
<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 🚀 Project Overview 🚀-->
<details open>
<summary><h2> 🚀 Project Overview 🚀 </h2></summary>
<hr noshade>

- __프로젝트 명__ : 돋보기 (Magnifier)

- __프로젝트 소개__ : 시니어들을 위한 취업 지원 서비스, 돋보기

- __프로젝트 목표__ : 큰 글씨, 쉬운 UI/UX, 시니어 맞춤 공고로 디지털 환경이 익숙하지 않은 시니어 구직자들에게 쉽고 편리한 구직 환경 제공

- __주요 기능__ :
  - 개인/기업 회원가입
  - 개인/기업 로그인
  - 로그아웃
  - 내 정보
  - 이력서 등록 & 수정
  - 이력서 상세 조회
  - 전체 채용 공고 목록 조회 & 기업별 등록한 채용 공고 목록 조회
  - 공고 상세 조회
  - 공고 등록 & 수정 & 삭제
  - 공고 지원하기
  - 공고 지원내역

- __개발 기간__ : 2025.08. 25~ 2025.09.16 (약 3주간)

- __프로젝트 블로그__ : [노션_돋보기 (Magnifier)](https://www.notion.so/KOSA-2-25aebb90731b80778e09d9c5b46d1451)

- __발표 자료__ : [Canva_프로젝트 PPT](https://www.canva.com/design/DAGzBL_Y42o/XN6zYoycwCQ4e13muPccbw/edit)

- __시연 영상__ : <영상 추가>
<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 🧑‍🤝‍🧑 Project Team "ZoomIn" 🧑‍🤝‍🧑-->
<details>
<summary><h2> 🧑‍🤝‍🧑 Project Team "ZoomIn" 🧑‍🤝‍🧑 </h2></summary>
<hr noshade>

&nbsp;&nbsp;&nbsp;&nbsp;KOSA_2차 프로젝트 팀 ZOOMIN

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: 사회적 취약 계층인 시니어에게 관심을 기울이고, 그들의 어려움도 확대하여 세심하게 들여다보겠다는 의지를 담음
<br>
<br>

&nbsp;&nbsp;__[ 팀원 소개 ]__

|<img src="https://avatars.githubusercontent.com/u/97112125?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/109258144?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/219790960?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|
|Kiseong Kim<br/>[@kks1177](https://github.com/kks1177)|kimkyongah<br/>[@hagnoykmik](https://github.com/hagnoykmik)|lsw1096<br/>[@leesangwoo-dev](https://github.com/leesangwoo-dev)|

<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 📜 Project Rules 📜-->
<details>
<summary><h2> 📜 Project Rules 📜 </h2></summary>
<hr noshade>

### 개발 환경 (버전참고)
- __Environment__ : Spring Tools 3.9 for Eclipse, Oracle SQL Developer(^20.4.0.379), git(^2.50.0), github
- __Language__ :  Java 8
- __Framwork__ :
  - Spring Framework 5
  - Spring Web MVC
  - Spring Security
- __Library__
  - Maven
  - MyBatis
  - HikariCP
  - JUnit 4
  - lombok-1.18.38.jar
- __DB__: OracleDB
- __Communication__ : notion, figma, erdcloud, canva

### Code Convention
- https://www.notion.so/25eebb90731b808ca3e5c1d93d833aa9

<br>

### 프로젝트 운영 방식 및 Rules 예시
  ####     __[ 깃허브 규칙 ]__
  - 브랜치 이름 : 기능 이름

  ####     __[ Commit Convention ]__

| 타입     | 설명                                                         |
| -------- | ------------------------------------------------------------ |
| Feat     | 기능 구현                                                    |
| Fix      | 버그 수정                                                    |
| Refactor | 코드 리팩토링                                                |
| chore    | 빌드 task 수정, 패키지 매니저 수정 (pom.xml 등)              |
| Style    | 코드 스타일링 관련 코드 추가/수정                            |
| Minor    | 마이너 한 변경사항 (오타 수정, 탭 사이즈 변경, 변수명 변경 등) |
| test     | 테스트 코드, 리팩토링 테스트 코드 추가                        |
| Docs     | 문서 추가/수정 (README.md, .gitignore 등)                   |
| Dir      | 폴더 구조 수정                                              |

  ####     __[ 커밋 메시지 예시 ]__
&nbsp;&nbsp; __FEAT : 신규 input 태그에 대한 이벤트 함수 추가__
<br>
<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 🚩 Getting Started Guide 🚩-->
<details>
<summary><h2> 🚩 Getting Started Guide 🚩 </h2></summary>
<hr noshade>

### Installation
```bash
$ git clone https://github.com/KOSA-MSAFullStack/2ndProject_Magnifier.git
$ cd 2ndProject_Magnifier
```

### API Key
1. views/enterprises/signup.jsp 서비스 인증키 수정
2. 인증키 (회원가입 후 마이페이지 서비스 인증 키) 발급받기
   - [사업자등록정보 진위확인 API 인증 서비스 키](https://www.data.go.kr/data/15081808/openapi.do) (SERVICE_KEY: 실제 키로 교체 필요)
```bash
<!-- signup.jsp -->
<c:set var="serviceKey" value="SERVICE_KEY" />
```

### DB (SQL Developer)
- https://www.notion.so/SQL-25eebb90731b807c9839f75ac6eb1aaa
<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 🎨 Output 🎨-->
<details>
<summary><h2> 🎨 Output 🎨 </h2></summary>
<hr noshade>
  
- ### Wireframe (Figma)
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/14677861-3d4f-42b3-a3d7-faf01af6f1c7" />
  
- ### ERD (ERD Cloud)
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/3c46b3b3-7107-4f38-ae9a-f115287ab085" />

<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## ✨ Project UI & Feature Overview ✨-->
<details open>
<summary><h2> ✨ Project UI & Feature Overview ✨ </h2></summary>
<hr noshade>

**[ 로그인 ]**

<img width="800" height="600" alt="screencapture-localhost-8080-members-login-2025-09-22-17_47_44" src="https://github.com/user-attachments/assets/f1ba63ff-985b-4e89-a71d-42f72285d128" />
<br>

- 개인/기업 회원 로그인
<br>


**[ 회원가입 ]**

<img width="800" height="600" alt="screencapture-localhost-8080-members-signup-2025-09-22-17_48_28" src="https://github.com/user-attachments/assets/5abbd7a7-ce98-49ec-83a8-b047a0d82fa1" />
<br>

- 개인/기업 회원 로그인
- 기업회원은 회원가입 시 실제 사업자 등록번호인지 OpenAPI를 통해 인증해야 함
<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 🔧 Troubleshooting 🔧-->
<details>
<summary><h2> 🔧 Troubleshooting 🔧 </h2></summary>
<hr noshade>

<br>
</details>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--## 📂 File Structure 📂-->
<details>
<summary><h2> 📂 File Structure 📂 </h2></summary>
<hr noshade>

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂magnifier
 ┃ ┃ ┃ ┃ ┣ 📂applylist
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApplylistController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EnterpriseApplylistDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberApplylistDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Applylist.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApplylistInsertMapper.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApplylistMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApplylistService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApplylistServiceImpl.java
 ┃ ┃ ┃ ┃ ┣ 📂enterprise
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EnterpriseApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterpriseController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateEnterpriseRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindEnterpriseResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateEnterpriseRequest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Enterprise.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterpriseMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EnterpriseService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterpriseServiceImpl.java
 ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckIdRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateMemberRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindMemberResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateMemberRequest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberServiceImpl.java
 ┃ ┃ ┃ ┃ ┣ 📂recruit
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Recruit.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitServiceImpl.java
 ┃ ┃ ┃ ┃ ┣ 📂resume
 ┃ ┃ ┃ ┃ ┃ ┣ 📂career
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CareerDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CareerMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂license
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LicenseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LicenseMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResumeService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeServiceImpl.java
 ┃ ┃ ┃ ┃ ┗ 📂security
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommonController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomEnterprise.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomMember.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomAccessDeniedHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomLoginSuccessHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsServiceEnterprise.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomUserDetailsServiceMember.java
 ┃ ┣ 📂resources
 ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┗ 📂magnifier
 ┃ ┃ ┃ ┃ ┣ 📂applylist
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApplylistInsertMapper.xml
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ApplylistMapper.xml
 ┃ ┃ ┃ ┃ ┣ 📂enterprise
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterpriseMapper.xml
 ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberMapper.xml
 ┃ ┃ ┃ ┃ ┣ 📂recruit
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitMapper.xml
 ┃ ┃ ┃ ┃ ┗ 📂resume
 ┃ ┃ ┃ ┃ ┃ ┣ 📂career
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CareerMapper.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📂license
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LicenseMapper.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeMapper.xml
 ┃ ┃ ┣ 📜log4j.xml
 ┃ ┃ ┗ 📜log4jdbc.log4j2.properties
 ┃ ┗ 📂webapp
 ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📜applylist.css
 ┃ ┃ ┃ ┃ ┣ 📜common.css
 ┃ ┃ ┃ ┃ ┣ 📜login.css
 ┃ ┃ ┃ ┃ ┣ 📜logout.css
 ┃ ┃ ┃ ┃ ┣ 📜recruit-detail.css
 ┃ ┃ ┃ ┃ ┣ 📜recruit.css
 ┃ ┃ ┃ ┃ ┣ 📜resume.css
 ┃ ┃ ┃ ┃ ┗ 📜signup.css
 ┃ ┃ ┃ ┗ 📂images
 ┃ ┃ ┃ ┃ ┗ 📜logo.png
 ┃ ┃ ┗ 📂WEB-INF
 ┃ ┃ ┃ ┣ 📂spring
 ┃ ┃ ┃ ┃ ┣ 📂appServlet
 ┃ ┃ ┃ ┃ ┃ ┗ 📜servlet-context.xml
 ┃ ┃ ┃ ┃ ┣ 📜root-context.xml
 ┃ ┃ ┃ ┃ ┗ 📜security-context.xml
 ┃ ┃ ┃ ┣ 📂views
 ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┗ 📜navbar.jsp
 ┃ ┃ ┃ ┃ ┣ 📂enterprises
 ┃ ┃ ┃ ┃ ┃ ┣ 📜applylist.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜login.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜memberResume.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mypage.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.jsp
 ┃ ┃ ┃ ┃ ┣ 📂members
 ┃ ┃ ┃ ┃ ┃ ┣ 📜applylist.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜login.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜mypage.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📜signup.jsp
 ┃ ┃ ┃ ┃ ┣ 📂recruits
 ┃ ┃ ┃ ┃ ┃ ┣ 📜detail.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜list.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜listById.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜modify.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📜register.jsp
 ┃ ┃ ┃ ┃ ┣ 📂resumes
 ┃ ┃ ┃ ┃ ┃ ┣ 📜form.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📜resume.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📜view.jsp
 ┃ ┃ ┃ ┃ ┗ 📜accessError.jsp
 ┃ ┃ ┃ ┗ 📜web.xml
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂magnifier
 ┃ ┃ ┃ ┃ ┣ 📂applylist
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberApplylistMapperTest.java
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DataSourceTests.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜JDBCTests.java
 ┃ ┃ ┃ ┃ ┣ 📂enterprise
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EnterpriseMapperTest.java
 ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberMapperTest.java
 ┃ ┃ ┃ ┃ ┣ 📂recruit
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitMapperTest_delete.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitMapperTest_detail.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitMapperTest_list.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitMapperTest_listById.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitMapperTest_modify.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitMapperTest_register.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitServiceTest_delete.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitServiceTest_detail.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitServiceTest_list.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitServiceTest_listById.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitServiceTest_modify.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitServiceTest_register.java
 ┃ ┃ ┃ ┃ ┣ 📂resume
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeControllerTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CareerMapperDeleteTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CareerMapperModifyTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CareerMapperTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LicenseMapperDeleteTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LicenseMapperModifyTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LicenseMapperTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResumeMapperModifyTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeMapperTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResumeServiceModifyTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResumeServiceTest.java
 ┃ ┃ ┃ ┃ ┗ 📂security
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EnterpriseTests.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberTests.java
 ┃ ┗ 📂resources
 ┃ ┃ ┗ 📜log4j.xml
```

<br>
</details>
