package seedu.address.testutil;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Grade;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder extends PersonBuilder {

    public static final String DEFAULT_PARENT_NAME = "Test parent";
    public static final String DEFAULT_PARENT_PHONE = "91234567";
    public static final String DEFAULT_PARENT_EMAIL = "testparent@example.com";
    public static final String DEFAULT_GRADE = "0";

    private Name parentName;
    private Phone parentPhone;
    private Email parentEmail;
    private Grade grade;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        parentName = new Name(DEFAULT_PARENT_NAME);
        parentPhone = new Phone(DEFAULT_PARENT_PHONE);
        parentEmail = new Email(DEFAULT_PARENT_EMAIL);
        grade = new Grade(DEFAULT_GRADE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        super(studentToCopy);
        parentName = studentToCopy.getParentName();
        parentEmail = studentToCopy.getParentEmail();
        parentPhone = studentToCopy.getParentPhone();
        grade = studentToCopy.getGrade();
    }

    /**
     * Sets the {@code Parent's name} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentName(String parentName) {
        this.parentName = new Name(parentName);
        return this;
    }

    /**
     * Sets the {@code Parent's phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentPhone(String parentPhone) {
        this.parentPhone = new Phone(parentPhone);
        return this;
    }

    /**
     * Sets the {@code Parent's email} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentEmail(String parentEmail) {
        this.parentEmail = new Email(parentEmail);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public StudentBuilder withGrade(String gradeIndex) {
        this.grade = new Grade(gradeIndex);
        return this;
    }

    @Override
    public StudentBuilder withName(String name) {
        super.withName(name);
        return this;
    }

    @Override
    public StudentBuilder withPhone(String phone) {
        super.withPhone(phone);
        return this;
    }

    @Override
    public StudentBuilder withEmail(String email) {
        super.withEmail(email);
        return this;
    }

    @Override
    public StudentBuilder withAddress(String address) {
        super.withAddress(address);
        return this;
    }

    @Override
    public StudentBuilder withTags(String... tags) {
        super.withTags(tags);
        return this;
    }

    public Student build() {
        return new Student(super.build(), parentName, parentPhone, parentEmail, grade);
    }

}
