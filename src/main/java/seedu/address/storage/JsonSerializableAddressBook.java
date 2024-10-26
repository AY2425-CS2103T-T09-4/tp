package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_ARCHIVED_PERSON = "ArchivedPersons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPerson> archivedPersons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons an archivedPersons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("archivedPersons") List<JsonAdaptedPerson> archivedPersons) {
        this.persons.addAll(persons);
        this.archivedPersons.addAll(archivedPersons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::of).collect(Collectors.toList()));
        archivedPersons.addAll(source.getArchivedPersonList().stream()
                .map(JsonAdaptedPerson::of).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        addPersonsToModel(addressBook);
        addArchivedPersonsToModel(addressBook);
        return addressBook;
    }


    // Helper Methods

    /**
     * Adds the active persons from the JSON serialized data to the provided {@code AddressBook}.
     * This method iterates over the list of {@code JsonAdaptedPerson} objects, converts each to a {@code Person},
     * and adds them to the {@code AddressBook}'s active list.
     *
     * @param addressBook The {@code AddressBook} where the active persons will be added.
     * @throws IllegalValueException if there are duplicate persons in the list or if any data constraints are violated.
     */
    private void addPersonsToModel(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
    }

    /**
     * Adds the archived persons from the JSON serialized data to the provided {@code AddressBook}.
     * This method iterates over the list of {@code JsonAdaptedPerson} objects, converts each to a {@code Person},
     * and archives them in the {@code AddressBook}'s archived list.
     *
     * @param addressBook The {@code AddressBook} where the archived persons will be added.
     * @throws IllegalValueException if there are duplicate persons in the list or if any data constraints are violated.
     */
    private void addArchivedPersonsToModel(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPerson jsonAdaptedPerson : archivedPersons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ARCHIVED_PERSON);
            }
            addressBook.archivePerson(person);
        }
    }


}
