package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class NotesCommandToNotesTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }


}
