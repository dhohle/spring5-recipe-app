package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Override
    public NotesCommand convert(Notes source) {
        if (source == null)
            return null;
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
