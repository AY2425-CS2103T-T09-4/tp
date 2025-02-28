package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnpinCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnpinCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnpinCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnpinCommandParserTest {

    private UnpinCommandParser parser = new UnpinCommandParser();

    @Test
    public void parse_validArgs_returnsUnpinCommand() {
        assertParseSuccess(parser, "1", new UnpinCommand(List.of(INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_multipleValidArgs_returnsUnpinCommand() {
        assertParseSuccess(parser, "1 2 3",
                new UnpinCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnpinCommand.MESSAGE_USAGE));
    }
}
