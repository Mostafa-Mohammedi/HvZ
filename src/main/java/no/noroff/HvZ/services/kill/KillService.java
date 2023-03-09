package no.noroff.HvZ.services.kill;

import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface KillService extends CrudService<Kill, Integer> {
    Kill findById(Integer id);
    Collection<Kill> findAll();
}
