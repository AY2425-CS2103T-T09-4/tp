package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

public class Student extends Person {

    private final Name parentName;
    private final Phone parentPhone;
    private final Email parentEmail;

    public Student(Name name, Phone phone, Email email, Address address, Name parentName, Phone parentPhone,
            Email parentEmail, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.parentEmail = parentEmail;
    }

    public Name getParentName() {
        return parentName;
    }

    public Phone getParentPhone() {
        return parentPhone;
    }

    public Email getParentEmail() {
        return parentEmail;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return super.equals(otherStudent) && parentName.equals(otherStudent.parentName) &&
                parentPhone.equals(otherStudent.parentPhone) && parentEmail.equals(otherStudent.parentEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getParentName(), this.getParentPhone(), this.getParentEmail(), this.getTags());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", this.getName())
                .add("phone", this.getPhone())
                .add("email", this.getEmail())
                .add("address", this.getAddress())
                .add("parentName", this.getParentName())
                .add("parentPhone", this.getParentPhone())
                .add("parentEmail", this.getParentEmail())
                .add("tags", this.getTags())
                .toString();
    }

    @Override
    public String toMessageString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Parent Name: ")
                .append(getParentName())
                .append("; Parent Phone: ")
                .append(getParentPhone())
                .append("; Parent Email: ")
                .append(getParentEmail())
                .append("; Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
