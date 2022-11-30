package go.nkrcd.web.review.service;

import go.nkrcd.web.review.model.Experience;

import java.util.List;
import java.util.Map;

public interface ExperienceService {

    public Experience save(Experience experience);
    public List<Map<String, Object>> findTop3(List<Long> rIds);
}
