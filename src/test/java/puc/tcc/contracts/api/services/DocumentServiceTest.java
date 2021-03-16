package puc.tcc.contracts.api.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import puc.tcc.contracts.api.SampleBaseTestCase;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.mapper.DocumentMapper;
import puc.tcc.contracts.api.persistence.domain.DocumentEntity;
import puc.tcc.contracts.api.persistence.repositories.DocumentRepository;
import puc.tcc.contracts.api.resources.document.DocumentRequest;
import puc.tcc.contracts.api.resources.document.DocumentResponse;
import puc.tcc.contracts.api.services.impl.DocumentServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest extends SampleBaseTestCase {

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService = new DocumentServiceImpl();

    @Test
    @DisplayName("Salvar documento com sucesso")
    void save() throws ContractsApiException {
        when(documentMapper.toModel(any())).thenReturn(getDocumentSaveEntity());

        documentService.saveOrUpdate(getDocumentSaveRequest());
        ArgumentCaptor<DocumentEntity> captor = ArgumentCaptor.forClass(DocumentEntity.class);
        verify(documentRepository).save(captor.capture());

        var documentEntity = captor.getValue();
        assertSaveDocument(getDocumentSaveRequest(), documentEntity);
    }

    private DocumentEntity getDocumentSaveEntity(){
        return DocumentEntity.builder()
                .name("Norma 1")
                .text("Norma teste")
                .build();
    }

    private DocumentRequest getDocumentSaveRequest(){
        return DocumentRequest.builder()
                .name("Norma 1")
                .text("Norma teste")
                .build();
    }


    private void assertSaveDocument(DocumentRequest expected, DocumentEntity actual){
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(1, actual.getVersion());
        assertNotNull(actual.getDatUpdated());
    }

    @Test
    @DisplayName("Atualizar documento com sucesso")
    void update() throws ContractsApiException {
        when(documentMapper.toModel(any())).thenReturn(getDocumentUpdateEntity());
        when(documentRepository.findById(anyLong())).thenReturn(Optional.of(getDocumentUpdateEntity()));
        when(documentMapper.toResponse(any())).thenReturn(getDocumentUpdateResponse());

        var document = documentService.saveOrUpdate(getDocumentUpdateRequest());
        ArgumentCaptor<DocumentEntity> captor = ArgumentCaptor.forClass(DocumentEntity.class);
        verify(documentRepository).save(captor.capture());

        var documentEntity = captor.getValue();
        assertUpdateDocument(getDocumentUpdateRequest(), documentEntity);
    }

    private DocumentEntity getDocumentUpdateEntity(){
        return DocumentEntity.builder()
                .id(1L)
                .name("Norma 2")
                .text("Norma teste 2")
                .version(1)
                .datUpdated(LocalDateTime.now())
                .build();
    }

    private DocumentResponse getDocumentUpdateResponse(){
        return DocumentResponse.builder()
                .id(1L)
                .name("Norma 2")
                .text("Norma de teste 2")
                .version(2)
                .build();
    }

    private DocumentRequest getDocumentUpdateRequest(){
        return DocumentRequest.builder()
                .id(1L)
                .name("Norma 2")
                .text("Norma teste 2")
                .build();
    }

    private void assertUpdateDocument(DocumentRequest expected, DocumentEntity actual){
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(2, actual.getVersion());
        assertNotNull(actual.getDatUpdated());
    }



}
