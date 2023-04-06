package ru.mch.dreamjob.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.mch.dreamjob.dto.FileDto;
import ru.mch.dreamjob.entity.Candidate;
import ru.mch.dreamjob.entity.City;
import ru.mch.dreamjob.service.CandidateService;
import ru.mch.dreamjob.service.CityService;
import java.util.List;
import java.util.Optional;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CandidateControllerTest {

    private CandidateService candidateService;

    private CityService cityService;

    private CandidateController candidateController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        candidateService = mock(CandidateService.class);
        cityService = mock(CityService.class);
        candidateController = new CandidateController(candidateService, cityService);
        testFile = new MockMultipartFile("testFile.img", new byte[]{1, 2, 3});
    }

    @Test
    public void whenRequestCandidateListPageThenGetPageWithCandidates() {
        var candidate1 = new Candidate(1, "Name1", "desc1", now(), 1, 2);
        var candidate2 = new Candidate(2, "Name2", "desc2", now(), 2, 4);
        List<Candidate> expectedCandidates = List.of(candidate1, candidate2);
        when(candidateService.findAll()).thenReturn(expectedCandidates);
        ConcurrentModel model = new ConcurrentModel();
        String view = candidateController.getAll(model);
        var actualCandidates = model.getAttribute("candidates");
        assertThat(view).isEqualTo("candidates/list");
        assertThat(actualCandidates).isEqualTo(expectedCandidates);
    }

    @Test
    public void whenRequestCandidateCreationPageThenGetPageWithCities() {
        var city1 = new City(1, "Москва");
        var city2 = new City(2, "Санкт-Петербург");
        var expectedCities = List.of(city1, city2);
        when(cityService.findAll()).thenReturn(expectedCities);
        var model = new ConcurrentModel();
        var view = candidateController.getCreationPage(model);
        var actualCandidates = model.getAttribute("cities");
        assertThat(view).isEqualTo("candidates/create");
        assertThat(actualCandidates).isEqualTo(expectedCities);
    }

    @Test
    public void whenPostCandidateWithFileThenSameDataAndRedirectToCandidatesPage() throws Exception {
        var candidate = new Candidate(1, "test1", "desc1", now(), 1, 2);
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        var candidateArgumentCaptor = ArgumentCaptor.forClass(Candidate.class);
        var fileDtoArgumentCaptor = ArgumentCaptor.forClass(FileDto.class);
        when(candidateService.save(candidateArgumentCaptor.capture(), fileDtoArgumentCaptor.capture())).thenReturn(candidate);
        var model = new ConcurrentModel();
        var view = candidateController.create(candidate, testFile, model);
        var actualCandidate = candidateArgumentCaptor.getValue();
        var actualFileDto = fileDtoArgumentCaptor.getValue();
        assertThat(view).isEqualTo("redirect:/candidates");
        assertThat(actualCandidate).isEqualTo(candidate);
        assertThat(fileDto).usingRecursiveComparison().isEqualTo(actualFileDto);
    }

    @Test
    public void whenSomeExceptionThrownThenGetErrorPageWithMessage() {
        var expectedException = new RuntimeException("Failed to write file");
        when(candidateService.save(any(), any())).thenThrow(expectedException);
        var model = new ConcurrentModel();
        var view = candidateController.create(new Candidate(), testFile, model);
        var actualExceptionMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualExceptionMessage).isEqualTo(expectedException.getMessage());
    }

    @Test
    public void whenRequestGetCandidateByIdThenReturnPageWithOneCandidate() {
        var candidate = Optional.of(new Candidate(1, "test1", "desc1", now(), 1, 2));
        var city = new City(1, "Москва");
        var expectedCities = List.of(city);
        when(cityService.findAll()).thenReturn(expectedCities);
        when(candidateService.findById(1)).thenReturn(candidate);
        ConcurrentModel model = new ConcurrentModel();
        String view = candidateController.getById(model, 1);
        var actualCandidates = model.getAttribute("candidate");
        var actualCity = model.getAttribute("cities");
        assertThat(actualCandidates).isEqualTo(candidate.get());
        assertThat(actualCity).isEqualTo(expectedCities);
        assertThat(view).isEqualTo("candidates/one");
    }

    @Test
    public void whenRequestGetCandidateByIdThenGetErrorPageWithMessage() {
        var expected = "Резюме с указанным идентификатором не найдено";
        when(candidateService.findById(1)).thenReturn(Optional.empty());
        ConcurrentModel model = new ConcurrentModel();
        String view = candidateController.getById(model, 1);
        var actualMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expected);
    }

    @Test
    public void whenRequestUpdateCandidateThenRedirectCandidatePage() {
        when(candidateService.update(any(), any())).thenReturn(true);
        var candidate = mock(Candidate.class);
        var model = new ConcurrentModel();
        var view = candidateController.update(candidate, testFile, model);
        assertThat(view).isEqualTo("redirect:/candidates");
    }

    @Test
    public void whenRequestUpdateCandidateThenReturnFalseAndRedirectErrorPage() {
        var expected = "Кандидат по Вашему запросу не был найден";
        when(candidateService.update(any(), any())).thenReturn(false);
        var candidate = mock(Candidate.class);
        var model = new ConcurrentModel();
        var view = candidateController.update(candidate, testFile, model);
        var actualMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expected);
    }

    @Test
    public void whenRequestUpdateCandidateThenGetErrorPageWithMessage() {
        var expectedException = new RuntimeException("Вакансия с указанным идентификатором не найдена");
        when(candidateService.update(any(), any())).thenThrow(expectedException);
        var candidate = mock(Candidate.class);
        var model = new ConcurrentModel();
        var view = candidateController.update(candidate, testFile, model);
        var actualExceptionMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualExceptionMessage).isEqualTo(expectedException.getMessage());
    }

    @Test
    public void whenRequestDeleteCandidateThenRedirectCandidatesPage() {
        when(candidateService.deleteById(1)).thenReturn(true);
        var model = new ConcurrentModel();
        var view = candidateController.delete(model, 1);
        assertThat(view).isEqualTo("redirect:/candidates");
    }

    @Test
    public void whenRequestDeleteCandidateThenGetErrorPageWithMessage() {
        var expected = "Резюме с указанным идентификатором не найдено";
        when(candidateService.deleteById(1)).thenReturn(false);
        var model = new ConcurrentModel();
        var view = candidateController.delete(model, 1);
        var actualMessage = model.getAttribute("message");
        assertThat(actualMessage).isEqualTo(expected);
        assertThat(view).isEqualTo("errors/404");
    }
}