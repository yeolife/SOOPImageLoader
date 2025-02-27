# SOOP 모바일SDK ImageLoader 사전과제

## ☁️프로젝트 설명
<a href="https://www.sooplive.co.kr/directory/category">SOOP 홈페이지</a>의 카테고리 정보를 API로 가져와 썸네일을 표시하는 안드로이드 앱입니다.<br><br>
- `ImageLoader` <br>
  - 이미지 원격 URL 로드: 서버의 이미지 URL을 비동기로 로드하여 ImageView 표시
  - 이미지 로컬 경로 로드: 기기에 저장된 이미지 파일을 비동기로 로드하여 ImageView 표시
  - 로딩한 이미지 Bitmap 반환: 이미지를 로드한 결과를 Bitmap으로 반환
- `디스크 캐시` <br>
<a href="https://developer.android.com/topic/libraries/architecture/paging/v3-network-db?hl=ko">`RemoteMediator`</a>를 활용하여 로컬DB 기반으로 `페이지네이션`을 수행하면서, `디스크 캐시`를 지원하도록 구현했습니다. Google에서 제공하는 RemoteMediator는 서버 데이터를 페이지 단위로 가져와 로컬DB에 저장함으로써, 오프라인에서도 데이터를 유지할 수 있도록 돕는 기술입니다. 이를 활용하여 효율적인 데이터 호출과 함께, 로컬DB로 디스크 캐시 효과를 낼 수 있을 것이라 생각하여 도전했습니다.<br>
특히, `캐시`의 개념을 명확히 하기 위해 `LRU 캐싱` 정책과 유사한 방식으로 로컬 DB에 캐시 유효기간을 24시간으로 설정했고, 전체 캐시 용량이 일정 수준을 초과하면 가장 오래 사용되지 않은 데이터 20개를 우선적으로 삭제하는 정책을 적용했습니다.<br>
- `메모리 캐시` <br>
Android LruCache를 적용하여 동일한 이미지를 반복 로드하지 않게 했으며, 최근에 사용되지 않은 이미지부터 순차적으로 메모리에서 제거하도록 했습니다.
- `화면 회전` <br>
화면 회전에 따라 썸네일 레이아웃이 변경되도록 구성했습니다.
  - 세로 모드: 2열
  - 가로 모드: 4열

### 장점
| 비교 항목 | 기존 방법 | 사용한 방법 |
|----------|----------------------------------|------------------------------------|
| **네트워크 요청** | Pagination으로 항상 서버 데이터를 요청 | RemoteMediator로 로컬DB의 우선 활용을 통해 불필요한 요청 분기 처리 가능 |
| **캐시 정책** | Glide의 내부 정책에 따름 | 개발자가 직접 캐시 유효기간 및 삭제 정책 설정 |

<br>

## 🚥실행 방법

- Android Studio Koala 2024.1.1
- 최소 SDK(minSdk): 24
- 대상 SDK(targetSdk): 35
- JDK 버전: Java 17

<br>아래 Github 주소에서 소스파일을 받습니다.
```
git clone https://github.com/yeolife/SOOPImageLoader.git
cd SOOPImageLoader
```
<br>SOOPImageLoader(가장 상위 폴더)에서 local.properties에 아래 내용을 붙여넣기 합니다.
<br>압축 해제할 때 최상위 폴더가 생길 수 있습니다!
```
baseUrl="https://sch.sooplive.co.kr/"
```
<br>Android Studio 상단에 'Sync Project with Gradle'(Ctrl+Shift+O) 버튼으로 빌드 후, 앱을 실행(Shift+F10)합니다.
<br><br>

## ⚡사용한 기술 스택

<img src="https://github.com/user-attachments/assets/81be5aa2-ac2e-4cf3-aab4-715388ab9a05" width="800" /> <br>
<img src="https://github.com/user-attachments/assets/bd381db1-2137-4e8d-bafd-b097165e4159" width="800" />

- `ViewModel`: 화면 회전 등 Fragment의 생명주기에 영향을 받지 않으면서 데이터를 유지하기 위함
- `Hilt`: 의존성 주입을 통해 클래스 간 결합도를 줄이고, Data Layer의 객체 생성을 효율적으로 관리하기 위함
- `Paging3`: RemoteMediator로 효율적인 데이터 로드와 로컬 캐싱을 동시에 하기 위함
- `Room`: PagingSource를 기반으로 한 로컬 캐싱을 위함
- `Retrofit`: SOOP 썸네일 홈페이지의 데이터를 API로 호출하기 위함
- `Gson`: API로 받아온 Json 데이터를 Kotlin 객체로 변환하기 위함
- `Flexbox`: 썸네일 태그 리스트를 UI에 가시성 있게 표시하기 위함
- `SwiperefreshLayout`: RecyclerView에서 사용자가 직접 데이터를 새로고침할 수 있도록 하기 위함

<br>

## 💁‍♂️구현 기능 목록
### Caching 및 오프라인 동기화 <br>
<img src="https://github.com/user-attachments/assets/cab3460e-be61-44b9-ac6d-d4de13b4c8e7" width="320" /> <br>
### Pagination <br>
<img src="https://github.com/user-attachments/assets/4ececeb7-7246-48ba-a741-216c7b1a4034" width="320" /> <br>
### 가로/세로 회전 <br>
<img src="https://github.com/user-attachments/assets/c004bd94-dec1-4631-8738-6f991db5f50d" width="320" /> <br><br>

## ✨성능 개선

서버에서 받아온 PNG 파일을 WEBP로 변환하여 디스크 캐싱을 했습니다. 그 결과 약 50~60%의 용량 감소를 할 수 있었습니다.
|PNG|WEBP|
|-----|-----|
|<img src="https://github.com/user-attachments/assets/1afe48d5-ea20-4f75-97bb-711ebb0df657" width="500" />|<img src="https://github.com/user-attachments/assets/2170dee0-c8c0-41c2-a96e-1500f24f221c" width="500" />|

















