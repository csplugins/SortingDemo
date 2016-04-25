public enum Sort {
    QUICK("Quick Sort"), INSERT("Insertion Sort"), MERGE("Merge Sort"), BUBBLE("Bubble Sort"), RADIX("Radix Sort");
    private final String name;

    Sort(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
