package systemio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class Writer implements IOSystem {
    /**
     * Scrie datele intr-un fisier
     *
     * @param outputFile
     * @param result
     * @throws IOException
     */
    public void write(final String outputFile, final OutputData result) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        obj.writeValue(new File(outputFile), result);
    }

    /**
     * Scrie datele intr-un fisier
     *
     * @param file
     * @param outputData
     * @return
     * @throws IOException
     */
    public InputData doYourMagic(final String file,
                                 final OutputData outputData) throws IOException {
        write(file, outputData);
        return null;
    }
}
