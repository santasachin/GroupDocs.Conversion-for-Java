package com.groupdocs.conversion.examples.advanced_usage.loading.loading_documents_from_different_sources;

import com.groupdocs.conversion.examples.Constants;

import java.io.File;
import java.io.FileInputStream;


/**
* This example demonstrates how to convert document from stream.
*/
public class LoadDocumentFromStream {
    public static void run()
    {
        String outputDirectory = Constants.getOutputDirectoryPath(null);
        String outputFile = new File(outputDirectory, "converted.pdf").getPath();

        Converter converter = new Converter(new FileInputStream(Constants.SAMPLE_DOCX));
        PdfConvertOptions options = new PdfConvertOptions();

        converter.convert(outputFile, options);


        System.out.println("\nSource document converted successfully.\nCheck output in " + outputDirectory);
    }

}