# 11 апреля

## Механизмы отражения и проксирования
## Reflection API

1) Тестирование  
2) В механизме сериализаци  
3) Фреймворки  

SomeClass.class  
SomeClass.getClass()  
Class.forName("SomeClass")  // ClassLoader

getFields() // унаследованные поля
getDeclaredFields() // не унаследованные + private поля
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

1) Интерфейс
2) Класс
3) InvocationHandler // добавляет к вызову метода что-то свое
4) Proxy

invoke(Object proxy, Method method, Object[] args); => Object object
