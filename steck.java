public class ArrayStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    // Конструктор по умолчанию
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    // Конструктор с заданной начальной емкостью
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.elements = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    // Добавление элемента в стек
    public void push(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size++] = element;
    }

    // Извлечение элемента из стека
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = elements[--size];
        elements[size] = null; // Для предотвращения утечки памяти
        
        // Уменьшаем размер массива, если он слишком большой
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        
        return element;
    }

    // Просмотр верхнего элемента без извлечения
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[size - 1];
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // Размер стека
    public int size() {
        return size;
    }

    // Изменение размера внутреннего массива
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
