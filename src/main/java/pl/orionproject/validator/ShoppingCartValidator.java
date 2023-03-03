package pl.orionproject.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;

@Component
public class ShoppingCartValidator {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;
    public boolean isShoppingCartIsEmpty() {
        return shoppingCartService.fillItemsByUser().size() == 0 && !userService.getUserSessionEmail().equals("anonymousUser");
    }
}
