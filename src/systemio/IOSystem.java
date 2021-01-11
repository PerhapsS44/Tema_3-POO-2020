package systemio;

import java.io.IOException;

public interface IOSystem {
    /**
     * Ruleaza read/write in functie de input
     * @param file
     * @param outputData
     * @return
     * @throws IOException
     */
    InputData doYourMagic(String file, OutputData outputData) throws IOException;
}
