package com.groupdocs.conversion.examples.advanced_usage.loading.load_options_by_document_type.txt;

import com.groupdocs.conversion.examples.Constants;

import java.io.File;

/**
* This example demonstrates how to convert a txt document to pdf with advanced options
*/
public class ConvertTxtByControlingLeadingSpacesBehavior {
    public static void run()
    {
        String outputFolder = Constants.getOutputDirectoryPath(null);
        String outputFile = new File(outputFolder, "converted.pdf").getPath();


        Contracts.Func<LoadOptions> getLoadOptions = () => new TxtLoadOptions
        {
            LeadingSpacesOptions = TxtLeadingSpacesOptions.ConvertToIndent,
                    DetectNumberingWithWhitespaces = true
        };

        Converter converter = new Converter(Constants.SAMPLE_TXT, getLoadOptions);
        PdfConvertOptions options = new PdfConvertOptions();
        converter.convert(outputFile, options);


        System.out.print("\nTxt document converted successfully. \nCheck output in "+ outputFolder);
    }
}