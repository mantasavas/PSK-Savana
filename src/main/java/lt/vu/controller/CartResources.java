package lt.vu.controller;

import lombok.extern.slf4j.Slf4j;
import lt.vu.model.Cart;
import lt.vu.model.CartItem;
import lt.vu.model.Customer;
import lt.vu.model.Product;
import lt.vu.service.api.CartItemService;
import lt.vu.service.api.CartService;
import lt.vu.service.api.CustomerService;
import lt.vu.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/cart")
@Slf4j
public class CartResources {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/{cartId}")
    public @ResponseBody Cart getCartById(@PathVariable(value = "cartId") int cartId, Principal activeUser) {
        if (cartId == -1)
            return new Cart();
        else {
            Customer customer = customerService.getCustomerByEmail(activeUser.getName());
            if (customer.getCart().getCartId() != cartId)
                throw new IllegalAccessError("User tried to access cart other than his current");
            return cartService.getCartById(cartId);
        }
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem (@PathVariable(value = "productId") int productId, Principal activeUser){

        try {
            Customer customer = customerService.getCustomerByEmail(activeUser.getName());
            Product product = productService.getProductById(productId);

            Cart cart = customer.getCart();
            if (cart != null) {
                List<CartItem> cartItems = cart.getCartItems();

                for (CartItem cartItem : cartItems) {
                    if (product.getProductId() == cartItem.getProduct().getProductId()) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        BigDecimal quantityBigDecimal = new BigDecimal(cartItem.getQuantity());
                        cartItem.setTotalPrice(product.getActualPrice().multiply(quantityBigDecimal));

                        cartItemService.addCartItem(cartItem);

                        return;
                    }
                }
            } else
                cart = cartService.createNew(customer);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            BigDecimal quantityBigDecimal = new BigDecimal(cartItem.getQuantity());
            cartItem.setTotalPrice(product.getActualPrice().multiply(quantityBigDecimal));
            cartItem.setCart(cart);

            cartItemService.addCartItem(cartItem);
        }
        catch (Exception exc) {
            log.error(exc.toString());
        }
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable(value = "productId") int productId){
        CartItem cartItem = cartItemService.getCartItemByProductId(productId);
        cartItemService.removeCartItem(cartItem);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable(value = "cartId") int cartId, Principal activeUser){
        Customer customer = customerService.getCustomerByEmail(activeUser.getName());
        if (customer.getCart().getCartId() != cartId) {
            throw new IllegalAccessError("Tried to delete cart not belonging to the active user");
        }

        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) {
       log.info("Returning 400", ex);
    }

    @ExceptionHandler(IllegalAccessError.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Cart not found")
    public void handleIllegalAccessErrors(Exception ex) {
        log.info("Illegal access: ", ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public void handleServerErrors(Exception ex){
        log.error("Internal error: ", ex);
    }

}