package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    CodeRepository codeRepository;

    public List<Code> findCodeList() {
        return codeRepository.findCodeList();
    }
}
