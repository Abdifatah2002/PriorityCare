//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P10 Priority Care
// Course:   CS 300 Spring 2023
//
// Author:   Abdifatah Abdi
// Email:    aaabdi2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
/// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//
////   _X__ Write-up states that pair programming is allowed for this assignment.
//
////   _X__ We have both read and understand the course Pair Programming Policy.
//
////   _X__ We have registered our team prior to the team registration deadline.
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//// Persons:         TA: TA Snehal Wadhwani  help with little help
// TA: Yiwei Zhang help with little help
//TA; MICHELLE JENSEN help me a lttile bit
//// Online Sources:
/////////////////////////////////////////////////////////////////////////////


import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Arrays;
/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords.
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
    /**
     * array min-heap of PatientRecords representing this priority
     */
    private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority

    // queue
    /**
     * size of this priority queue
     */
    private int size; // size of this priority queue


    /**
     * Creates a new empty PriorityCareAdmissions queue with the given capacity
     *
     * @param capacity Capacity of this PriorityCareAdmissions queue
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public PriorityCareAdmissions(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        queue = new PatientRecord[capacity];
        size = 0;
    }

    /**
     * Checks whether this PriorityCareAdmissions queue is empty
     *
     * @return {@code true} if this PriorityCareAdmissions queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of this PriorityCareAdmissions queue
     *
     * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the capacity of this PriorityCareAdmissions queue
     *
     * @return the capacity of this PriorityCareAdmissions queue
     */
    public int capacity() {
        return queue.length;
    }


    /**
     * Removes all the elements from this PriorityCareAdmissions queue
     */
    public void clear() {
        size = 0;
    }

    /**
     * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
     * PatientRecord having the the highest priority.
     *
     * @return the PatientRecord at the root of this PriorityCareAdmissions queue
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        }
        return queue[0];
    }


    /**
     * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
     * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
     * PatientRecord at each index is less than or equal to than the PatientRecords in its child
     * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
     *
     * @param p PatientRecord to add to this PriorityCareAdmissions queue
     * @throws NullPointerException  if the given PatientRecord is null
     * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
     *                               if this PriorityCareAdmissions queue is full
     */
    public void addPatient(PatientRecord p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (size == queue.length) {
            throw new IllegalStateException("Warning: Full Admissions Queue!");
        }
        queue[size] = p;
        percolateUp(size);
        size++;
    }

    /**
     * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
     * priority queue by percolating a leaf up the heap. If the element at the given index does not
     * violate the min-heap invariant (it is greater than its parent), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
     * continue percolating the element up the heap.
     *
     * @param i index of the element in the heap to percolate upwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = (i - 1) / 2;
        if (queue[i].compareTo(queue[parent]) < 0) {
            PatientRecord temp = queue[i];
            queue[i] = queue[parent];
            queue[parent] = temp;
            percolateUp(parent);
        }
    }

    /**
     * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
     * the PatientRecord having the highest priority (the minimum one).
     *
     * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
     *         queue.
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord removeBestRecord() {
        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        }
        PatientRecord bestRecord = queue[0];
        queue[0] = queue[size - 1];
        size--;
        percolateDown(0);
        return bestRecord;
    }


    /**
     * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
     * by percolating its root down the tree. If the element at the given index does not violate the
     * min-heap ordering property (it is smaller than its smallest child), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
     * correct child and continue percolating the element down the heap.
     *
     * @param i index of the element in the heap to percolate downwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && queue[left].compareTo(queue[smallest]) < 0) {
            smallest = left;
        }
        if (right < size && queue[right].compareTo(queue[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            PatientRecord temp = queue[i];
            queue[i] = queue[smallest];
            queue[smallest] = temp;
            percolateDown(smallest);
        }
    }

    /**
     * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
     * same order. This method does not return the deepest copy, meaning that you do not need to
     * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
     * be duplicated.
     *
     * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
     *         admissions queue has the same length and size as this queue.
     */
    public PriorityCareAdmissions deepCopy() {
        PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
        deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
        deepCopy.size = this.size;
        return deepCopy;
    }

    /**
     * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
     *
     * This method can be used for testing purposes.
     *
     * @return a deep copy of the array-heap storing the ParientRecords in this queue
     */
    protected PatientRecord[] arrayHeapCopy() {
        return Arrays.copyOf(this.queue, this.queue.length);

    }
    /**
     * Returns a String representing this PriorityCareAdmissions queue, where each element
     * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
     *
     * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
     *         queue is empty.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PriorityCareAdmissions copy = this.deepCopy();
        while (!copy.isEmpty()) {
            sb.append(copy.removeBestRecord().toString()).append("\n");
        }
        return sb.toString();
    }
}