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
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

    /**
     * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
     * higher triage level is compared to a lower triage level, regardless of patient order of
     * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
     * returns a negative integer when a lower triage level is compared to a higher triage level,
     * regardless of patient order of arival.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToDifferentTriage() {
        // TODO complete the implementation of this tester method
        // Reset the patient counter before testing
        PatientRecord.resetCounter();
        // Create some PatientRecords with different triage levels
        PatientRecord patient1 = new PatientRecord('F', 30, TriageLevel.RED);
        PatientRecord patient2 = new PatientRecord('M', 25, TriageLevel.YELLOW);
        PatientRecord patient3 = new PatientRecord('F', 35, TriageLevel.GREEN);

// Test comparing patients with different triage levels
        if (patient1.compareTo(patient2) >= 0) {
            return false; // Patient 1 should have a higher priority (lower value) than patient 2
        }
        if (patient2.compareTo(patient3) >= 0) {
            return false; // Patient 2 should have a higher priority (lower value) than patient 3
        }
        if (patient1.compareTo(patient3) >= 0) {
            return false; // Patient 1 should have a higher priority (lower value) than patient 3
        }

// Create some more PatientRecords with different triage levels but the same order of arrival
        PatientRecord.resetCounter();
        PatientRecord patient4 = new PatientRecord('F', 40, TriageLevel.RED);
        PatientRecord.resetCounter();
        PatientRecord patient5 = new PatientRecord('M', 45, TriageLevel.YELLOW);

// Test comparing patients with different triage levels and the same order of arrival
        if (patient4.compareTo(patient5) >= 0) {
            return false; // Patient 4 should have a higher priority (lower value) than patient 5
        }

        return true;
    }

        /**
         * Tests whether patients in the same triage level are compared based on their order of arrival.
         * Patients of the same triage level with a lower arrival number compared to patients with a
         * higher arrival number should return a negative integer. The reverse situation should return a
         * positive integer.
         *
         * @return true if the tester verifies a correct functionality and false if at least one bug is
         *         detected
         * @see PatientRecord#compareTo(PatientRecord)
         */
        public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
            // Reset patient counter before running the test
            PatientRecord.resetCounter();

            // Create two PatientRecord instances with the same TriageLevel but different arrival orders
            PatientRecord patientA = new PatientRecord('M', 25, TriageLevel.YELLOW);
            PatientRecord patientB = new PatientRecord('F', 30, TriageLevel.YELLOW);

            // Compare the two PatientRecord instances using compareTo()
            int comparisonResult = patientA.compareTo(patientB);

            // Check if the comparison result matches the expected value (negative integer)
            if (comparisonResult >= 0) {
                return false; // Test failed
            }

            // Now compare patientB to patientA and expect a positive integer
            comparisonResult = patientB.compareTo(patientA);

            // Check if the comparison result matches the expected value (positive integer)
            if (comparisonResult <= 0) {
                return false; // Test failed
            }

            // If both comparisons return the expected values, the test passes
            return true;
        }

    /**
     * Tests whether patients in the same triage level and with the same order of arrival are equal
     * (compareTo should return 0). Even though this case will not be possible in your priority queue,
     * it is required for testing the full functionality of the compareTo() method. Hint: you will
     * need to use the resetCounter() to create equivalent PatientRecords.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToSameTriageSameArrival() {
        // Reset the patient counter to ensure the same order of arrival for the test
        PatientRecord.resetCounter();

        // Create two patient records with the same triage level and same order of arrival
        PatientRecord patient1 = new PatientRecord('M', 25, TriageLevel.YELLOW);
        PatientRecord.resetCounter(); // Reset the counter again to make the order of arrival the same
        PatientRecord patient2 = new PatientRecord('F', 30, TriageLevel.YELLOW);

        // Compare the two patient records using compareTo
        int comparisonResult = patient1.compareTo(patient2);

        // Return true if the comparison result is 0, indicating that the two records are equal
        return comparisonResult == 0;
    }


    /**
     * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
     * the following tests:
     *
     * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
     * IllegalArgumentException
     * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
     * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
     * the capacity that was passed as a parameter.
     *
     * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
     * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
     */
    public static boolean testConstructor() {
        // Test case 1: Invalid capacity
        boolean test1Passed;
        try {
            PriorityCareAdmissions invalidCapacity = new PriorityCareAdmissions(-1);
            test1Passed = false; // Exception not thrown
        } catch (IllegalArgumentException e) {
            test1Passed = true; // Exception thrown as expected
        }

        // Test case 2: Valid capacity
        boolean test2Passed;
        try {
            int validCapacity = 10;
            PriorityCareAdmissions validQueue = new PriorityCareAdmissions(validCapacity);
            test2Passed = validQueue.isEmpty() &&
                    validQueue.size() == 0 &&
                    validQueue.capacity() == validCapacity;
        } catch (Exception e) {
            test2Passed = false; // Unexpected exception thrown
        }

        return test1Passed && test2Passed;
    }



    /**
     * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
     * throws a NoSuchElementException.
     *
     * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
     */
    public static boolean testPeekEmpty() {
        try {
            PriorityCareAdmissions admissions = new PriorityCareAdmissions(5);
            admissions.peek(); // This should throw a NoSuchElementException since the queue is empty
            return false; // If we reach this point, the exception was not thrown, so the test fails
        } catch (NoSuchElementException e) {
            return true; // If we catch the NoSuchElementException, the test passes
        }
    }


    /**
     * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
     * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
     * the PatientRecord from the queue.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testPeekNonEmpty() {
        // Reset the counter before testing
        PatientRecord.resetCounter();

        // Create several PatientRecords with different priorities
        PatientRecord patient1 = new PatientRecord('M', 25, TriageLevel.RED);
        PatientRecord patient2 = new PatientRecord('F', 30, TriageLevel.YELLOW);
        PatientRecord patient3 = new PatientRecord('X', 22, TriageLevel.RED);
        PatientRecord patient4 = new PatientRecord('M', 35, TriageLevel.GREEN);

        // Create a new PriorityCareAdmissions object and add the PatientRecords
        PriorityCareAdmissions priorityQueue = new PriorityCareAdmissions(10);
        priorityQueue.addPatient(patient1);
        priorityQueue.addPatient(patient2);
        priorityQueue.addPatient(patient3);
        priorityQueue.addPatient(patient4);

        // Test the peek() method for correct functionality
        boolean result = true;

        // Check if peek() returns the PatientRecord with the highest priority
        if (!priorityQueue.peek().equals(patient1)) {
            result = false;
        }

        // Check if the size of the queue remains the same after calling peek()
        if (priorityQueue.size() != 4) {
            result = false;
        }

        // Check if the PatientRecord is still in the queue after calling peek()
        priorityQueue.removeBestRecord();
        if (!priorityQueue.peek().equals(patient3)) {
            result = false;
        }

        return result;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
     * ensuring the method 1) adds the PatientRecord and 2) increments the size.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientEmpty() {
        // Reset the patient counter
        PatientRecord.resetCounter();

        // Create a new instance of PriorityCareAdmissions
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(10);

        // Create a new PatientRecord with sample data
        PatientRecord samplePatient = new PatientRecord('M', 25, TriageLevel.YELLOW);

        // Add the PatientRecord to the PriorityCareAdmissions instance
        priorityCareAdmissions.addPatient(samplePatient);

        // Check if the size of the PriorityCareAdmissions instance is incremented
        if (priorityCareAdmissions.size() != 1) {
            return false;
        }

        // Check if the PatientRecord is added correctly
        if (!priorityCareAdmissions.peek().equals(samplePatient)) {

            return false;
        }

        return true;
    }



    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
     * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
     * size. Try add at least 5 PatientRecords.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
     */
    public static boolean testAddPatientNonEmpty() {
        // Reset the patient counter
        PatientRecord.resetCounter();

        // Create a new instance of PriorityCareAdmissions
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(10);

        // Create 5 new PatientRecords with different sample data
        PatientRecord samplePatient1 = new PatientRecord('M', 25, TriageLevel.RED);
        PatientRecord samplePatient2 = new PatientRecord('F', 40, TriageLevel.GREEN);
        PatientRecord samplePatient3 = new PatientRecord('X', 30, TriageLevel.YELLOW);
        PatientRecord samplePatient4 = new PatientRecord('M', 50, TriageLevel.RED);
        PatientRecord samplePatient5 = new PatientRecord('F', 35, TriageLevel.GREEN);

        // Add the PatientRecords to the PriorityCareAdmissions instance
        priorityCareAdmissions.addPatient(samplePatient1);
        priorityCareAdmissions.addPatient(samplePatient2);
        priorityCareAdmissions.addPatient(samplePatient3);
        priorityCareAdmissions.addPatient(samplePatient4);
        priorityCareAdmissions.addPatient(samplePatient5);

        // Check if the size of the PriorityCareAdmissions instance is incremented
        if (priorityCareAdmissions.size() != 5) {
            return false;
        }

        // Check if the PatientRecords are added correctly and in the right order
        PatientRecord[] expectedPatients = {samplePatient1, samplePatient4, samplePatient3, samplePatient2, samplePatient5};
        for (int i = 0; i < 5; i++) {
            PatientRecord actualPatient = priorityCareAdmissions.removeBestRecord();
            PatientRecord expectedPatient = expectedPatients[i];
            if (!actualPatient.equals(expectedPatient)) {
                return false;
            }
        }

        return true;
    }





    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
     * ensuring the method throws an IllegalStateException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientFull() {
        try {
            int capacity = 5;
            PriorityCareAdmissions admissions = new PriorityCareAdmissions(capacity);
            for (int i = 0; i < capacity; i++) {
                PatientRecord patient = new PatientRecord('M', i, TriageLevel.RED);
                admissions.addPatient(patient);
            }

            PatientRecord patient = new PatientRecord('M', capacity, TriageLevel.RED);
            admissions.addPatient(patient);

            return false;
        } catch (IllegalStateException e) {
            if (e.getMessage().equals("Warning: Full Admissions Queue!")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // If a different type of exception is thrown, return false
            return false;
        }
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() with a null
     * PatientRecord and ensuring the method throws a NullPointerException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     *         otherwise.
     */
    public static boolean testAddPatientNull() {
        try {
            PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(10);
            priorityCareAdmissions.addPatient(null);
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }






    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
     * queue.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
     *         false otherwise
     */
    public static boolean testRemoveBestRecordEmpty() {
        PriorityCareAdmissions admissions = new PriorityCareAdmissions(5);
        try {
            admissions.removeBestRecord();
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }


    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
     * of size one.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
     *         size is 0
     */
    public static boolean testRemoveBestRecordSizeOne() {
        PriorityCareAdmissions queue = new PriorityCareAdmissions(1);
        PatientRecord patient = new PatientRecord('M', 30, TriageLevel.YELLOW);
        queue.addPatient(patient);

        PatientRecord removedPatient = queue.removeBestRecord();

        return removedPatient.equals(patient) && queue.isEmpty();
    }


    /**
     * Tests the functionality of removeBestRecord() methods.
     *
     * The removeBestRecord() method must remove, and return the patient record with the highest
     * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
     * method is successfully called.
     *
     * Remove the best record from a queue whose size is at least 6. Consider cases where
     * percolate-down recurses on left and right.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
     *         each time it is called and size is appropriately decremented, false otherwise
     */
    public static boolean testRemoveBestRecordNonEmpty() {
        PriorityCareAdmissions admissions = new PriorityCareAdmissions(10);

        // Define patient records
        PatientRecord p1 = new PatientRecord('F', 25, TriageLevel.YELLOW);
        PatientRecord p2 = new PatientRecord('M', 30, TriageLevel.GREEN);
        PatientRecord p3 = new PatientRecord('F', 40, TriageLevel.RED);
        PatientRecord p4 = new PatientRecord('M', 35, TriageLevel.YELLOW);
        PatientRecord p5 = new PatientRecord('X', 20, TriageLevel.GREEN);
        PatientRecord p6 = new PatientRecord('F', 45, TriageLevel.RED);

        // Add patient records to the queue
        admissions.addPatient(p1);
        admissions.addPatient(p2);
        admissions.addPatient(p3);
        admissions.addPatient(p4);
        admissions.addPatient(p5);
        admissions.addPatient(p6);

        // Call removeBestRecord() and check if the returned patient record is correct
        PatientRecord bestRecord = admissions.removeBestRecord();
        if (!bestRecord.equals(p3)) {
            return false;
        }

        // Check if the size is decremented correctly
        if (admissions.size() != 5) {
            return false;
        }

        // Call removeBestRecord() again and check if the returned patient record is correct
        bestRecord = admissions.removeBestRecord();
        if (!bestRecord.equals(p6)) {
            return false;
        }

        // Check if the size is decremented correctly
        if (admissions.size() != 4) {
            return false;
        }

        // All test cases passed
        return true;
    }




    /**
     * Tests the functionality of the clear() method.
     * Should implement at least the following scenarios:
     * - clear can be called on an empty queue with no errors
     * - clear can be called on a non-empty queue with no errors
     * - After calling clear(), the queue should contain zero PatientRecords.
     * - After calling clear(), the size should be 0
     *
     * @return true if PriorityCareAdmissions.clear() functions properly
     */
    public static boolean testClear() {
        // Test on an empty queue
        PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(5);
        emptyQueue.clear();
        if (emptyQueue.size() != 0) {
            return false;
        }

        // Test on a non-empty queue
        PriorityCareAdmissions nonEmptyQueue = new PriorityCareAdmissions(5);
        nonEmptyQueue.addPatient(new PatientRecord('M', 25, TriageLevel.RED));
        nonEmptyQueue.addPatient(new PatientRecord('F', 35, TriageLevel.YELLOW));
        nonEmptyQueue.clear();
        if (nonEmptyQueue.size() != 0) {
            return false;
        }

        return true;
    }


//come back to later
    /**
     * Tests toString() method of PriorityCareAdmissions class.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     *         detected
     */
    public static boolean testToString() {
        PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
        PatientRecord p1 = new PatientRecord('M', 30, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 25, TriageLevel.YELLOW);

        String expectedEmptyQueueString = "";
        if (!queue.toString().equals(expectedEmptyQueueString)) {
            return false;
        }

        queue.addPatient(p1);
        queue.addPatient(p2);

        String expectedNonEmptyQueueString = p1.toString() + "\n" + p2.toString() + "\n";
        if (!queue.toString().equals(expectedNonEmptyQueueString)) {
            return false;
        }

        return true;
    }


    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {

        return testPatientRecordCompareToDifferentTriage()
                && testPatientRecordCompareToSameTriageDifferentArrival()
                && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
                && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
                && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
                && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
                && testToString();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToDifferentTriage: "
                + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
                + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
                + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
        System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
        System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
        System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
        System.out
                .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
        System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
        System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
    }

}
