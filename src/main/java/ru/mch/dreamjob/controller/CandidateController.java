package ru.mch.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mch.dreamjob.entity.Candidate;
import ru.mch.dreamjob.entity.Vacancy;
import ru.mch.dreamjob.repository.CandidateRepository;
import ru.mch.dreamjob.repository.MemoryCandidateRepository;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository = MemoryCandidateRepository.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "candidates/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isEmpty()) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("candidate", candidateOptional.get());
        return "candidates/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Candidate candidate, Model model) {
        var isUpdated = candidateRepository.update(candidate);
        if (!isUpdated) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = candidateRepository.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        return "redirect:/candidates";
    }
}