# 6 апреля

# Многопоточность
ПсевдоПаралельные вычисления

Переключение контекста

Расходы произврдительности на обслуживание потоков

class Thread .run()

interface Runnable .run() как аннонимный класс

Executors:  
FixedThreadPool
ScheduledThreadPoolExecutor

Callable .call()

Синхронизация и доступ к общим ресурсам

Потоки стопать нельзя
interrupt() isInterupted=true

InterruptedException  
Состояние потока: new runnable wait timewait sleep park block terminate

Два потока
boolean isShow = false;
int x = 0;

1 поток:

    x++;
    if (x<0)
        isShow = true;
    if (isShow)
        sout(x);
    
2 поток:

    x--;     
    if (x<0)
        isShow = false;

    if (isShow)
        sout(x);


Видимость  
Атомарность
Ордеринг


volatile  
- глобальная видимость  
- гарантирует атомарность операции  
- порядок в рамках потоков

Java Memeory Model про многопоточную работу
happendBefore чтение до записи (произошло до)

В заголовке объекта Lock
Synchronized (SomeObject)

Synchronized void met () {

}

init/new bised thin fat/inflate  
Быстрый и медленный вход

wait()
Освобождает монитор

notify()
notifyAll()
yield() сразу паркует


## Лекция Елизарова


## Контракты Memory Model
1) Гарантирован порядок
2) Запуск потока start() happendBefore run()
3) Чтение volatile happendBefore записи
4) Отпускание монитора (Lock) одним потоком happendBefore его захвата другим потоком
5) Если thread1 join thread2, то завершение кода thread1 после завершения thread2


## Лабораторная Вариант 2

Необходимо разработать программу, которая получает на вход список ресурсов, содержащих текст, и проверяет уникальность каждого слова. Каждый ресурс должен быть обработан в отдельном потоке, текст не должен содержать инностранных символов, только кириллица, знаки препинания и цифры. В случае нахождения дубликата, программа должна прекращать выполнение с соответсвующим сообщением. Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами

