# 11 ������

## ��������� ��������� � �������������
## Reflection API

1) ������������  
2) � ��������� �����������  
3) ����������  

SomeClass.class  
SomeClass.getClass()  
Class.forName("SomeClass")  // ClassLoader

getFields() // �������������� ����
getDeclaredFields() // �� �������������� + private ����
Field.getType(); => Type
Field.getTypeName(); => String
Field.getName();

getMethods();
getDeclaredMethods();
Methods[]
getArguments();
getTypes();


setAccessable(true);


## Proxy

1) ���������
2) �����
3) InvocationHandler // ��������� � ������ ������ ���-�� ����
4) Proxy

invoke(Object proxy, Method method, Object[] args); => Object object
