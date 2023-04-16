package capstone.focus.domain;

import java.io.Serializable;
import java.util.Objects;

public class ChapterId implements Serializable {
    private int seq;
    private Long book;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChapterId))
            return false;
        ChapterId other = (ChapterId)obj;
        return other.seq == this.seq && Objects.equals(other.book, this.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq + book);
    }
}
