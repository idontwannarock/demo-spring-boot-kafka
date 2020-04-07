This is a demo and test project for Kafka integration with Spring Boot.

# TL;DR

必須先安裝並設定好 Java 11 版本及 Git 環境變數，再依序執行以下命令

```shell script
git clone git@github.com:idontwannarock/demo-spring-boot-kafka.git
cd demo-spring-boot-kafka
mkdir "src/main/resources/config"
cp src/main/resources/application.yml src/main/resources/config/application.yml
```

接著請用任何文字編輯器打開 `src/main/resources/config/application.yml` 檔案，並將其中所有以 `${}` 包括的參數改正

若已安裝並設定好 Maven 環境變數，可以直接執行以下命令來運行

```shell script
mvn spring-boot:run
```

若沒有安裝 Maven 且環境為 Windows，執行以下命令來運行

```shell script
./mvnw.cmd -N io.takari:maven:wrapper
mvn spring-boot:run
```

若沒有安裝 Maven 且環境為 Unix-like，則執行以下命令來運行

```shell script
./mvnw -N io.takari:maven:wrapper
mvn spring-boot:run
```

即可在瀏覽器開啟 [Swagger API文件](http://localhost:8080/swagger-ui.html#/) 來查詢並使用 API