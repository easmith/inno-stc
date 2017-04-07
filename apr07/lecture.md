# 7 апреля

Deadlock

|---I1---|---I2---|
|---I2-----|---I1---|

Решения:

1) Установить очередность захвата

2)
trylock (obj) {
    // do
}

3) Многоместный семафор
от одного до нескольких потоков

Пакет java.util.Concurrent
Atomic
ConcurrentCollection
Executors
Synchronizers

Lock  
\ ReentrantLock  
  FairLock
\ ReentrantReadWriteLock  

Synchronizers
\ Semaphore  
\ CountDownLatch  
\ CyclicBarrier  
\ Exchangers для обмена данными между двумя объектами  
\ Phazer  
 - \ CountDownLatch  
 - \ CyclicBarrier  
 
ConcurrentCollection
 copyOn  
  - \ CopyOnWriteArrayList  
 Concurrent  
  - \ ConcurrentHashMap  
  
Atomics
Все операции обладают свойством атомарности
CAS (compare and set/swap) инструкции в процессоре

cas(memory, cache, forset)


# Итоги недели

методы класса Object. Наследование переопределения

Exception - нужна на них реакция

Коллекции. Задавать capacity. Map - неизменяемый ключ

Пакет io. Самое узкое место при оптимизации

Сериализация SerialiseVersionUID

Многопоточность. Синхронизация. Пакет Cuncurrent


# Домашка

[Обзор java.util.concurrent.*](https://habrahabr.ru/company/luxoft/blog/157273/)  
[IBM Пять вещей, которые вы не знали о ... пакете java.util.concurrent.  Часть 2](https://www.ibm.com/developerworks/ru/library/j-5things5/)  
[Собеседование по Java concurrency](http://www.duct-tape-architect.ru/?p=294)  
[Пять секретов... многопоточного Java-программирования](https://www.ibm.com/developerworks/ru/library/j-5things15/)  
[Теория и практика Java: Пулы потоков и очередь действий](https://www.ibm.com/developerworks/ru/library/j-jtp0730/)  
[BlockingQueue](http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html)  
[HashMap и ConcurrentHashMap популярные вопросы на собеседованиях](http://info.javarush.ru/translation/2013/09/23/HashMap-%D0%B8-ConcurrentHashMap-%D0%BF%D0%BE%D0%BF%D1%83%D0%BB%D1%8F%D1%80%D0%BD%D1%8B%D0%B5-%D0%B2%D0%BE%D0%BF%D1%80%D0%BE%D1%81%D1%8B-%D0%BD%D0%B0-%D1%81%D0%BE%D0%B1%D0%B5%D1%81%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%D1%85.html)  
[Теория и практика Java: Загадки родовых типов (generics)](https://www.ibm.com/developerworks/ru/java/library/j-jtp01255/index.html) 

