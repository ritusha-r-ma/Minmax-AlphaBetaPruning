package validation;

import graph.MinMaxWithAlphaBeta;
import utilities.ErrorHandlingUtil;
import graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileValidation {

    Graph graph;
    GraphValidation gv;

    public FileValidation(String path) {
        graph = new Graph();
        readInputFile(path);
        gv = new GraphValidation(graph);
        new MinMaxWithAlphaBeta(graph);
    }

    private void readInputFile(String ioPath) {
        try {
            int lineNumber = 1;
            String line;
            BufferedReader br = new BufferedReader(new FileReader(ioPath));
            while ((line = br.readLine()) != null) {
                massageInputFile(line.replace(" ", ""), lineNumber);
                lineNumber++;
            }
        } catch (Exception e) {
            ErrorHandlingUtil.errorOccurred(e.getMessage());
        }
    }

    /**
     * Read the file and process into graph
     * @param line
     * @param lineNumber
     */
    private void massageInputFile(String line, int lineNumber) {
        if (line.contains(":")) {
            String[] text = line.split(":");

            if (text.length != 2) {
                ErrorHandlingUtil.errorOccurred(lineNumber + "of the text file contains bad parameter");
            }

            String[] neighbour = text[1].replace("[", "").replace("]", "").split(",");
            graph.addNonLeafVertex(text[0], neighbour);
        } else if (line.contains("=")) {
            String[] text = line.split("=");
            if (text.length != 2) {
                ErrorHandlingUtil.errorOccurred(lineNumber + "of the text file contains bad parameter");
            }

            graph.addLeafVertex(text[0], Float.valueOf(text[1]));
        } else {
            ErrorHandlingUtil.errorOccurred(lineNumber + "Bad Format");
        }
    }
}
