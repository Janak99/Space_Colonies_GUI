package spacecolonies;

import java.util.Arrays;
import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * ArrayQueue Class : data structure holds queue of Planet Applicants
 * 
 * @author Janak Majeethia
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;

    /**
     * Main constructor
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Parameterized constructor that takes in variable size
     */
    public ArrayQueue(int defaultCapacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[defaultCapacity + 1];
        queue = temp;
        dequeueIndex = 0;
        enqueueIndex = defaultCapacity;
    }

    /**
     * If no more space is available in the queue, the capacity is doubled
     */
    private void ensureCapacity() {
        if (this.size < MAX_CAPACITY) {

            if (dequeueIndex == ((enqueueIndex + 2) % queue.length)) {
                T[] oldQueue = queue;
                int oldSize = oldQueue.length;

                @SuppressWarnings("unchecked")
                T[] tempQueue = (T[]) new Object[(2 * oldSize) - 1];
                queue = tempQueue;
                for (int j = 0; j < oldSize - 1; j++) {
                    queue[j] = oldQueue[dequeueIndex];
                    dequeueIndex = (dequeueIndex + 1) % oldSize;
                }

                dequeueIndex = 0;
                enqueueIndex = oldSize - 2;

            }
        } else {
            throw new IllegalStateException();
        }

    }

    /**
     * Queues an object T
     * Usually Person objects
     */
    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();
        enqueueIndex = (enqueueIndex + 1) % queue.length;
        queue[enqueueIndex] = newEntry;
        size++;
    }

    /**
     * Returns front of the queue
     */
    @Override
    public T getFront() {
        T front = null;
        if (!isEmpty()) {
            front = queue[dequeueIndex];
            return front;
        } else {
            throw new EmptyQueueException();
        }

    }

    /**
     * Removes Object from the front of the line
     */
    @Override
    public T dequeue() {
        if (size > 0) {
            T front = null;
            front = queue[dequeueIndex];
            queue[dequeueIndex] = null;
            dequeueIndex = (dequeueIndex + 1) % queue.length;
            size--;
            return front;
        } else {
            throw new EmptyQueueException();
        }

    }

    /**
     * Clears the queue
     */
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[DEFAULT_CAPACITY + 1];
        queue = temp;
        enqueueIndex = DEFAULT_CAPACITY;
        dequeueIndex = 0;
        size = 0;
    }

    /**
     * Turns the Queue into an array of type Object[]
     * 
     * @return Array of Objects representation of the Queue
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            int front = dequeueIndex;
            int back = enqueueIndex;
            Object[] tempResult = (Object[]) new Object[this.size];
            for (int i = 0; i < this.size; i++) {
                tempResult[i] = queue[dequeueIndex];
                dequeueIndex = (dequeueIndex + 1) % queue.length;
            }
            dequeueIndex = front;
            enqueueIndex = back;

            Object[] finalResult = new Object[this.size];
            for (int i = 0; i < size; i++) {
                finalResult[i] = tempResult[i];
            }
            return finalResult;
        }
    }

    /**
     * Returns a string representation of the queue
     * 
     * @return String representation of the Queue
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getSize() == 0) {
            return "[]";
        }
        Object[] tempArray = this.toArray();
        for (int index = 0; index < this.size; index++) {
            if (index == 0) {
                sb.append("[");
            }
            sb.append(tempArray[index].toString());
            if (index < this.size - 1) {
                sb.append(", ");
            }
            if (index == this.size - 1) {
                sb.append("]");
            }
        }
        return sb.toString();
    }

    /**
     * Returns capacity of queue
     * 
     * @return integer capacity of the queue
     */
    public int getLength() {
        return queue.length;
    }

    /**
     * Boolean to determine if queue is empty
     */
    @Override
    public boolean isEmpty() {
        return dequeueIndex == ((enqueueIndex + 1) % queue.length);
    }

    /**
     * Returns capacity of the queue
     * 
     * @return size of queue
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Determines if two queues are equals
     * If their queues are of equal size and values at exact indices, returns
     * true
     * 
     * @param obj
     *            ArrayQueue being compared
     * @return boolean true or false if the two are equal or not
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ArrayQueue<T> other = ((ArrayQueue<T>) obj);
        if (size != other.size || other.isEmpty()) {
            return false;
        }

        Object[] localArray = this.toArray();
        Object[] compArray = other.toArray();

        return Arrays.equals(localArray, compArray);

    }
}