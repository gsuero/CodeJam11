package com.pmf.codejam.ejb;
import java.util.List;

import com.pmf.codejam.entity.Special;
import com.pmf.codejam.exception.*;
/**
 * 
 * @author Frederick
 *
 */
public interface SpecialServiceLocal {
    void create(Special order);
    void edit(Special order) throws IllegalOrphanException, SpecialException, Exception;
    void destroy(Long id) throws IllegalOrphanException, SpecialException;
    List<Special> findSpecials();
    List<Special> findSpecials(int maxResults, int firstResult);
    Special findSpecial(Long id);
    int getSpecialsCount();
}
