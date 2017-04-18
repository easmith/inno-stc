# 17 апреля

## Classloader

- bootstrap - прогрузка базовых классов %JAVA_HOME%/lib  
- system - classpath, глобальная переменная в окружении /CLASSPATH  
- extension - %JAVA_HOME%/lib/ext
- custom - наследует от system свой

class.getClassLoader() - null bootstrap. В остальных случаях вернет название класса загрузчика

class.getClassLoader() loading Stugent
1) ищем в системном ? end : continue;  
2) ищем в extension ? end : continue;  
3) ищем в bootstrap ? end : continue;  
4) грузим bootstrap ? end : continue;  
5) грузим extension ? end : continue;  
6) грузим system ? end : continue;  

loadClass
findClass

## Сетевые протоколы

Уровни:
- физический: Кабель, радиоэфир
- канальный: Ethernet, ARP
- сетевой: ipv4, ipv6
- транспортный: tcp, udp ...
- сеансовый: RPC, PPTP
- представления: JPEG, ASCII
- приложения: HTTP, FFTP

## ДЗ
1) Classloader
2) Чаты
3) Создать БД "студенты"
 - Студент, группа, занятия, журнал посещений
 
 вывеси первых трех самых старших студентов в каждой группе
 
 Select * 
