package com.bridgelabz.fundonotes.note.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.LabelException;
import com.bridgelabz.fundonotes.note.exceptions.NoteException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Label;
import com.bridgelabz.fundonotes.note.models.LabelDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;
import com.bridgelabz.fundonotes.note.repository.LabelRepository;
import com.bridgelabz.fundonotes.note.repository.NoteRepository;
import com.bridgelabz.fundonotes.note.util.NoteUtility;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noterespositry;

	@Autowired
	private LabelRepository labelRepository;

	@Override
	public ViewNoteDTO createNote(CreateNoteDTO createNoteDTO, String userId)
			throws NoteNullPointerException, LabelException {

		NoteUtility.validateNote(createNoteDTO);

		Note note = new Note();
		note.setTitle(createNoteDTO.getTitle());
		note.setDescription(createNoteDTO.getDescription());
		if (createNoteDTO.getColour() != null || createNoteDTO.getColour().trim().length() != 0) {
			note.setColour(createNoteDTO.getColour());
		}
		note.setRemainder(createNoteDTO.getRemainder());
		note.setUserid(userId);
		note.setCreatedAt(NoteUtility.createdAt());
		note.setUpdatedAt(NoteUtility.createdAt());
		note.setArchive(createNoteDTO.getArchive());
		note.setPin(createNoteDTO.getPin());

		List<Label> label = createNoteDTO.getLabellist();

		for (int i = 0; i < label.size(); i++) {
			if (label.get(i).getLabelName() != null || label.get(i).getLabelName().trim().length() != 0) {

				Optional<Label> optionallabel = labelRepository.findByLabelName(label.get(i).getLabelName());

				if (optionallabel.isPresent()) {
					List<LabelDTO> list = new ArrayList<>();
					LabelDTO newlabel = new LabelDTO();
					newlabel.setLabelId(optionallabel.get().getLabelId());
					newlabel.setLabelName(label.get(i).getLabelName());
					list.add(newlabel);
					note.setLabel(list);
				} else {
					Label label1 = new Label();
					label1.setLabelName(label.get(i).getLabelName());
					label1.setLabelId(label.get(i).getLabelId());
					label1.setUserId(userId);
					labelRepository.save(label1);

					List<LabelDTO> list2 = new ArrayList<>();
					LabelDTO newlabel = new LabelDTO();
					newlabel.setLabelId(optionallabel.get().getLabelId());
					newlabel.setLabelName(label.get(i).getLabelName());
					list2.add(newlabel);
					note.setLabel(list2);
				}
			}
		}
		noterespositry.save(note);

		ViewNoteDTO viewnote = new ViewNoteDTO();
		viewnote.setId(note.getNoteId());
		viewnote.setTitle(note.getTitle());
		viewnote.setDescription(note.getDescription());
		viewnote.setColour(note.getColour());
		viewnote.setRemainder(note.getRemainder());
		viewnote.setCreatedAt(note.getCreatedAt());
		viewnote.setUpdatedAt(note.getUpdatedAt());
		viewnote.setLable(note.getLabel());
		return viewnote;

	}

	@Override
	public void updateNote(UpdateNoteDTO updateNoteDTO, String userId, String noteId)
			throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {

		Optional<Note> optionalnote = noterespositry.findById(noteId);

		if (!optionalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}

		if (!userId.equals(optionalnote.get().getUserid())) {

			throw new UnauthorizedException("User Not Present");
		}

		Note note = optionalnote.get();
		note.setTitle(updateNoteDTO.getTitle());
		note.setDescription(updateNoteDTO.getDescription());
		note.setColour(updateNoteDTO.getColour());
		note.setUpdatedAt(new Date());
		noterespositry.save(note);
	}

	@Override
	public void deleteNote(String userId, String noteId) throws UnauthorizedException, NoteNotFoundException {

		Optional<Note> optionalnote = noterespositry.findById(noteId);

		if (!optionalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}

		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new UnauthorizedException("User Not Present");

		}

		Note note = optionalnote.get();
		note.setTrash(true);
		noterespositry.save(note);

	}

	@Override
	public void deleteorrestore(String userId, String noteId, boolean isBoolean)
			throws UnauthorizedException, NoteException, NoteNotFoundException {

		Optional<Note> note = noterespositry.findById(noteId);
		if (!note.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(note.get().getUserid())) {
			throw new UnauthorizedException(" User not Present ");
		}

		if (!note.get().isTrash()) {
			throw new NoteException("Note is Not TrashedAt!!");
		}
		if (isBoolean) {
			noterespositry.deleteById(noteId);
		} else {
			Note newnote = note.get();
			newnote.setTrash(false);
			noterespositry.save(newnote);
		}
	}

	@Override
	public void emptyTrash(String userId) throws UnauthorizedException, NoteNotFoundException {

		List<Note> list = noterespositry.findAllByUserId(userId);

		List<Note> notelist = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			notelist.add(list.get(i));

			if (notelist.get(i).isTrash()) {
				noterespositry.delete(notelist.get(i));
			}
		}

	}

	@Override
	public Note displayNote(String userId, String noteId) throws UnauthorizedException, NoteNotFoundException {

		Optional<Note> optinallist = noterespositry.findById(noteId);

		if (!optinallist.isPresent()) {
			throw new NoteNotFoundException(" Note not found ");
		}
		if (!userId.equals(optinallist.get().getUserid())) {

			throw new UnauthorizedException(" User not Present ");
		}

		return optinallist.get();

	}

	public List<ViewNoteDTO> viewAllNote(String userId) {

		List<Note> noteList = noterespositry.findAllByUserId(userId);

		List<ViewNoteDTO> viewNoteList = new LinkedList<>();

		for (int i = 0; i < noteList.size(); i++) {

			if (!noteList.get(i).isTrash()) {

				ViewNoteDTO viewNote = new ViewNoteDTO();
				Note note = noteList.get(i);
				viewNote.setId(note.getNoteId());
				viewNote.setTitle(note.getTitle());
				viewNote.setDescription(note.getDescription());
				viewNote.setCreatedAt(note.getCreatedAt());
				viewNote.setUpdatedAt(note.getUpdatedAt());
				viewNote.setRemainder(note.getRemainder());
				viewNote.setArchive(note.isArchive());
				viewNote.setPin(note.isPin());
				viewNote.setLable(note.getLabel());
				viewNoteList.add(viewNote);
			}
		}
		return viewNoteList;
	}

	@Override
	public void addRemainder(String userId, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException {

		NoteUtility.validateDate(date);

		Optional<Note> optionalnote = noterespositry.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new NoteNotFoundException("Note not Found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new UnauthorizedException("User not present");
		}
		Note note = optionalnote.get();
		note.setRemainder(date);
		noterespositry.save(note);
	}

	@Override
	public void removeRemainder(String userId, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException {

		NoteUtility.validateDate(date);

		Optional<Note> optionalnote = noterespositry.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new UnauthorizedException("User not present");
		}
		Note note = optionalnote.get();
		note.setRemainder(null);
		noterespositry.save(note);
	}

	@Override
	public void addpinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		Optional<Note> optinalnote = noterespositry.findById(noteId);
		if (!optinalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(optinalnote.get().getUserid())) {
			throw new UnauthorizedException("User not Present");

		}
		if (optinalnote.get().isPin()) {
			throw new NoteException("Note is Already Pinned");
		}

		Note note = optinalnote.get();
		note.setPin(true);
		noterespositry.save(note);

	}

	@Override
	public void removepinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		Optional<Note> optinalnote = noterespositry.findById(noteId);
		if (!optinalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(optinalnote.get().getUserid())) {
			throw new UnauthorizedException("User not Present");

		}
		if (!optinalnote.get().isPin()) {
			throw new NoteException("Note is Un-Pinned!!");
		}

		Note note = optinalnote.get();
		note.setPin(false);
		noterespositry.save(note);

	}

	@Override
	public void addArchivePinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		Optional<Note> optinalnote = noterespositry.findById(noteId);
		if (!optinalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(optinalnote.get().getUserid())) {
			throw new UnauthorizedException("User not Present");

		}
		if (!optinalnote.get().isPin()) {
			throw new NoteException("Note is Archived Already");
		}

		Note note = optinalnote.get();
		note.setArchive(true);
		noterespositry.save(note);

	}

	@Override
	public void removeArchivePinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		Optional<Note> optinalnote = noterespositry.findById(noteId);
		if (!optinalnote.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}
		if (!userId.equals(optinalnote.get().getUserid())) {
			throw new UnauthorizedException("User not Present");

		}
		if (!optinalnote.get().isPin()) {
			throw new NoteException("Note is Not Archived");
		}

		Note note = optinalnote.get();
		note.setArchive(false);
		noterespositry.save(note);
	}

	@Override
	public List<ViewNoteDTO> viewAllArchiveNote(String userId) {

		List<Note> noteList = noterespositry.findAllByUserId(userId);

		List<ViewNoteDTO> viewNoteList = new LinkedList<>();

		for (int i = 0; i < noteList.size(); i++) {

			if (!noteList.get(i).isArchive()) {

				ViewNoteDTO viewNote = new ViewNoteDTO();
				Note note = noteList.get(i);
				viewNote.setTitle(note.getTitle());
				viewNote.setDescription(note.getDescription());
				viewNote.setCreatedAt(note.getCreatedAt());
				viewNote.setUpdatedAt(note.getUpdatedAt());
				viewNote.setRemainder(note.getRemainder());
				viewNote.setArchive(note.isArchive());
				viewNote.setPin(note.isPin());
				viewNoteList.add(viewNote);
			}
		}
		return viewNoteList;
	}

	@Override
	public void createLabel(String labelName, String userId) throws LabelException, UnauthorizedException {

		if (!noterespositry.findById(userId).isPresent()) {

			throw new UnauthorizedException("Invalid token");
		}

		if (labelRepository.findByLabelName(labelName).isPresent()) {
			throw new LabelException("Label already exists");
		}

		Label label = new Label();
		label.setLabelName(labelName);
		label.setUserId(userId);
		labelRepository.save(label);
	}

	@Override
	public List<Label> viewLabels(String userId) throws UnauthorizedException {

		List<Label> list = labelRepository.findAllByuserId(userId);

		List<Label> newlist = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			newlist.add(list.get(i));
		}
		return newlist;
	}

	@Override
	public void deleteLabel(String userId, String labelId) throws UnauthorizedException, LabelException {

		Optional<Label> optinallabel = labelRepository.findByLabelId(labelId);

		if (!optinallabel.isPresent()) {
			throw new LabelException("Label Not found");
		}

		Label newlabel = optinallabel.get();
		labelRepository.delete(newlabel);

		List<Note> notelist = noterespositry.findAllByUserId(userId);

		for (int i = 0; i < notelist.size(); i++) {
			Note note1 = notelist.get(i);
			for (int j = 0; j < notelist.get(i).getLabel().size(); j++) {
				if (note1.getLabel().get(j).getLabelId().equals(labelId)) {
					note1.getLabel().remove(i);
					break;
				} else {
					throw new LabelException("Label Not found");
				}
			}
			noterespositry.save(note1);
		}
	}

	@Override
	public void addLabeltoNote(String userId, String noteId, String labelName)
			throws UnauthorizedException, NoteNotFoundException, LabelException {

		Optional<Note> note = noterespositry.findById(noteId);

		if (!note.isPresent()) {
			throw new NoteNotFoundException("Note not found");
		}

		if (!userId.equals(note.get().getUserid())) {
			throw new UnauthorizedException("User not Present");
		}

		NoteUtility.validateLabel(labelName);

		Optional<Label> label = labelRepository.findByLabelName(labelName);

		if (!label.isPresent()) {
			Label label1 = new Label();
			label1.setLabelName(labelName);
			label1.setUserId(userId);
			labelRepository.save(label1);
		} else {

			Note note1 = note.get();
			ArrayList<LabelDTO> label2 = (ArrayList<LabelDTO>) note1.getLabel();

			LabelDTO newlabel = new LabelDTO();
			newlabel.setLabelId(label.get().getLabelId());
			newlabel.setLabelName(label.get().getLabelName());
			label2.add(newlabel);
			note1.setLabel(label2);

			noterespositry.save(note1);
		}
	}

	@Override
	public void updateLabel(String userId, String labelId, String labelName)
			throws UnauthorizedException, NoteNotFoundException, LabelException {

		Optional<Label> optinallabel = labelRepository.findById(labelId);

		if (!optinallabel.isPresent()) {
			throw new LabelException("Label Not found");
		}

		Label newlabel = optinallabel.get();
		newlabel.setLabelName(labelName);
		labelRepository.save(newlabel);

		List<Note> notelist = noterespositry.findAllByUserId(userId);

		for (int i = 0; i < notelist.size(); i++) {
			Note note1 = notelist.get(i);
			for (int j = 0; j < notelist.get(i).getLabel().size(); j++) {
				if (note1.getLabel().get(j).getLabelId().equals(labelId)) {
					note1.getLabel().get(j).setLabelName(labelName);
					break;
				} else {
					throw new LabelException("Label not found");
				}
			}
			noterespositry.save(note1);
		}
	}

	@Override
	public void removeLabelFromNote(String userId, String labelId, String noteId)
			throws NoteNotFoundException, UnauthorizedException, LabelException {

		Optional<Note> optinalnote = noterespositry.findById(noteId);
		if (!optinalnote.isPresent()) {
			throw new NoteNotFoundException("Note not Found");
		}
		if (!optinalnote.get().getUserid().equals(userId)) {
			throw new UnauthorizedException(" Unauthorized User");
		}
		Note note = optinalnote.get();
		List<LabelDTO> labellist = note.getLabel();

		for (int i = 0; i < labellist.size(); i++) {
			if (labellist.get(i).getLabelId().equals(labelId)) {
				labellist.remove(i);
				break;
			} else {
				throw new LabelException("Label not found");
			}
		}
		noterespositry.save(note);
	}
}
