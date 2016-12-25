package week09;

import java.util.List;
import java.util.Map;

/**
 * Created by mestanov on 15.12.16.
 */
public interface Mappable {
    Map<String, String> toMap();

    Mappable fromList(List<String> attributes);
}
