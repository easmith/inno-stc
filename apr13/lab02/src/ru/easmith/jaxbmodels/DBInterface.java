package ru.easmith.jaxbmodels;

/**
 * Created by eku on 15.04.17.
 */
public interface DBInterface {
    /**
     * Сохраняет объект в базу
     * @return true, если удачно
     */
    boolean toDB();

    /**
     * Заполяет объект данными из базы
     * @return true, если удачно
     */
    boolean fromDB();

    /**
     * Возвращает зависимости, которые следует удовлетворить перед добавлением в базу данных
     * @return Массив названий зависимых классв
     */
    String[] getDepends();
}
