//Базовая реализация (интерфейс)
public interface MySet<T> {
    boolean add(T element);       // Добавление элемента
    boolean remove(T element);    // Удаление элемента
    boolean contains(T element);  // Проверка наличия элемента
    int size();                   // Количество элементов
    boolean isEmpty();            // Проверка на пустоту
    void clear();                 // Очистка множества
}
