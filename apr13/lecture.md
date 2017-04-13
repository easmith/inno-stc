# 13 ������

## XML

1) ���������  
2) ���������  
3) �������  
4) ��������������  

### .xsd �����
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



## ������ �������������� ��� ������

���������� ����� ��
1) ����������� ������ (���������� ������)  
2) + ��������� ����  
3) + ������� ����  

[CAP �������](https://ru.wikipedia.org/wiki/�������_CAP)
 - consistency - ���������������  
 - availability - �����������  
 - partition tolerance - ������������ � ����������  

SELECT INSERT UPDATE DELETE  
CREATE TABLE  
ALTER TABLE  
DROP DATABASE  

## JDBC
���������� ��� ������ � ������ ������
DriverManager  
Connection  
Statement  
PreparedStatment  
CallableStatement  
ResultSet  












