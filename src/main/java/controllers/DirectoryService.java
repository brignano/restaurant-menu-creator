package controllers;

import com.kogurr.pdf.driver.objects.Menu;
import com.kogurr.pdf.driver.objects.MenuItem;
import com.kogurr.pdf.driver.objects.Submenu;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paolo
 */
@Service
public class DirectoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private SubmenuRepository submenuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(DirectoryService.class);

    public Menu addMenu(Menu menu) {
        return getMenuRepository().save(menu);
    }

    public void deleteMenu(Long id) {
        getMenuRepository().delete(id);
    }

    public Menu findMenu(Long id) {
        return getMenuRepository().findOne(id);
    }

    public Menu updateMenu(Menu menu) {
        return getMenuRepository().save(menu);
    }
    
    public boolean availableUsername(String username) {
        List<UserClass> users = userRepository.findAll();
        for (UserClass user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

	    public Iterable<Menu> getAllMenus(){
        return menuRepository.findAll();
    }
    public MenuItem addMenuItem(MenuItem menuItem) {
        return getMenuItemRepository().save(menuItem);
    }
    public Submenu addSubmenu(Submenu submenu){
        return getSubmenuRepository().save(submenu);
    }

    public UserClass saveUser(UserClass userClass) {
        return getUserRepository().saveAndFlush(userClass);
    }

    public void deleteUser(Long id) {
        getUserRepository().delete(id);
    }

    public UserClass findUser(Long id) {
        return getUserRepository().findOne(id);
    }

    public boolean login(UserClass userClass) {
        return getUserRepository().equals(userClass);
    }

    public SubmenuRepository getSubmenuRepository() {
        return submenuRepository;
    }

    public MenuItemRepository getMenuItemRepository() {
        return menuItemRepository;
    }

    public void setSubmenuRepository(SubmenuRepository submenuRepository) {
        this.submenuRepository = submenuRepository;
    }

    public void setMenuItemRepository(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * @return the userRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * @param userRepository the userRepository to set
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MenuRepository getMenuRepository() {
        return menuRepository;
    }

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    
    public UserClass verifyLogin(UserClass userClass) {
        List<UserClass> users = userRepository.findAll();
        for (UserClass user : users) {
            if (user.getUsername().equals(userClass.getUsername()) && user.getPassword().equals(userClass.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
