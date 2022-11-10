package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired 
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public List<Code> findCodeList() {
        return codeRepository.findCodeList();
    }

    public Code findByCId(String cdId) {
        return codeRepository.findByCId(cdId);
    }
}
