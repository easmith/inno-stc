# 13 апреля

## XML

1) Генерация  
2) Валидация  
3) Парсинг  
4) Преобразование  

### .xsd схема
<element name="Person" type="xs:string"/>  
<element name="Person" type="PersonType"/>

<xs:simpleType id="PersonType">
	<xs:restriction base="xs:string">
		<xs:minLength value="10"/>
		<xs:maxLength value="100"/>
		<xs:enumeration/>
		<xs:pattern value="[a-z]"/>
	</xs:restriction>
</xs:simpleType>

List Union  

<xs:complexType>
// <xs:all>
// <xs:any>
<xs:sequence>
	<xs:element name="Name" type="Type" minOccurs="1" maxOccurs="10"/>
	<xs:attribute name="" type=""/>
</xs:sequence>


<xs:baseSchema xmlns="http://site.com"/>
<xs:schema xmlns="http://site.com" prefix="br"/>


<xml namespace="http://mysite.com/mySchema.xsd"> 



## Основы проектирования баз данных

Нормальные формы БД
1) Атомарность данных (отсутствие дублей)  
2) + первичный ключ  
3) + внешний ключ  

[CAP теорема](https://ru.wikipedia.org/wiki/Теорема_CAP)
 - consistency - согласованность  
 - availability - доступность  
 - partition tolerance - устойчивость к разделению  

SELECT INSERT UPDATE DELETE  
CREATE TABLE  
ALTER TABLE  
DROP DATABASE  

## JDBC
библиотека для работы с базами данных
DriverManager  
Connection  
Statement  
PreparedStatment  
CallableStatement  
ResultSet  












