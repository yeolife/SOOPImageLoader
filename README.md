# SOOP 모바일SDK ImageLoader 사전과제

## ☁️프로젝트 설명
<a href="https://developer.android.com/topic/libraries/architecture/paging/v3-network-db?hl=ko">RemoteMediator</a>를 활용해 Pagination을 지원하는 로컬DB 기반 '커스텀 디스크 캐싱'을 구현했습니다.


<br>

## 🚥실행 방법

- Android Studio Koala 2024.1.1
- 최소 SDK(minSdk): 24
- 대상 SDK(targetSdk): 35
- JDK 버전: Java 17

<br>아래 Git 주소에서 소스파일을 받습니다.
```
git clone https://github.com/yeolife/SOOPImageLoader.git
cd SOOPImageLoader
```
<br>SOOPImageLoader(가장 상위 폴더)에서 메모장으로 local.properties를 생성하고 아래 내용을 붙여넣기 합니다.
```
baseUrl="https://sch.sooplive.co.kr/"
```
<br>Android Studio 상단에 'Sync Project with Gradle'(Ctrl+Shift+O) 버튼으로 빌드 후, 앱을 실행(Shift+F10)하시면 됩니다!
<br><br>

## ⚡사용한 기술 스택

<img src="https://github.com/user-attachments/assets/81be5aa2-ac2e-4cf3-aab4-715388ab9a05" width="800" /> <br>
<img src="https://github.com/user-attachments/assets/bd381db1-2137-4e8d-bafd-b097165e4159" width="800" />

- ViewModel: 화면 회전 등 Fragment의 생명주기에 영향을 받지 않으면서 데이터를 유지하기 위해 사용했습니다.
- Hilt: 의존성 주입을 통해 클래스 간 결합도를 줄이고, Data Layer의 객체 생성을 효율적으로 관리하기 위해 사용했습니다.
- Paging3: RemoteMediator로 효율적인 데이터 로드와 로컬 캐싱을 동시에 하기 위해 사용했습니다.
- Room: PagingSource를 기반으로 한 로컬 캐싱을 위해 사용했습니다.
- Glide: 이미지 로딩 최적화와 메모리 캐싱을 위해 사용했습니다.
- Retrofit: SOOP 썸네일 홈페이지의 데이터를 API로 호출하기 위해 사용했습니다.
- Gson: API로 받아온 Json 데이터를 Kotlin 객체로 변환하기 위해 사용했습니다.
- Flexbox: 썸네일 태그 리스트를 UI에 가시성 있게 표시하기 위해 사용했습니다.
- SwiperefreshLayout: RecyclerView에서 사용자가 직접 데이터를 세로고침할 수 있도록 하기 위해 사용했습니다.

<br>

## 💁‍♂️구현 기능 목록
### Caching <br>
<img src="https://github.com/user-attachments/assets/cab3460e-be61-44b9-ac6d-d4de13b4c8e7" width="320" /> <br>
### Pagination <br>
<img src="https://github.com/user-attachments/assets/4ececeb7-7246-48ba-a741-216c7b1a4034" width="320" /> <br>
### 가로/세로 회전 <br>
<img src="https://github.com/user-attachments/assets/c004bd94-dec1-4631-8738-6f991db5f50d" width="320" /> <br><br>
