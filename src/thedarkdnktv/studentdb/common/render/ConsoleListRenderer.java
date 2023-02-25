package thedarkdnktv.studentdb.common.render;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ConsoleListRenderer<T> {

    private static final String IDX_LABEL = "Индекс";

    private final List<ColumnData<T>> columns;
    private boolean printHeader;
    private boolean printIndex;

    public final List<T> data;

    public ConsoleListRenderer() {
        this.columns = new LinkedList<>();
        this.data = new LinkedList<>();
        this.printHeader = true;
    }

    public void render() {
        final String format = buildRowFormat();
        final int width = getTotalWidth();

        if (printHeader) {
            printHeader(format, width);
        }

        for (int i = 0; i < data.size(); i++) {
            List<Object> list = buildDataFormat(data.get(i));
            if (printIndex) {
                list.add(0, Integer.toString(i));
            }

            out.printf(format, list.toArray());
        }

        printFooter(width);
    }

    private void printHeader(String format, int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            builder.append('#');
        }

        List<Object> header = columns.stream()
            .map(ColumnData::getLabel)
            .collect(Collectors.toList()); // String array actually
        if (printIndex) {
            header.add(0, IDX_LABEL);
        }

        out.println(builder);
        out.printf(format, header.toArray());
        out.println(builder);
    }

    private void printFooter(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            builder.append('#');
        }

        builder.append('\n');
        out.println(builder);
    }

    private String buildRowFormat() {
        final StringBuilder builder = new StringBuilder();

        {
            builder.append("# ");

            if (printIndex) {
                builder.append("%-8s | ");
            }

            for (ColumnData<T> column : columns) {
                builder.append('%');
                builder.append('-');
                builder.append(column.width);
                builder.append('s');
                builder.append(" | ");
            }

            builder.replace(builder.length() - 3, builder.length(), " #\n");
        }

        return builder.toString();
    }

    private int getTotalWidth() {
        int width = 4; // first and last 2 chars '# '
        width += columns.stream()
            .mapToInt(ColumnData::getWidth)
            .sum(); // data chars
        width += Math.max(0, (columns.size() - 1) * 3); // padding chars

        if (printIndex) {
            width += 11;
        }

        return width;
    }

    private List<Object> buildDataFormat(T obj) {
        List<Object> result = new LinkedList<>();
        for (int i = 0; i < columns.size(); i++) {
            result.add(columns.get(i).getDataForDisplay(obj));
        }

        return result;
    }

    public ConsoleListRenderer<T> setRenderHeader(boolean value) {
       this.printHeader = value;
       return this;
    }

    public ConsoleListRenderer<T> setPrintIndex(boolean value) {
        this.printIndex = value;
        return this;
    }

    public ConsoleListRenderer<T> addColumn(String label, int width, Function<T, ?> dataGetter) {
        columns.add(new ColumnData<>(label, width, dataGetter));
        return this;
    }

    private static class ColumnData<T> {

        final String label;
        final int width;
        final Function<T, ?> dataGetter;

        public ColumnData(String label, int width, Function<T, ?> dataGetter) {
            this.label = label;
            this.width = width;
            this.dataGetter = dataGetter;
        }

        public String getLabel() {
            return label;
        }

        public int getWidth() {
            return width;
        }

        public String getDataForDisplay(T object) {
            StringBuilder builder = new StringBuilder();
            builder.append(dataGetter.apply(object));

            if (builder.length() > width) {
                builder.replace(width - 3, builder.length(), "...");
            }

            return builder.toString();
        }
    }
}
