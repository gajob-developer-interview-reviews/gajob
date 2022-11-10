package go.nkrcd.web.review.service;

import go.nkrcd.web.review.model.Experience;
import go.nkrcd.web.review.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    @Autowired
    ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }

    public List<Map<String, Object>> findTop3(List<Long> rIds) {
        return experienceRepository.findTop3(rIds, PageRequest.of(0, 3));
    }
}
