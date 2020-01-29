package com.groupdocs.conversion.examples.basic_usage.convert_to_image;

import com.groupdocs.conversion.examples.Constants;

import java.io.File;

/**
* This example demonstrates how to convert document to PNG.
*/
public class ConvertToPng {
    public static void run()
    {
        String outputFolder = Constants.getOutputDirectoryPath(null);
        String outputFileTemplate = new File(outputFolder, "converted-page-{0}.png").getPath();

        SavePageStream getPageStream = page => new FileStream(string.Format(outputFileTemplate, page), FileMode.Create);

        Converter converter = new Converter(Constants.SAMPLE_PDF);
        ImageConvertOptions options = new ImageConvertOptions();
        options.setFormat(ImageFileType.Png);
        converter.convert(getPageStream, options);


        System.out.print("\nConversion to png completed successfully. \nCheck output in " + outputFolder);
    }
}