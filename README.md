<img width="350" height="200" alt="logo" src="https://github.com/user-attachments/assets/b21ef912-bb7f-4cb8-b488-964bdad05ee0" />

# 돋보기 프로젝트
- __프로젝트 명__ : 돋보기 (Magnifier)

- __프로젝트 소개__ : 시니어들을 위한 취업 지원 서비스, 돋보기

- __프로젝트 목표__ : 큰 글씨, 쉬운 UI/UX, 시니어 맞춤 공고로 디지털 환경이 익숙하지 않은 시니어 구직자들에게 쉽고 편리한 구직 환경 제공

- __주요 기능__ :
  - 개인/기업 로그인
  - 개인/기업 회원가입
  - 로그아웃
  - 전체 채용 공고 목록 조회 & 기업별 등록한 채용 공고 목록 조회 & 공고 상세 조회
  - 공고 등록 & 수정 & 삭제
  - 공고 지원하기
  - 공고 지원내역
  - 이력서 등록 & 수정
  - 이력서 상세 조회
  - 내 정보

- __개발 기간__ : 2025.08. 25~ 2025.09.16 (약 3주간)

- __프로젝트 블로그__ : [노션_돋보기 (Magnifier)](https://www.notion.so/KOSA-2-25aebb90731b80778e09d9c5b46d1451)

- __발표 자료__ : [Canva_프로젝트 PPT](https://www.canva.com/design/DAGzBL_Y42o/XN6zYoycwCQ4e13muPccbw/edit)

- __시연 영상__ : <영상 추가>
<br>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--팀원 소개-->
## Team "ZoomIn"
&nbsp;&nbsp;&nbsp;&nbsp;KOSA_2차 프로젝트 팀 ZOOMIN

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: 사회적 취약 계층인 시니어에게 관심을 기울이고, 그들의 어려움도 확대하여 세심하게 들여다보겠다는 의지를 담음
<br>
<br>

&nbsp;&nbsp;&nbsp;&nbsp;__[ 팀원 소개 ]__

|<img src="https://avatars.githubusercontent.com/u/97112125?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/109258144?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/219790960?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|
|Kiseong Kim<br/>[@kks1177](https://github.com/kks1177)|kimkyongah<br/>[@hagnoykmik](https://github.com/hagnoykmik)|lsw1096<br/>[@leesangwoo-dev](https://github.com/leesangwoo-dev)|
<br>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--개발 환경-->
## 개발 환경 (버전참고)
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
<br>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--실제 구현 화면/기능-->
## 실제 구현 화면/기능

**[ 로그인 ]**

<img width="45%" alt="members-login-page" src="https://github.com/user-attachments/assets/f1ba63ff-985b-4e89-a71d-42f72285d128" />
<img width="45%" alt="enterprises-login-page" src="https://github.com/user-attachments/assets/3490a4ff-3843-4de9-b9d8-0ea0598fee37" />
<br>

- 개인/기업 회원 로그인
<br>


**[ 회원가입 ]**

<img width="45%" alt="members-signup" src="https://github.com/user-attachments/assets/5abbd7a7-ce98-49ec-83a8-b047a0d82fa1" />
<img width="45%" alt="enterprises-signup" src="https://github.com/user-attachments/assets/d6f14a1f-0076-40c9-bf4d-3efe584e7961" />
<br>

- 개인/기업 회원 로그인
- 기업회원은 회원가입 시 실제 사업자 등록번호인지 OpenAPI를 통해 인증해야 함
<br>


**[ 채용 공고 ]**

<img width="45%" alt="member-recruits-list" src="https://github.com/user-attachments/assets/c747b6af-550e-4377-8a13-dd4b867464e8" />
<img width="45%" alt="enterprise-recruits-list" src="https://github.com/user-attachments/assets/90ed3e1d-7967-4cf2-ad4e-e3621f09b286" />
<br>

- <설명>
- <설명>
<br>


**[ 공고 관리 ]**

<img width="45%" alt="enterprise-recruits-listbyid" src="https://github.com/user-attachments/assets/55c82745-365b-43be-8efd-58eabe0dd505" />
<img width="45%" alt="enterprise-recruits-listbyid" src="https://github.com/user-attachments/assets/5398fa14-cf52-4377-81a1-813bed519481" />
<img width="45%" alt="enterprise-recruits-register" src="https://github.com/user-attachments/assets/45aa7ede-3b14-47f2-b0a6-755b2457be80" />
<img width="45%" alt="공고 등록 성공" src="https://github.com/user-attachments/assets/8a0f4436-b629-486d-b417-e9a34af033d4" />
<img width="45%" alt="삭제할 공고 선택" src="https://github.com/user-attachments/assets/5312f9f5-06e9-4c2c-89e7-63d25b807a7d" />
<img width="45%" alt="공고 삭제 성공" src="https://github.com/user-attachments/assets/86afc053-a379-45a7-8403-bfdfee2f0704" />
<br>

- <설명>
- <설명>
<br>


**[ 공고 상세 ]**

<img width="45%" alt="enterprise-recruits-detail" src="https://github.com/user-attachments/assets/22d1ba5d-1004-42e6-bc56-65b70f10286f" />
<img width="45%" alt="enterprise-recruits-modify" src="https://github.com/user-attachments/assets/22f27e51-1e4a-40b1-b847-98a3e5cfb1c2" />
<br>

- <설명>
- <설명>
<br>


**[ 공고 지원하기 ]**

<img width="45%" alt="member-recruits-detail" src="https://github.com/user-attachments/assets/16808107-e091-4c3d-9751-33f60a8dad6a" />
<img width="45%" alt="공고 지원 성공" src="https://github.com/user-attachments/assets/c6e7c381-82b0-43c7-85c8-40d414aa59c0" />
<img width="45%" alt="이미 지원한 공고 지원 시도(실패)" src="https://github.com/user-attachments/assets/40461654-aa08-4894-977b-e64c84363907" />
<br>

- <설명>
- <설명>
<br>


**[ 공고 지원내역 ]**

<img width="45%" alt="members-applylist" src="https://github.com/user-attachments/assets/fc3980fa-134e-4ac1-b95a-e3fefd0da02d" />
<img width="45%" alt="enterprises-applylist" src="https://github.com/user-attachments/assets/30069d0c-3c7c-4cf2-a3fd-1509531d8750" />
<img width="45%" alt="member-recruits-detail" src="https://github.com/user-attachments/assets/885bb9c8-483d-499d-8359-b099cea4f40d" />
<img width="45%" alt="이미 지원한 공고 지원 시도(실패)" src="https://github.com/user-attachments/assets/0b3ba4ba-8c53-416b-9e3a-0eafd1a825f7" />
<br>

- <설명>
- <설명>
<br>


**[ 이력서 관리 ]**

<img width="45%" alt="member-resumes" src="https://github.com/user-attachments/assets/63f63c91-af5b-42bc-90c1-144ec2f47358" />
<img width="45%" alt="member-resumes-register" src="https://github.com/user-attachments/assets/1fb0d737-5b4c-4c55-bbec-a80bbcd18338" />
<img width="45%" alt="경력사항 추가" src="https://github.com/user-attachments/assets/ba52aa76-e7e9-4347-b2ee-ec09c7efbc6f" />
<img width="45%" alt="자격사항 추가" src="https://github.com/user-attachments/assets/1138d0c8-359a-4255-acbc-b406030de4ea" />
<img width="45%" alt="이력서 추가 성공" src="https://github.com/user-attachments/assets/00507c83-f17b-4c1d-86f2-40296249ba76" />
<br>

- <설명>
- <설명>
<br>


**[ 이력서 상세 조회 ]**

<img width="45%" alt="enterprises-resume" src="https://github.com/user-attachments/assets/cbd6afd0-ff1b-4783-b12c-5a793503b353" />
<br>

- <설명>
- <설명>
<br>


**[ 내 정보 ]**

<img width="45%" alt="members-mypage" src="https://github.com/user-attachments/assets/5c69aabb-80a9-4416-bf3d-c5e5531dddf5" />
<img width="45%" alt="enterprises-mypage" src="https://github.com/user-attachments/assets/c83e0a89-c9a4-46f0-9079-2a8c88aef5ce" />
<br>

- <설명>
- <설명>
<br>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--컨벤션-->
## 컨벤션

- ### 코딩 컨벤션
<img width="773" height="832" alt="image" src="https://github.com/user-attachments/assets/52143d95-d8cb-445f-8924-e3ca22da527b" />

- ### 깃 컨벤션
<img width="733" height="754" alt="image" src="https://github.com/user-attachments/assets/d5114bda-c205-4b78-a5b1-c6719ed2a7df" />
<br>
<br>



<!-- --------------------------------------------------------------------------------------------------------------- -->
<!--산출물-->
## 산출물

- ### Notion ([돋보기_ZoomIn 노션 바로가기](https://purple-neem-6c1.notion.site/KOSA-2-25aebb90731b80778e09d9c5b46d1451?source=copy_link))
<img width="1920" height="2030" alt="image" src="https://github.com/user-attachments/assets/098c3c85-21c6-4e49-9b53-fb9f3e20eac0" />

- ### 기능 명세서
<img width="1552" height="814" alt="image" src="https://github.com/user-attachments/assets/e41b22cc-97da-47c4-8b85-32802eb5dbd2" />

- ### 시스템 아키텍처
![Image](https://github.com/user-attachments/assets/93942e96-788d-408a-95af-324f34fe0974)

- ### Wireframe (Figma)
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/14677861-3d4f-42b3-a3d7-faf01af6f1c7" />
  
- ### ERD (ERD Cloud)
<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/3c46b3b3-7107-4f38-ae9a-f115287ab085" />
