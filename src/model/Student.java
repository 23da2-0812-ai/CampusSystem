package model;

import java.util.Objects;

/**
 * Represents a single university student record.
 *
 * The student ID is the unique key of the record and cannot be changed
 * after creation. All other fields are validated whenever they are set.
 *
 * @author ANEESIYA (23DA2-0812)
 */
public class Student {

    private static final double MIN_MARKS = 0.0;
    private static final double MAX_MARKS = 100.0;

    private final String id;
    private String name;
    private String programme;
    private double marks;

    /**
     * Creates a new student record.
     *
     * @throws IllegalArgumentException if any value is invalid
     */
    public Student(String id, String name, String programme, double marks) {
        this.id = validateText(id, "Student ID");
        this.name = validateText(name, "Name");
        this.programme = validateText(programme, "Programme");
        this.marks = validateMarks(marks);
    }

    // ---------- Getters ----------

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProgramme() {
        return programme;
    }

    public double getMarks() {
        return marks;
    }

    // ---------- Setters (ID has no setter because it is final) ----------

    public void setName(String name) {
        this.name = validateText(name, "Name");
    }

    public void setProgramme(String programme) {
        this.programme = validateText(programme, "Programme");
    }

    public void setMarks(double marks) {
        this.marks = validateMarks(marks);
    }

    // ---------- Validation helpers ----------

    private static String validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    private static double validateMarks(double marks) {
        if (Double.isNaN(marks) || marks < MIN_MARKS || marks > MAX_MARKS) {
            throw new IllegalArgumentException(
                    "Marks must be between " + MIN_MARKS + " and " + MAX_MARKS + ".");
        }
        return marks;
    }

    // ---------- Object methods ----------

    /** Two students are equal if they have the same ID. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-15s | Programme: %-10s | Marks: %.2f",
                id, name, programme, marks);
    }
}