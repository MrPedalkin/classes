public class ArraySet<T> implements MySet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] elements;
    private int size;

    public ArraySet() {
        this(DEFAULT_CAPACITY);
    }

    public ArraySet(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }
        
        if (size == elements.length) {
            resize();
        }
        
        elements[size++] = element;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[i]).append(", ");
        }
        return sb.append(elements[size - 1]).append("]").toString();
    }
}
