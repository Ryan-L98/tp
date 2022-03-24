package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.model.Model;

public class ListLessonsCommand extends Command {
    public static final String COMMAND_WORD = "listlessons";
    public static final String SHORTENED_COMMAND_WORD = "ll";

    public static final String MESSAGE_SUCCESS = "Listed all lessons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS, ViewTab.LESSON);
    }
}
