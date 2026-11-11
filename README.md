# Лаборатори №9 — JaCoCo код хамралт

- Оюутан: О. Доржсамбуу
- Оюутны код: B232270071
- Орчин: Windows 11, OpenJDK 25.0.2, Maven 3.9.16

`java -version`:

```text
openjdk version "25.0.2" 2026-01-20 LTS
OpenJDK Runtime Environment Temurin-25.0.2+10 (build 25.0.2+10-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.2+10 (build 25.0.2+10-LTS, mixed mode, sharing)
```

`mvn -version`:

```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\Program Files\Apache\Maven\apache-maven-3.9.16
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: C:\Users\User\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.2.10-hotspot
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", arch: "amd64", family: "windows"
```

## A — Эхний хэмжилт

Эхний таван happy-path тестээр Line хамралт 15/25 = **60.0%**, Branch хамралт 20/41 = **48.8%** байв (`results/jacoco-A.csv`). Тайланд шарласан хэсгүүдэд онооны ангилал болон тэтгэлгийн шийдвэрийн зарим салаа дутуу байсан. Улаан мөрүүдэд хүчингүй оролт, зөрчил, бусад дүнгийн ангилал болон GPA-ийн замууд тестгүй байлаа.

## B — Бүрэн branch хамралт

[`cfg.png`](cfg.png) нь `scholarship()`-ийн нөхцөл ба салааг харуулж, ирмэг бүр дээр тестийн методын нэрийг тэмдэглэсэн. Бүрэн branch/line хамралтад 23 тест шаардсан (`results/mvn-verify-B.txt`). `results/jacoco-B.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0`.

## C — Давталтын зааг

`gpaNullArray` болон `gpaEmptyArray` нь 0 удаагийн давталтын null/хоосон оролтыг `assertThrows`-оор шалгана. `oneIterationGpa` нь 1, `multipleIterationGpa` нь 2+ элементийн тохиолдлыг шалгаж, `zeroIterationIsRejected` хоосон массивын exception-ийг давхар батална. Эдгээр тест хамралтын хувийг өсгөхгүй ч массивын хязгаарын ажиллагааг баталгаажуулна.

## D — Хамралт 100% ч алдаа үлдсэн нь

`results/mvn-test-D-fail.txt`-д `ScholarshipCalculatorTest.gradeBoundary60IsD` тест `expected: <D> but was: <F>` гэж унасан. `letterGrade` дахь `score > 60` нөхцөлийг спецификацийн дагуу `score >= 60` болгон зассан.

Branch coverage нь шийдвэрийн хоёр талд тест хүрсэн эсэхийг хэмждэг болохоос харьцуулах операторын зааг зөв эсэхийг баталдаггүй. `score > 60` болон `score >= 60` нь зааг болох яг 60-аас бусад оноон дээр ижил branch-үүдээр явах боломжтой. Тиймээс 100% branch хамралт байсан ч 60 онооны алдаа үлдэж, `letterGrade(60)` тест л илрүүлсэн. Засварын дараа 33 тест бүгд ногоон болж, `results/jacoco-final.csv`-д Branch ба Line missed хоёулаа 0 болсон.

## Коммит ба AI ашиглалт

Коммитын дараалал A → B → C → D алдааг илрүүлсэн тест → D засвар байна. JaCoCo хувилбарыг Java 25-тэй нийцүүлэхээр 0.8.14 болгосон; энэ хувилбар Java 25-ыг албан ёсоор дэмждэг. Хиймэл оюун ухаанаар тест болон тайлан бэлтгэхэд туслуулсан; Maven build болон тайланг бодитоор ажиллуулж шалгасан.
