package learning.handwriting.core;

public class FloatDrawing {
    private double[][] pixels;

    public FloatDrawing(int width, int height) {
        pixels = new double[width][height];
    }

    public FloatDrawing(double[][] pixels) {
        this.pixels = pixels;
    }

    public FloatDrawing(Drawing src) {
        pixels = new double[src.getWidth()][src.getHeight()];
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                pixels[x][y] = src.isSet(x, y) ? 1.0 : 0.0;
            }
        }
    }

    public double get(int x, int y) {
        return pixels[x][y];
    }

    public void set(int x, int y, double value) {
        pixels[x][y] = value;
    }

    // TODO: Calculate a weighted average. The argument d1weight is a value
    //  between zero and one. Each pixel in the returned FloatDrawing
    //  is calculated as follows:
    //  d1weight * d1 pixel value + (1.0 - d1weight) * d2 pixel value
    public static FloatDrawing weightedAverageOf(FloatDrawing d1, FloatDrawing d2, double d1weight) {
        int width = d1.getWidth();
        int height = d1.getHeight();
        double pixelvalued1;
        double pixelvalued2;
        double weightedValue;
        FloatDrawing floatDrawing = new FloatDrawing(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelvalued1 = d1.get(i, j);
                pixelvalued2 = d2.get(i, j);
                weightedValue = d1weight * pixelvalued1 + (1.0 - d1weight) * pixelvalued2;
                floatDrawing.set(i, j, weightedValue);
            }
        }

        return floatDrawing;
    }

    public int getWidth() {
        return pixels.length;
    }

    public int getHeight() {
        return pixels[0].length;
    }

    // TODO: Calculate the pixel-by-pixel Euclidean distance between these two
    //  FloatDrawing objects.
    public double euclideanDistance(FloatDrawing other) {
        int width = this.getWidth();
        int height = this.getHeight();
        double thisFloatDrawing;
        double valueOther;
        double difference;
        double sum = 0;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                thisFloatDrawing = this.get(i, j);
                valueOther = other.get(i, j);
                difference = thisFloatDrawing - valueOther;
                sum += Math.pow(difference, 2);
            }
        }

        return sum;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof FloatDrawing that) {
            if (this.getHeight() == that.getHeight() && this.getWidth() == that.getWidth()) {
                for (int x = 0; x < getWidth(); x++) {
                    for (int y = 0; y < getHeight(); y++) {
                        if (this.pixels[x][y] != that.pixels[x][y]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                result.append(pixels[x][y]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
