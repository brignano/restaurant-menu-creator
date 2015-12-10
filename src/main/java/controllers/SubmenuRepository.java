package controllers;

import com.kogurr.pdf.driver.objects.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmenuRepository extends JpaRepository<Submenu, Long> {
    
}
