package org.gmol.TranslatingSysTray.translator;

public interface ITranslator {
	//
	Dataset translate(String word) throws TranslatorEx;
	//
	IDataset translateee(String word) throws TranslatorEx;

}
