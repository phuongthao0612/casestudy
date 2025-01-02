package service.impl;

import dto.TrainerDTO;
import entity.Trainer;
import repository.TrainerRepository;
import service.ITrainerService;

import java.util.List;

public class TrainerService implements ITrainerService {
    private static TrainerRepository trainerRepository = new TrainerRepository();

    @Override
    public List<Trainer> getAll() {
        List<Trainer> trainers = trainerRepository.getAll();
        return trainers;
    }

    @Override
    public void add(Trainer trainer) {

    }


    @Override
    public void update(Trainer trainer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Trainer getById(int id) {
        return null;
    }

    @Override
    public List<TrainerDTO> getAllDTO() {
        return trainerRepository.getAllDTO();
    }
}
