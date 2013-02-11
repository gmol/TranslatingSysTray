package org.gmol.TranslatingSysTray.translator;

import org.gmol.TranslatingSysTray.translator.IDataset;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

public interface ITranslator {
	//
	IDataset translate(String word) throws TranslatorEx;
}
