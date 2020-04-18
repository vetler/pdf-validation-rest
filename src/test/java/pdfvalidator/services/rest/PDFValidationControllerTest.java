package pdfvalidator.services.rest;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.multipart.StreamingFileUpload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import pdfvalidator.services.rest.service.Validator;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PDFValidationControllerTest {

    @Test
    void should_return_429() throws InterruptedException {
        var validator = mock(Validator.class);
       // when(validator.validate(any())).thenReturn(Validator.Validation.build().build());

        var controller = new PDFValidationController(validator);
        var file = mock(StreamingFileUpload.class);
        when(file.getFilename()).thenReturn("test.pdf");
        when(file.transferTo(any(File.class))).thenReturn(Subscriber::onComplete);

        controller.validate(Optional.empty(), file);

        var response1 = controller.validate(Optional.empty(), file);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response1.blockingGet().getStatus());

        Thread.sleep(1000);

        var response2 = controller.validate(Optional.empty(), file);
        assertEquals(HttpStatus.OK, response2.blockingGet().getStatus());
    }

}