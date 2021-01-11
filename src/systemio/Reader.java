package systemio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class Reader implements IOSystem {
    /**
     * Citeste datele dintr-un fisier si returneaza un obiect cu toate datele
     * @param inputFile
     * @return
     * @throws IOException
     */
    public InputData read(final String inputFile) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(new File(inputFile), InputData.class);
    }

    /**
     * Citeste datele dintr-un fisier si returneaza un obiect cu toate datele
     * @param file
     * @param outputData
     * @return
     * @throws IOException
     */
    public InputData doYourMagic(final String file,
                                 final OutputData outputData) throws IOException {
        return read(file);
    }
}
