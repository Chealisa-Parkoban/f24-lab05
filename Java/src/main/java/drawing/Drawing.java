package drawing;

import drawing.shapes.Line;
import drawing.shapes.Shape;
import drawing.writing.JPEGWriter;
import drawing.writing.PNGWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Refactor Task 3: (Mis-)Shaped
 *
 * @author Zishen Wen (F22), Deyuan Chen (S22)
 */
public class Drawing {

    private List<Shape> shapes;
    private Map<String, Writer> writers;

    public Drawing(List<Shape> shapes) {
        this.shapes = shapes;
        writers = new HashMap<>();
        writers.put("jpeg", new JPEGWriter("jpeg"));
        writers.put("png", new PNGWriter("png"));
    }

    /**
     * Draw shapes to a file with given file format.
     *
     * @param format   file format
     * @param filename file name
     */
    public void draw(String format, String filename) {
        // TODO: Do you notice any issues here?
        // Repetitive code
        // create a writers map to store the writer for each format
        try (Writer writer = writers.get(format)) {
            for (Shape shape : this.shapes) {
                // TODO: What is the issue of the behavior here?
                // The lines are created by the shape
                // this job should be integrated into draw method
                // so that it ensures the information hiding principle
                // and the draw method can take only one parameter writer
                Line[] lines = shape.toLines();
                shape.draw(writer, lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (format.equals("jpeg")) {
//            try (Writer writer = new JPEGWriter(filename + ".jpeg")) {
//                for (Shape shape : this.shapes) {
//
//                    Line[] lines = shape.toLines();
//                    shape.draw(writer, lines);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (format.equals("png")) {
//            try (Writer writer = new PNGWriter(filename + ".png")) {
//                for (Shape shape : this.shapes) {
//                    Line[] lines = shape.toLines();
//                    shape.draw(writer, lines);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}

