package helpers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

public interface Key {
    public BigDecimal generatedKey(PreparedStatement statement);
}
