= 4 апреля =
    
== Исключния ==

Ошибка -> Исправление || Извинение


Throwable (Интерфейс)
\ Error (ошибки в JVM)
\ Exception
-\ RuntimeException (NullPointerException)// не проверяемое
-\ IOException
-\ SQLException

    try {
    } catch (NullPionterException ex) {
        throw new ArifmeticalException(); // на уровень выше
    } catch (Exception ex) {
    } finally {
        // выполняется всегда
    }
    
catch не обязателен

    void met() throw IOException {
    }
    
try with resources
Closeable

*** apr04 ***


Generic
Механизм для написания более универсального кода

class SomeSlass<T>  {
    T somemember;
    print() {
        somemember.toString();
    }
}

SomeClass<Integer> some1 = new SomeClass<>();
SomeClass<Double> some2 = new SomeClass<>();

--

class Calc<T extends Number> {
    T sum (T a, T b) {
        return a + b;
    }
}

class Calc<T extends Comparable> {
    int comp(T a, Tc) {
        return a.compare(c);
    }
}

--

Генерики не ковариантны

List lst = new ArrayList();
lst,add("a");
lst.add(1); // порождает много ошибок


List<Integer> lst = new ArrayList<>();

List<Number> lst = new ArrayList<Integer>(); // не скомпилируется

<AB super Number>AB met(AB param)

--

Иерархия коллекций

Динамическое расширение, отсутствие дубликатов

[Iterable]
.irerator()

[Collection]
\ List (.add() .remove() .indexOf())
-\ ArrayList
\ Set // только уникальные 
\ Queue // fifo


=== ArrayList 
Индекс-значение
Емкость capacity=10

При добавлении capacity *= 3 / 2 + 1;

get() O(1)
containts(), indexOf() O(n)
remove() O(n)
systemArrayCopy

=== LinkedList (Dequeue)
Двусвязанный список
next()
prev()
length();
head-tail

=== Set
Список уникальны значений
\ HashSet
\ SortedSet
-\ NavigableSet
-\ TreeSet (Красно-черное)

=== Map
\ SortedMap
-\ NavigableMap
-\ TreeMap
\ HashMap (bucket)
capacity - кол-во бакетов (16) ~ 2^n
loadfactor - 3/4

Добавление элемента (выбор бакета)
hash: hashcode ^ (hashCode >>> 16)
indexFor: hash & (capacity - 1)

null bucket

Ключь должен быть неизменяемым

Вариант номер (2)

LinkedList 