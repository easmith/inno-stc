# 18 апреля

### Сборщики

Ant этапы сборки
Maven конфигурирование
pom.xml
- dependency
- pluging
- build
- poperty
- artifact
- version
- ...

[mvnrepository](https://mvnrepository.com/)

### Этапы сборки
- compile
- test
- package (jar, war, ear)
- integration test
- install
- deploy

### Gradle
Как процедурный так и конфигурационный подход
Groovy

## Java EE
1) долго
2) много
3) единообразие - повторяемость
4) надежность

- Спецификация EJB (концепция, идея, правило)
- - EJB3.0
- - String Framework больше чем EJB
- Servlet API / Java server page (JSP) (JSTL) / JavaServer Faces JSF
- JAX-WS / JAX-RS
- JNDI
- Java Message Services (JMS) & Co
- Java Transaction API (JTA)
- Java Persistance API (JPA)
- - javax.persistance
- - Hibernate

### war file
- Resources
- WEB-INF
- - Классы
- - Web страница
- - Конфигурационный файл
- - JS
- - CSS
- META-INF - настройки всего приложения
- web.xml - управляет развертыванием сервера

web.xml
- <welcome-page>
- <error-page>
- <servlet>
- - <name>
- - <class>
- - <load-on-startup>
- <servlet-mapping>
- - <url-pattern>
- - <servlet>
- <listner>
- <filter> - паттерн цепочка обязанностей
- <filter-mapping> - interceptor


Все сервлеты хранятся в контейнере сервлетов, который явлется объектным пулом.


## ДЗ
1) Students CRUD в пакете models
Три вида классов:
 - pojo (два поля - id и коллекция)
 - DAO - класс который отвечает за запросы к каждой таблице (Интерфейс и имплементация StudentsDAO StudentsDAOImpl)
 - connectionPool средствами томкэта
2) dao авторизации

структура пакетов
- models
- - pojo
- - Dao
- - Connection


## как работает приложение
Filter -> servlet
forward - перенаправление внутри страницы
getParametr
setParametr
getAttribute
setAttribute


Лаба #3

- Трехслойная архитектура
- Три страницы
- - логин (AuthorizationService) фильтрация HttpSession(.getAttribute .setAttribute)  
- - регистрация
- - список
- Servlet фильтры (whiteList, AdminList)
- Logout
- ошибки












