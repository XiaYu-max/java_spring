# java_spring 學習紀錄
## 1.vs code 安裝過程(下載所需jdk檔)
### Java 擴充包
```Java
Language Support for Java(TM) by Red Hat
Java Extension Pack
Debugger for Java
Maven for Java
```
### spring boot 擴充包
```Java
Spring Initializr Java Support
Spring Boot Tools
Spring Boot Dashboard
```
### 參考資料
<https://ithelp.ithome.com.tw/articles/10217531>

## 2.快速建立檔案與過程
### 快速建立專案的連結
<https://start.spring.io/>
Lombok、Web、H2、JPA等
### VS code建立
* CTRL + SHIFT + P, 開啟Command
* 看到Spring Initializr: xxx 可選擇Maven或Gradle
* 跳出Java, Kotlin, Groovy，則一選擇
* 輸入GroupId: com.lwdevelop
* 輸入ArtifactId: backend
* Spring Boot Version 選擇
* dependencies選擇Lombok、Spring Web Starter、H2 Database、Spring Data JPA等
* 選擇完按Enter, 選要儲存專案的資料夾
## 3. 建立簡單spring thymeleaf web順序
* 建立controller的Java檔 & class
* 確認有無thymleaf
```Java
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
* 可在templates建立Thymeleaf的.html
* 建立controler、model、dao、service的java檔
  * controler : 負責外界(ex:網頁)與service的溝通
  * service : 使用應用程式的功能都寫在於此，除了curd的功能函數之外，驗證碼或郵件處理功能都在此
  * dao : 主要與資料庫溝通的橋樑，基本上處理有關curd的細部功能撰寫，所以可能需要寫有關SQL語法
* 建立ORM
  * ORM利用了Model數據層與數據訪問層做了關聯
  * Spring Data JPA優點
    * 可以配置多個DataSource來源
    * 多個資料表查詢
    * 可自訂SQL查詢
    * 擁有分頁與排序的功能
## 參考資料與bug紀錄
* 參考資料
  * https://spring.io/guides/gs/spring-boot/    #官方 
  * https://jovepater.com/article/category/series/java-spring-boot-restfulapi/		#Java Spring Boot開發Restful API
  * https://ithelp.ithome.com.tw/users/20107812/ironman/1538     #30天學習Spring MVC 系列
* bug維修紀錄
  * 解決mavan的dependency之javax
    * 改版重點<https://stackoverflow.com/questions/71179660/spring-boot-3-jakarta-and-javax>
    * 詳細修改內容<https://stackoverflow.com/questions/75337506/beancreationexception-error-creating-repository-with-spring-boot-not-a-manage>
    * 改用jakarta
    * 只需寫這行`import jakarta.persistence.*`
    * 依賴設定
    ```Java
    <dependency>
        <groupId>org.hibernate.javax.persistence</groupId>
        <artifactId>hibernate-jpa-2.1-api</artifactId>
        <version>1.0.0.Final</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.1.5.Final</version>
    </dependency>
    ```
  * 解決mavan的dependency連mysql <https://www.javaguides.net/2019/11/mysql-connector-java-maven-dependency.html    >
    
    
