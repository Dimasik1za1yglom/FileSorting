package com.example.filesorting.entite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Line {
    String value;
    int startSimvol;

    public static final Comparator<Line> BY_LENGTH = new ByLength();
    public static final Comparator<Line> BY_VALUE = new ByValueIgnoreCase();

    private static class ByLength implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.value.length() - o2.value.length();
        }
    }

    private static class ByValueIgnoreCase implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.value.compareToIgnoreCase(o2.value);
        }
    }
}
