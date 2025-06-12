package com.amigoscode;
import java.util.List;
import java.lang.IllegalStateException;

import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AIService aiService;
    public SoftwareEngineerService(
            SoftwareEngineerRepository softwareEngineerRepository, AIService aiService, AIService aiService1
    ) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService1;
    }
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        String prompt = """
                Based on the programming tech stack %s that %s has given provide a f ull learning path for this person.
                """.formatted(softwareEngineer.getTechStack(), softwareEngineer.getName());
        String chatRes=aiService.chat(prompt);
        softwareEngineer.setTechStack(chatRes);
        softwareEngineerRepository.save(softwareEngineer);
    }
    public void deleteSoftwareEngineer(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    id + " not found"
            );
        }
        softwareEngineerRepository.deleteById(id);
    }
    public void updateSoftwareEngineer(Integer id, SoftwareEngineer updatedEngineer) {
        SoftwareEngineer existing = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Software Engineer with id " + id + " not found"));

        existing.setName(updatedEngineer.getName());
        existing.setTechStack(updatedEngineer.getTechStack());

        softwareEngineerRepository.save(existing); // JPA will use merge() internally
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }
}
