//Реализация очереди на массиве (ArrayQueue)
public class ArrayQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int front;
    private int rear;
    private int size;

    // Конструктор по умолчанию
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Конструктор с заданной емкостью
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.elements = (T[]) new Object[initialCapacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Добавление элемента в очередь
    public void enqueue(T element) {
        if (isFull()) {
            resize(elements.length * 2);
        }
        
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    // Удаление элемента из очереди
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        
        T element = elements[front];
        elements[front] = null; // Для предотвращения утечки памяти
        front = (front + 1) % elements.length;
        size--;
        
        // Уменьшаем размер массива, если он слишком большой
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        
        return element;
    }

    // Просмотр первого элемента без удаления
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[front];
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // Проверка на заполненность
    public boolean isFull() {
        return size == elements.length;
    }

    // Размер очереди
    public int size() {
        return size;
    }

    // Изменение размера внутреннего массива
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        
        // Копируем элементы с учетом циклического характера очереди
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        
        elements = newElements;
        front = 0;
        rear = size - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % elements.length;
            sb.append(elements[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
