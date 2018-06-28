package com.bridgelabz.autowiringbyconstructor;

public class TextEditor {

	private SpellChecker spellChecker;
	private String name;

	public TextEditor(SpellChecker spellChecker, String name) {
		System.out.println("constructor 2");
		this.spellChecker = spellChecker;
		this.name = name;
	}

	public TextEditor(SpellChecker spellChecker) {
		System.out.println("constructor 1");
		this.spellChecker = spellChecker;
	}

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void spellCheck() {
		spellChecker.checkSpelling();
	}

	@Override
	public String toString() {
		return "SpellChecker [ name=" + name + "]";
	}

}
