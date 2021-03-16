package puc.tcc.contracts.api.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.resources.document.DocumentRequest;
import puc.tcc.contracts.api.mapper.DocumentMapper;
import puc.tcc.contracts.api.persistence.domain.DocumentEntity;
import puc.tcc.contracts.api.persistence.repositories.DocumentRepository;
import puc.tcc.contracts.api.resources.document.DocumentResponse;
import puc.tcc.contracts.api.services.DocumentService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    @Transactional
    public DocumentResponse saveOrUpdate(final DocumentRequest documentRequest) {
        var model = documentMapper.toModel(documentRequest);
        model.setVersion(getVersion(documentRequest));
        model.setDatUpdated(LocalDateTime.now());
        model = documentRepository.save(model);
        log.info("document saved/updated document={}", model);
        return documentMapper.toResponse(model);
    }

    private Integer getVersion(DocumentRequest documentRequest) {
        if(documentRequest.getId() != null){
            var document = documentRepository.findById(documentRequest.getId());
            if (document.isPresent()){
                return document.get().getVersion() + 1;
            }
        }
        return 1;
    }

    @Override
    public Page<DocumentEntity> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return documentRepository.search(pageRequest);
    }

    @Override
    public Optional<DocumentResponse> findById(final Long id){
        var document = documentRepository.findById(id);
        return document.map(documentMapper::toResponse);
    }

    @Override
    public List<DocumentResponse> findAll() {
        List<DocumentResponse> items = new ArrayList<>();
        var documents = documentRepository.findAll();
        documents.forEach(item -> items.add(documentMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws ContractsApiException {
        var document = documentRepository.findById(id);
        if(document.isEmpty()){
            throw new ContractsApiException(HttpStatus.NOT_FOUND, "document", "Documento não existe!");
        }
        documentRepository.deleteById(id);
    }
}
