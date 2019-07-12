/**
 * Range用クラス
 */
public class Range<T extends Comparable> {
    public T lowerBound;
    public T upperBound;
	/**
	 * Constructor
	 */
    public Range(T lowerBound, T upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean includes(T value) {
        return lowerBound.compareTo(value) <= 0 && value.compareTo(upperBound) <= 0;
    }
}
