# Лаборатори №9 — JaCoCo код хамралт

- Оюутны нэр: Өгөгдөөгүй
- Оюутны код: B232270071 (локал Git тохиргооноос)
- Орчин: Windows 11, OpenJDK 25.0.2, Maven 3.9.16

`java -version` гаралт:

```text
openjdk version "25.0.2" 2026-01-20 LTS
OpenJDK Runtime Environment Temurin-25.0.2+10 (build 25.0.2+10-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.2+10 (build 25.0.2+10-LTS, mixed mode, sharing)
```

`mvn -version` гаралт:

```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\Program Files\Apache\Maven\apache-maven-3.9.16
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: C:\Users\User\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.2.10-hotspot
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

## A — Happy path ба эхний хэмжилт

Эхний таван хэвийн урсгалын тестээр Line хамралт 15/25 = **60.0%**, Branch хамралт 20/41 = **48.8%** байв (`results/jacoco-A.csv`). Тайланд шарласан хэсгүүдэд ангиллын босгууд болон тэтгэлгийн нөхцөлийн зарим үр дүн дутуу байсан. Улаан мөрүүдэд онооны бусад ангилал, хүчингүй оролт, сахилгын зөрчил болон GPA тооцооны зарим замд тест хүрээгүй байлаа.

## B — CFG ба 100% хамралт

`cfg.png` нь `scholarship()` функцийн нөхцөл, салбар бүрийг харуулж, ирмэгүүдэд тестийн бодит методын нэрийг бичсэн. Branch ба Line хамралтыг 100% болгоход **23 тест** шаардсан (`results/mvn-verify-B.txt`). C үеийн давталтын гурван нэмэлт тест хамралтын тоог өсгөөгүй. `results/jacoco-B.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0`.

## C — Давталтын зааг

`gpaNullArray` ба `gpaEmptyArray` нь 0 давталтын оролтуудыг `assertThrows`-оор шалгана; `oneIterationGpa` нь нэг дүнтэй, `multipleIterationGpa` нь олон дүнтэй массивыг шалгана. Нэмэлт `zeroIterationIsRejected` тест хоосон массивын алдааг давтан тодруулна. Хамралт өмнө нь 100% байсан тул эдгээр тестийн хамралтын хувь өсөхгүй ч давталтын 0/1/олон удаагийн ажиллагаа болон хоосон/null оролтын тодорхойлолтыг хамгаална.

## D — Хамралт 100% ч заагийн алдаа үлдсэн нь

`results/mvn-test-D-fail.txt`-д `ScholarshipCalculatorTest.gradeBoundary60IsD` тест унаж, `expected: <D> but was: <F>` гэж гарсан. `letterGrade` дахь `score > 60` харьцуулалтыг `score >= 60` болгон зассан; засварын commit нь `3633502` (`Fix letterGrade threshold: 60 is D per specification`).

Branch хамралт нь шийдвэрийн хоёр тал руу орсон эсэхийг хэмждэг болохоос харьцуулах заагийн яг аль тал зөв эсэхийг хэмждэггүй. `score > 60` болон `score >= 60` нь оноонууд зөвхөн 60-аас доош, дээш үед ижил салаануудыг туулж чадна. Ийм учраас бүх салаа хамрагдсан ч 60 дээрх спецификацийн алдаа илрээгүй. `letterGrade(60)` зэрэг заагийн тест л ялгааг илрүүлж, засварын дараа 33 тест бүгд амжилттай болсон (`results/mvn-verify-final.txt`). Эцсийн тайланд Branch ба Line-ийн missed тоо хоёулаа 0 (`results/jacoco-final.csv`).

## Git явц ба AI тусламж

Коммитын дараалал: A happy path → B бүрэн салбарын хамралт → C давталтын зааг → D алдааг илрүүлсэн тест → D засвар. AI туслахыг төслийн ажлын файл, тест, CFG болон тайланг бэлтгэхэд ашигласан; build болон JaCoCo тайланг Maven-оор ажиллуулж шалгасан.

