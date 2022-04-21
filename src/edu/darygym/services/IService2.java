package edu.darygym.services;
import edu.darygym.entity.Reservation;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author delll
 */
public interface IService2 <Reservation>{
    public void ajouter(Reservation rev) throws SQLException;
  
    public List<Reservation> getAll();
}
