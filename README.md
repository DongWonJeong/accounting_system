# :bookmark_tabs: 정산 시스템 프로젝트
본 프로젝트는 유튜브를 모티브로 한 스트리밍 서비스의 판매자를 위한 정산 시스템을 개발하는 것을 목표로 합니다. <br>
판매자에게 유용한 정산 및 통계 기능을 제공하여 효율적인 관리가 가능하도록 합니다.
<br>
<br>

# :pushpin: 기술 스택
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
<br>
<br>

# :link: 사용 기술 & 기술적 의사결정
| 사용 기술 | 결정 이유 |
| --- | --- |
| Spring Boot | 스프링 기반 어플리케이션을 더 빠르고 쉽게 개발할 수 있도록 해주는 오픈소스 프로젝트이기 때문에 <br> 사용했습니다. |
| GitHub Actions | CI/CD 자동화를 통해 main branch에 이벤트 발생 시 자동으로 빌드 및 테스트를 수행하도록 하기 위해 <br> 사용했습니다. |
| docker | 애플리케이션과 의존성을 컨테이너화하여 일관성 있는 환경을 유지하고 배포를 간편하게 하기 위해 <br> 사용했습니다. |
| jwt | 사용자가 로그인하면 서버가 JWT를 발급하고, 이후 요청에서 이 토큰을 통해 사용자를 인증하기 위해 <br> 사용했습니다. |
| Spring Security | Spring Security는 JWT를 검증하고, 사용자의 권한을 확인하여 요청에 대한 접근을 제어하기 위해 <br> 사용했습니다. |
| mysql | 사용자 정보, 비디오 정보, 광고 정보 등을 안전하고 일관성 있게 저장하기 위해 <br> 사용했습니다. |
<br>

# :open_file_folder: 아키텍쳐
<details>
<summary>프로젝트 구조</summary>
<div markdown="1">

accounting_system <br>
├─.github <br>
│  └─workflows <br>
│			└─workflows <br>
│			  └─gradle.yml <br>
├─common <br>
│  ├─src <br>
│  │   ├─main <br>
│  │   │  ├─java <br>
│  │   │  │  └─com <br>
│  │   │  │      └─sparta <br>
│  │   │  │          └─exception <br>
│  │   │  └─resources <br>
│  │   └─test <br>
│  │       ├─java <br>
│  │       └─resources <br>
│  └─build.gradle <br>
├─streaming_service <br>
│  ├─src <br>
│  │   ├─main <br>
│  │   │  ├─java <br>
│  │   │  │  └─com <br>
│  │   │  │      └─sparta <br>
│  │   │  │          ├─controller <br>
│  │   │  │          ├─dto <br>
│  │   │  │          │  ├─ad <br>
│  │   │  │          │  │  ├─info <br>
│  │   │  │          │  │  ├─register <br>
│  │   │  │          │  │  └─upload <br>
│  │   │  │          │  └─video <br>
│  │   │  │          │      ├─completion <br>
│  │   │  │          │      ├─info <br>
│  │   │  │          │      ├─play <br>
│  │   │  │          │      ├─stop <br>
│  │   │  │          │      └─upload <br>
│  │   │  │          ├─entity <br>
│  │   │  │          ├─repository <br>
│  │   │  │          └─service <br>
│  │   │  └─resources <br>
│  │   └─test <br>
│  │       ├─java <br>
│  │       └─resources <br>
│  ├─ build.gradle <br>
│  └─ dockerfile <br>
├─user_service <br>
│   ├─src <br>
│   │  ├─main <br>
│   │  │  ├─java <br>
│   │  │  │  └─com <br>
│   │  │  │      └─sparta <br>
│   │  │  │          ├─config <br>
│   │  │  │          ├─controller <br>
│   │  │  │          ├─dto <br>
│   │  │  │          │  ├─info <br>
│   │  │  │          │  ├─login <br>
│   │  │  │          │  └─signUp <br>
│   │  │  │          ├─entity <br>
│   │  │  │          ├─jwt <br>
│   │  │  │          ├─repository <br>
│   │  │  │          └─service <br>
│   │  │  └─resources <br>
│   │  └─test <br>
│   │      ├─java <br>
│   │      └─resources <br>
│   ├─ build.gradle <br>
│   └─ dockerfile <br>
│       <br>
├─buil.grade <br>
├─docker-compose.yml <br> 
├─gradlew <br>
├─gradlew.bat <br>
├─HELP.md <br>
└─settings.gradle <br>

</div>
</details>
<br>

# :bulb: 주요 기능
1. 회원 서비스
   - 회원가입 / 로그인 / 로그아웃 기능 제공
   - 사용자 계정 & 판매자 계정 권한 구분
2. 비디오 서비스
   - 등록된 비디오 재생
   - 재생 시 비디오 조회수 증가
   - 비디오 중단 시 현재까지 재생한 시점 저장
3. 광고 서비스
   - 동영상에 한개 이상의 광고 영상 등록
   - 광고 조회수 증가
4. 통계 및 정산 데이터 서비스
   - 1일, 1주일, 1달 동안 조회수가 높은 동영상 TOP5 제공
   - 1일, 1주일, 1달 동안 재생 시간이 긴 동영상 TOP5 제공
   - 동영상 정산 금액 계산 및 제공
     - 업로드 영상 정산 금액 및 광고 영상 정산 금액 합산
     - 조회수에 따라 영상별 단가 및 광고별 단가 적용
     - 1일, 1주일, 1달등의 정산 금액 조회 기능 제공    
