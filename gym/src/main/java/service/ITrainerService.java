package service;

import dto.TrainerDTO;
import entity.Trainer;

import java.util.List;

public interface ITrainerService extends IService<Trainer> {
    List<TrainerDTO> getAllDTO();
}
