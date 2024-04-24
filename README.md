# 📝 채용 시스템 실습 프로젝트 

## 1️⃣ 프로젝트 소개
채용을 하려는 기업들과 구직을 하려는 구직자들 사이에서 좀더 쉽게 할 수 있도록 연결 시켜주는 역할을 하는 시스템

## 요구사항
- 개인이 원하는 기업의 공고에 지원할 수 있도록 해야함.
- 지원한 결과를 개인들이 확인할 수 있도록 해야함.

### 추가 요구사항
1. 지원자들 학력에 가산점 배율 지정.(Ex. 고졸 : 0, 전문대졸: 1, 대졸: 2, 석사: 3, 박사: 4)
2. 가산점 배율에 출신학교의 점수를 곱함. (Ex. 서울대학교: 10 -> 10 * 2 = 20점)
3. 총 점수를 기준 상위 (기업의 채용수 * 5배)명의 지원자를 필터링 하도록함.
<br><br>
## 2️⃣ Tech Stack
- Language : Java
- Build : gradle
- DataBase : MySQL, H2 DataBase
- JDK : JDK 17
- framework : Springboot
<br><br>  
## 3️⃣ API 명세서

### 🎯 공고등록, 조회(전체, 개별), 수정, 삭제 (기업회원만)

✅ POST /recruitments

✅ GET /recruitments (전체)

✅ GET /recruitments/{id} (개별)

✅ PUT /recruitments/{id}

✅ DELETE /recruitments/{id}
<br><br>

### 🎯 이력서 작성, 조회(전체, 개별), 수정, 삭제 (개인회원만)

✅ POST/resumes

✅ GET/resumes (전체)

✅ GET/resumes (개별)

✅ PUT/resumes/{id}

✅ DELETE/resumes/{id}
<br><br>

### 🎯 지원하기, 지원자 조회

✅ POST/ecruitments/{id}/applications (개인 회원만)

✅ GET/recruitments/{id}/applications (기원 회원만)
<br><br>
### 🎯 지원결과 확인
✅ GET/recruitments/{id}/applications/{applicationId} 
<br><br>
## 4️⃣ 회원 API
- ✅ 회원가입
- ✅ 로그인
- ✅ 로그아웃
