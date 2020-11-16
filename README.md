## 삼성청년SW아카데미(SSAFY) 관통 프로젝트

### <기능구현 정의서> (2020.11.09)


1. 메인페이지
메뉴 NavBar과 간단한 소개페이지 + 기능별 링크
---------------------------------------------------------------------------------
2. 실거래가 조회
- 실거래가 페이지는 구/동 등을 선택 시 해당 지역 아파트 리스트 제공 (구글 맵스 API 이용)

3. 실거래가 상세 조회
- 원하는 정보 클릭 시, housedeal 테이블 에서 번호, 동이름, 아파트명, 시세정보 리스트를 제공
- Ajax를 이용해 Modal창에 각각 출력하여 확인.
- table 형식으로 보기 쉽게 표시.
- 관심지역 등록버튼 클릭 시 등록.

4. 관심지역 조회
- 로그인 시 관심지역 조회 사용가능
- 관심지역으로 등록된 정보만 화면에 출력
- 클릭 시 거래상세정보를 Modal을 확인.
- 삭제 버튼 클릭 시 관심지역에서 삭제.

5. 공지사항
- 메인페이지 오른쪽 하단부에 공지사항 구현
- MORE INFO클릭 시 공지사항 페이지로 이동
- 공지사항 글쓰기, 수정, 삭제기능은 관리자만 사용가능.(admin)
- 페이징을 활용하여 글이 10개 이상일 경우 다음 페이지로 넘어감.
- 검색기능을통해 원하는 글을 볼 수 있음(아이디, 글제목, 번호)
- 메인 페이지에 공지사항 글 3~4개 미리보기(최신순)

6. 사이트 맵
사이트 전체  정보를 Drow.io를 사용하여 보기쉽게 제공함.

7. 전체 UI 개선
- 이미지와 버튼을 추가하여 클릭 시 해당 페이지로 이동