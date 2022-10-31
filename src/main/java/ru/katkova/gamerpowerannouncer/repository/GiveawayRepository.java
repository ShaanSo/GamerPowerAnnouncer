package ru.katkova.gamerpowerannouncer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.katkova.gamerpowerannouncer.data.Giveaway;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GiveawayRepository extends JpaRepository<Giveaway, String> {
}
