package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LessonBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.lesson.Lesson;

/**
 * An Immutable StudentBook that is serializable to JSON format.
 */
@JsonRootName(value = "lessonbook")
class JsonSerializableLessonBook {

    public static final String MESSAGE_CONFLICTING_LESSONS = "Lesson list contains duplicate lesson(s).";

    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLessonBook} with the given lessons.
     */
    @JsonCreator
    public JsonSerializableLessonBook(
            @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyLessonBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLessonBook}.
     */
    public JsonSerializableLessonBook(ReadOnlyLessonBook source) {
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this lesson book into the model's {@code LessonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LessonBook toModelType() throws IllegalValueException {
        LessonBook lessonBook = new LessonBook();

        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType(); //get boolean
            if (lessonBook.hasConflictingLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_CONFLICTING_LESSONS);
            }

            lessonBook.addLesson(lesson);
        }

        return lessonBook;
    }
}
