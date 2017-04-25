# 24 апреля

## Java 7
- Синтаксический сахар
- - строки в switch
- - подчеркивания в цифрах
- - бинарные литералы
- - Multi-Catch - catch (IOException|SQLException ex)
- - Diamond operator
- - try with resources
- - Аннотация @SafeVarArgs
- Фреймворки
- - NIO2
- - - WatchService
- - - Ассинхронность
- - - File & Path


## Java 8
- Defaut методы - В интерфейсах возможна реализация
- Лямбды - функция, которую можно передавать как объект
- - MyClass::toString()
- - FunctionInterface
- - AWSLambda.invoke()
- - Collection.sort(collection, (o1, o2) -> { });
- StreamAPI


Чтобы класс мог использовать нашу лямбду мы должны определить интерфейс в нашем классе

@FunctionalInterface instance
MyConverter<T, V> {
  T convert (V v);
}

MyClass implements MyConverter

MyClass myClass = new MyClass();
myClass.convert((s)->{s.toString()});

Только один недефолтный методы

### Готовые функциональные интерфейсы

1) Predicate (input) -> {return true/false}
2) Function (input) -> { return input * 10 } .andThen()
3) Supplier (input) -> { return }
4) Consumer (input) -> { }
5) Comparator (o1, o2) -> { return int }
6) Optional - может быть пустым


## StreamAPI
Набор объектов, над которыми проводим действия
.stream()
.parallelStream()

Промежуточные и конечные операции

Опреации над стримом:
.filter(Predicate)
.sorted(Comparator)
.map(Function) - преобразователь из одного типа в другой
.anyMatch(Predicate) .allMatch(Predicate) .noneMatch()
.count(Predicate)
.reduce(in1, in2) -> {in1+in2}
.forEach(Consumer)

.toList()
.toArray()

final int c = 5;
()->{
  int x = 0;
  sout(x+c)
}
