package ru.katkova.gamerpowerannouncer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.katkova.gamerpowerannouncer.data.Giveaway;

@Repository
public interface GiveawayRepository extends JpaRepository<Giveaway, String> {
}
