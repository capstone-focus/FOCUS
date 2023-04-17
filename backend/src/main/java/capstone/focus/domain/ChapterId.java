package capstone.focus.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
