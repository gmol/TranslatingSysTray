package org.gmol.TranslatingSysTray.translator;

import org.gmol.TranslatingSysTray.translator.IDataset;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

public interface ITranslator {
	//
	Dataset translate(String word) throws TranslatorEx;
	//
	IDataset translateee(String word) throws TranslatorEx;

}
